# Design and Implementation

1 Spring boot application to expose a REST API - weather API.

2 The REST Weather API acts as a facade for the OpenWeatherMap service.

3 The Weather API uses API keys for authorization and to limit the number of calls made to the Weather API.

4 The Weather app uses in memory database to persist the response received from OpenWeatherMap to reduce the number of calls made to the actual OpenWeatherMap service.(Caching)


### Assumptions and Trade-offs

1 An API Key is rate limited to 5 weather reports an hour.

2 After 5 attempts, the Weather API should communicate the hourly rate has exceeded.

3 Define a REST endpoint in the weather API that accepts City Name , Country Name and API Key as the input
  - If authorization succeeds first validate against the data from the in memory database
  - If no data exists, then make a call to the OpenWeatherMap API and render the JSON response with description field
  - Always return a meaningful response back to the caller
  - Clear response messages for rejected calls to the Weather API


## Error Handling

1 API should return sensible HTTP status codes
  - 400 Series - Client Issues
    - 400 : Bad Request (Request is malformed)
    - 401 : Unauthorized (invalid authentication)
    - 403 : Forbidden (When authentication succeeded but authenticated user doesn't have access to the resource)
    - 404 : Not Found (Non Existent resource)
    - 405 : Method Not Allowed (HTTP Method requested is not allowed on the resource)
    - 415 : Unsupported Media Type (Incorrect content type)
    - 422 : Unprocessable entity (Validation errors)
    - 429 : Too many Requests (rate limiting)
    
  - 500 Series - Server Issues
    - 500 : Internal Server Error (unexpected error at server side)
    - 501 : Not Implemented (missing or unsupported functionality)
    - 502 : Bad Gateway (Invalid response from upstream server
    - 503 : Service Unavailable(Temporary down time Or refuse connections)
    - 504 : Gateway timeout (server did not receive timely response)
    
2 Provide a JSON error body
  - Code
  - Message
  - Description

3 Validation Errors
  - Code
  - Message
  - Errors
    - Code
    - Field
    - Message


