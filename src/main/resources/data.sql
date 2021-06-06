DROP TABLE IF EXISTS weather;

CREATE TABLE weather (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  city VARCHAR(250) NOT NULL,
  country VARCHAR(250) NOT NULL,
  api_key VARCHAR(250) DEFAULT NULL,
  description VARCHAR(250) DEFAULT NULL
);

INSERT INTO weather (city, country, api_key, description) VALUES
  ('Melbourne', 'AU', 'c8aadb8f4504f95b5a9144313cd96f80' , 'Cloudy'),
  ('Melbourne', 'AU', 'c8aadb8f4504f95b5a9144313cd96f81' , 'Cloudy'),
  ('Melbourne', 'AU', 'c8aadb8f4504f95b5a9144313cd96f82' , 'Cloudy'),
  ('Melbourne', 'AU', 'c8aadb8f4504f95b5a9144313cd96f83' , 'Cloudy'),
  ('Melbourne', 'AU', 'c8aadb8f4504f95b5a9144313cd96f84' , 'Cloudy');