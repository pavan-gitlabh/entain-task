# URL Shortener Service

## Overview

A URL Shortener API built with Java and Spring Boot.

---

## Features

- Shorten long URLs
- Optional expiration time (in minutes)
- Custom short codes
- Deduplication (same URL → same short code unless custom provided)
- Redirection with HTTP 301
- Analytics (visit count, creation & expiration time)
- List all shortened URLs
- Input validation and error handling
- Docker and Docker Compose support

---

## Technologies & Tools Used

- Java 17
- Spring Boot
- Maven
- JUnit & Mockito
- Docker & Compose

---

## Installation Steps

### Run with Maven

```
./mvnw spring-boot:run
```

### Build the project

```bash
./mvnw clean package
```

---

## Docker Deployment

### Build image

```bash
docker build -t url-shortener .
```

### Run container

```bash
docker run -d -p 8080:8080 url-shortener
```

### Or use Docker Compose

```bash
docker-compose up --build
```

---

## API Details

### POST `/api/shorten`

Create a shortened URL.

**Request Body:**

```json
{
  "url": "https://sampleurltest2.com/long-url",
  "custom_code": "AFDS",
  "expire_in_minutes": "10"
}
```

**Response:**

```json
{
  "short_url": "http://localhost:8080/AFDS",
  "short_code": "AFDS",
  "original_url": "https://sampleurltest2.com/long-url",
  "created_at": "2025-06-01T12:00:00Z",
  "expires_at": "2025-06-01T12:10:00Z"
}
```

---

### GET `/{shortCode}`

Redirects to the original URL. Returns `301 Moved Permanently` if valid, `410 Gone` if expired, `404` if not found.

---

### GET `/api/stats/{shortCode}`

Returns analytics for a short URL.

---

### GET `/api/urls`

Lists all shortened URLs.

---

## Example Edge Cases Handled

- Invalid or missing URL → `400 Bad Request`
- Duplicate custom code → `409 Conflict`
- Expired short code → `410 Gone`
- Non-existent short code → `404 Not Found`
- Long URLs and invalid inputs handled gracefully

---

## Tests

Run tests and generate coverage report:

```bash
./mvnw test
./mvnw verify
```

Reports are in `target/site/jacoco/index.html`.

---

## 📁 Directory Structure

```
src/
main/
java/
|-- controller
|-- service
|-- model

test/
java/
|-- applicationtest
|-- servicetest
|-- controllertest
```

---

