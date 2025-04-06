# 📘 User Activity Journal API Specification

This is the complete API reference and functional documentation for the **User Activity Journal Service**. This service consumes user-related events from Kafka (`user-events` topic), logs them to a PostgreSQL database, and exposes secure, role-based REST APIs for querying the logs.

> ⚠️ Only users with the `ROLE_ADMIN` are authorized to access these endpoints.

---

## 🔐 Authentication

All endpoints require a **valid JWT** in the `Authorization` header:

```
Authorization: Bearer <your-jwt-token>
```

---

## 🧱 Data Models

### 🔸 Enum: `UserActivityType`
```
USER_REGISTERED
USER_LOGGED_IN
ROLE_UPDATED
USER_DELETED
USERS_FETCHED
USER_FETCHED
```

### 🔸 DTO: `UserActivityEvent`
```json
{
  "username": "john.doe",
  "performedBy": "admin1",
  "activityType": "ROLE_UPDATED",
  "details": {
    "ip": "192.168.0.1",
    "role": "ROLE_ADMIN"
  }
}
```

### 🔸 DTO: `UserActivitySearchDTO`
```json
{
  "username": "john.doe",
  "performedBy": "admin1",
  "role": "ROLE_USER",
  "activityType": "USER_LOGGED_IN",
  "startDate": "2024-01-01T00:00:00Z",
  "endDate": "2025-01-01T00:00:00Z",
  "page": 0,
  "size": 10,
  "sortBy": "timestamp",
  "sortDirection": "desc"
}
```

### 🔸 Entity: `UserActivityLog`
```json
{
  "id": 1,
  "username": "john.doe",
  "performedBy": "admin1",
  "activityType": "USER_REGISTERED",
  "role": "ROLE_USER",
  "details": "{\"ip\":\"127.0.0.1\"}",
  "timestamp": "2024-05-01T12:00:00Z"
}
```

---

## 📦 API Endpoints

### 🔍 POST `/api/journal/search`
**Description**: Search logs using filters, pagination, and sorting.

**Request Body**: See `UserActivitySearchDTO`

**Query Support**:
- Filter by: `username`, `performedBy`, `role`, `activityType`, `startDate`, `endDate`
- Sort by: `timestamp`, `username`, `activityType`
- Pagination via `page`, `size`

**Response**:
```json
{
  "content": [ UserActivityLog, ... ],
  "totalElements": 10,
  "totalPages": 1,
  "size": 10,
  "number": 0
}
```

### 📄 GET `/api/journal`
**Description**: Retrieve all logs (admin-only).

**Response**:
```json
[ UserActivityLog, ... ]
```

### 👤 GET `/api/journal/{username}`
**Description**: Get all logs for a specific user.

**Path Variable**:
- `username`: The target user.

**Response**:
```json
[ UserActivityLog, ... ]
```

---

## 🔧 Kafka Consumer
- Consumes events from `user-events` topic.
- Logs metadata (timestamp, performedBy, activityType, etc.)
- Persists data into PostgreSQL.

---

## ✅ Roles & Authorization

| Endpoint                         | Required Role |
|----------------------------------|----------------|
| `GET /api/journal`               | ROLE_ADMIN     |
| `GET /api/journal/{username}`   | ROLE_ADMIN     |
| `POST /api/journal/search`      | ROLE_ADMIN     |

All checks are enforced using Spring Security with JWT decoding.

---

## 🛠️ Notes

- `details` is stored as a serialized JSON string.
- Timestamp is stored in UTC format.
- Only `ROLE_ADMIN` users can access journal data.
- Sorting and filtering are implemented using Spring Data JPA Specifications.

---

## 🧪 Sample Curl

```bash
curl -X POST http://localhost:8081/api/journal/search \
  -H "Authorization: Bearer <jwt-token>" \
  -H "Content-Type: application/json" \
  -d '{
    "performedBy": "admin1",
    "activityType": "USER_DELETED",
    "page": 0,
    "size": 5
  }'
```

---

## 📁 Tech Stack
- Java 17
- Spring Boot 3.x
- Spring Security + JWT
- Kafka Consumer
- PostgreSQL
- JPA + Specifications for filtering

---

> For any issues, reach out to the platform team.
