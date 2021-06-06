/**
 * 
 */
package com.vanguard.coding.challenge.weather.api.key.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.vangaurd.coding.challenge.weather.common.AppConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * Filter to validate the API Key
 * 
 * @author praveendas
 *
 */
@Slf4j
@Component
public class ApiKeyRequestFilter extends GenericFilterBean {

	Logger logger = LoggerFactory.getLogger(ApiKeyRequestFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		//Extract the apiKey from the request
		String[] apiKey = req.getParameterValues("apiKey");

		logger.info("Verifying apiKey: {}", apiKey[0]);
		
		Optional<ApiKey> apiKeyOptional = findkey(apiKey[0]);

		if (apiKeyOptional.isPresent()) {
			
			logger.info("apiKey validation successful for apiKey : {}", apiKey[0]);
			chain.doFilter(request, response);
			
		} else {
			
			logger.info("API Key Validation failed! Invalid apiKey : {}", apiKey[0]);
			
			HttpServletResponse resp = (HttpServletResponse) response;
			String error = "API Key Validation failed - Invalid API KEY!";

			resp.reset();
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentLength(error.length());
			response.getWriter().write(error);
		}

	}

	/**
	 * Validates the apiKey against the existing 5 keys
	 * 
	 * @param key
	 * 
	 * @return Optional<ApiKey> if validation is successful
	 * 
	 */
	private Optional<ApiKey> findkey(String key) {

		// TODO : If time permits, move this to H2 database
		// Optional<ApiKey> apiKeyOptional = this.apiKeyRepository.findOneByKey(apiKey);
		
		List<String> apiKeys = new ArrayList<String>();
		apiKeys.add(AppConstants.API_KEY_1);
		apiKeys.add(AppConstants.API_KEY_2);
		apiKeys.add(AppConstants.API_KEY_3);
		apiKeys.add(AppConstants.API_KEY_4);
		apiKeys.add(AppConstants.API_KEY_5);
		
		if (apiKeys.contains(key)) {
			ApiKey apiKey = new ApiKey();
			apiKey.setApiKey(key);
			return Optional.ofNullable(apiKey);
			
		} else {
			
			return Optional.empty();
		}

	}

}
