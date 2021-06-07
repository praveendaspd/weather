# Design and Implementation

1 Spring boot application to expose a REST API - weather API.

2 The REST Weather API acts as a facade for the OpenWeatherMap service.

3 The Weather API uses API keys for authorization.

4 Limit the number of calls made per API KEY to the Weather API.

4 The Weather app uses in memory database to persist the response received from OpenWeatherMap and to reduce the number of calls made to the actual OpenWeatherMap service.


### Assumptions and Trade-offs

1 An API Key is rate limited to 5 weather reports an hour.

2 After 5 attempts, the Weather API should communicate the hourly rate has exceeded.

3 Define a REST endpoint in the weather API that accepts City Name , Country Name and API Key as the input
  - If authorization succeeds first validate against the data from the in memory database
  - If no data exists, then make a call to the OpenWeatherMap API and render the JSON response with description field
  - Always return a meaningful response back to the caller
  - Clear response messages for rejected calls to the Weather API


## Error Handling

API should return sensible HTTP status message to the client

  - 400 Series - Client Issues
    - 400 : Bad Request (Request is malformed)
    - 401 : Unauthorized (invalid authentication)
    - 403 : Forbidden (When authentication succeeded but authenticated user doesn't have access to the resource)
    - 404 : Not Found (Non Existent resource)
    - 429 : Too many Requests (rate limiting)
    
  - 500 Series - Server Issues
    - 500 : Internal Server Error (unexpected error at server side)
    



