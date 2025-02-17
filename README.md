# URL Shortener

A simple URL shortener service built with **Spring Boot** 

## Features
- Generate short URLs from long URLs
- Redirect short URLs to their original long URLs
- Uses **Spring Boot**, **JPA**

---

## Getting Started

### Prerequisites
- **Java 17+**
- **Maven**

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Alan-459/UrlShortner.git
   cd UrlShortner
   ```

2. Configure database settings in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/url_shortener
   spring.datasource.username=postgres
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```

---

## API Endpoints

### 1️⃣ Shorten a URL
**Endpoint:** `POST /api/shorten`

**Request Body:**
```json
{
  "url": "https://example.com/long-url"
}
```

**Response:**
```json
{
  "shortUrl": "http://localhost:8080/api/abc123"
}
```

### 2️⃣ Redirect to Original URL
**Endpoint:** `GET /api/{shortCode}`

**Example:**
```
GET http://localhost:8080/api/abc123
```
Redirects to: `https://example.com/long-url`

---

## Project Structure
```
UrlShortner/
│-- src/main/java/com/example/urlshortener/
│   ├── model/ShortUrl.java  # Entity
│   ├── repository/ShortUrlRepository.java  # Database Access
│   ├── service/ShortUrlService.java  # Business Logic
│   ├── controller/ShortUrlController.java  # API Endpoints
│-- src/main/resources/application.properties
```

---

## Running with H2 (In-Memory Database)
For testing, use H2 instead of PostgreSQL:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

---




