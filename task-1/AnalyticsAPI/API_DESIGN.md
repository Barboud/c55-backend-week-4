# Analytics API Design

## Create Record

POST /analytics

Request

{
"eventType": "LOGIN",
"eventSource": "WEB",
"sessionId": "ABC123"
}

Response: 200 OK

---

## List Records

GET /analytics

Filters:
GET /analytics?eventType=LOGIN
GET /analytics?eventSource=WEB

Response: 200 OK

---

## Get Record

GET /analytics/{id}

Response: 200 OK
Response: 404 Not Found

---

## Update Record

PUT /analytics/{id}

Response: 200 OK
Response: 404 Not Found

---

## Delete Record

DELETE /analytics/{id}

Response: 200 OK
Response: 404 Not Found

---

## Summary

GET /analytics/summary

Response

{
"totalRecords": 10,
"uniqueSessions": 5,
"eventTotals": {
"LOGIN": 6,
"PURCHASE": 4
}
}