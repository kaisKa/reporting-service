# Reporting Service

## Overview

This project is a Spring Boot service designed to:

1. Consume submissions from a Kafka topic.
2. Perform stream processing and aggregation of submission data.
3. Store aggregated data in MongoDB.
4. Expose secured API endpoints to retrieve reports.
5. Secure the API endpoints using JWT and Spring Security.
6. Integrate Swagger for API documentation.
7. Include a Postman collection for testing the API.
8. Use Docker Compose to set up the necessary services including Kafka, MongoDB, and Zookeeper.

## Features

- **Kafka Stream Processing**:
  - Consumes submission data from a Kafka topic.
  - Aggregates the data for reporting purposes (e.g., total submissions per service, data trends over time, frequently used services).
- **Data Storage**:
  - Aggregated data is stored in **MongoDB** for persistence and retrieval.
- **Security**:
  - API endpoints are secured using **JWT** and **Spring Security**.
- **API Documentation**:
  - Swagger UI available at `http://localhost:9999/swagger-ui.html`.
- **Docker Support**:
  - Includes a Docker Compose file to run the stack.
  - Services include Kafka, Zookeeper, and MongoDB.
- **Postman Collection**:
  - Pre-configured collection for testing API endpoints.

## Prerequisites

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Postman](https://www.postman.com/)

## Getting Started

### Clone the Repository

```bash
git clone <repository-url>
cd reporting-service
```

### Running the Stack

Use Docker Compose to start if not already started the services (file included in the core service):

```bash
docker-compose up -d
```

The following services will be available:

- **Kafka**: Message broker
- **MongoDB**: NoSQL database
- **Mongo Express**: MongoDB admin interface (accessible at `http://localhost:8081`)

### Accessing Swagger UI

Swagger documentation is available at:

```
http://localhost:9999/swagger-ui.html
```

## Exposed Endpoints

### Base URL: `/api/report`

#### **Get Total Submissions**
- `GET /api/report/total-submissions`
- Retrieves the total number of submissions per service.


#### **Get Stats by Submission Count**
- `GET /api/report/stats-by-submission-count`
- Retrieves data on submission counts per service or customer.


#### **Get Daily Trends**
- `GET /api/report/daily-trends`
- Retrieves submission trends aggregated by day (e.g., number of submissions per day).

#### **Get Weekly Trends**
- `GET /api/report/weekly-trends`
- Retrieves submission trends aggregated by week (e.g., number of submissions per week).


## Dependencies

- **Spring Boot**
- **Spring Security**
- **MongoDB Driver**
- **PostgreSQL Driver**
- **Spring Kafka**
- **Swagger/OpenAPI**
- **Docker Compose**

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
