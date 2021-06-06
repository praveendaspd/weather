package com.vanguard.coding.challenge.weather.api.key.filter;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vanguard.coding.challenge.weather.common.AppConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * Filter to limit the API calls per hour using Googles Guava library 
 * 
 * @author praveendas
 *
 */
@Slf4j
@Component
public class ApiKeyRequestThrottle implements Filter {
	
	Logger logger = LoggerFactory.getLogger(ApiKeyRequestThrottle.class);

	@Value("${rate.limit.hourly.limit}")
	private int MAX_REQUESTS_PER_HOUR;

	private LoadingCache<String, Integer> requestCountsPerIpAddress;

	public ApiKeyRequestThrottle() {
		super();
		requestCountsPerIpAddress = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		String clientIpAddress = getClientIP((HttpServletRequest) servletRequest);
		
		logger.info("clientIpAddress - {}",clientIpAddress);
		
		if (isMaximumRequestsPerHourExceeded(clientIpAddress)) {
			
			logger.info("Maximum requests exceeded for IP Address - {} ",clientIpAddress);
			
			httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
			httpServletResponse.getWriter().write(AppConstants.API_THROTTLE_ERROR_MESSAGE + MAX_REQUESTS_PER_HOUR);
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	/**
	 * Validates the no.of requests received for the IP Address
	 * 
	 * @param clientIpAddress
	 * 
	 * @return true when rate exceeded
	 */
	private boolean isMaximumRequestsPerHourExceeded(String clientIpAddress) {
		int requests = 0;
		try {
			requests = requestCountsPerIpAddress.get(clientIpAddress);
			if (requests == MAX_REQUESTS_PER_HOUR) {
				requestCountsPerIpAddress.put(clientIpAddress, requests);
				return true;
			}
		} catch (ExecutionException e) {
			requests = 0;
		}
		requests++;
		requestCountsPerIpAddress.put(clientIpAddress, requests);
		return false;
	}

	/**
	 * Get the clients IP Address from the request header
	 * 
	 * @param request
	 * 
	 * @return String IP Address
	 */
	public String getClientIP(HttpServletRequest request) {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}

	@Override
	public void destroy() {

	}

}
