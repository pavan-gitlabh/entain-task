# URL Shortener – SOLUTION.md

## Overview

This project implements URL shortening service. It allows users to shorten URLs, specify custom short codes, apply expiration rules, and track analytics.

---

## Design Goals

- Simplicity and clarity in codebase
- RESTful APIs with proper status codes
- Stateless controller, isolated service, repository logic
- In-memory persistence for simplicity (extendable to rdbms or nosql)
- Fully testable with JUnit and Mockito

---

## Components

- **Model**: `UrlMapping` stores original, shortened URL, timestamps, visit count
- **Service**: `UrlShortenerService` handles business logic: generation, validation, deduplication
- **Controller**: `UrlShortenerController` manages API endpoints and error handling

---

## Features Included

| Feature                  | Notes                                           |
|--------------------------|-------------------------------------------------|
| Shorten URL              | Generates 6-char random code or accepts custom |
| Redirect by short code   | 301 redirect with visit count update           |
| Expiration time          | Optional; 410 returned if expired              |
| Deduplication            | Same URL gives same code unless custom given   |
| List all URLs            | GET `/api/urls` returns all mappings           |
| Stats                    | Includes visit count, timestamps               |
| Docker/Compose support   | Easy to run and deploy                         |

---

## Testing Strategy

### Unit Tests
- Validate each endpoint and edge case
- Mockito used to isolate controller from service
- Tested with MockMvc for API simulation

### Examples
- Shorten valid URL → expect 200
- Submit invalid URL → expect 400
- Expired code → expect 410
- Custom code reused → expect 409
- View stats → expect correct counts

---

## Deployment

### Local:
```bash
./mvnw spring-boot:run
```

### Docker:
```bash
docker build -t url-shortener .
docker run -d -p 8080:8080 url-shortener
```

### Docker Compose:
```bash
docker-compose up --build
```

### Heroku (optional):
- Add Procfile
- Push with Git and Heroku CLI

---

## Future Improvements

- Authentication & rate limiting (using api gateway)
- Persistent database (PostgreSQL, Redis, Mongodb)
- URL blacklist & safety checks
- Metrics dashboard ( Prometheus, Grafana config has been included )

---

