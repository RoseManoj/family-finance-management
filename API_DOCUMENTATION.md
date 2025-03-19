I'll create a comprehensive API documentation for the Family Finance Management System based on the controllers and DTOs.

# Family Finance Management API Documentation

## Table of Contents
- [Authentication](#authentication)
- [User Management](#user-management)
- [Family Management](#family-management)
- [Category Management](#category-management)
- [Transaction Management](#transaction-management)

## Authentication
All protected endpoints require a JWT token in the Authorization header:
```
Authorization: Bearer <jwt_token>
```

## User Management

### Register User
```
POST /api/users/register
```

**Request Body:**
```json
{
  "email": "string",
  "password": "string",
  "name": "string",
  "income": "number"
}
```

**Response (201 Created):**
```json
{
  "id": "number",
  "email": "string",
  "name": "string"
}
```

### Login User
```
POST /api/users/login
```

**Request Body:**
```json
{
  "email": "string",
  "password": "string"
}
```

**Response (200 OK):**
```json
{
  "token": "string"
}
```

## Family Management

### Create Family
```
POST /api/families/create
```

**Request Body:**
```json
{
  "familyName": "string",
  "memberCount": "number"
}
```

**Response (201 Created):**
```json
{
  "familyId": "number",
  "familyCode": "string",
  "familyName": "string"
}
```

### Join Family
```
POST /api/families/join
```

**Request Body:**
```json
{
  "email": "string",
  "familyCode": "string"
}
```

**Response (200 OK):**
```json
{
  "id": "number",
  "familyName": "string"
}
```

## Category Management

### Add Category
```
POST /api/categories/add
```

**Request Body:**
```json
{
  "name": "string"
}
```

**Response (201 Created):**
```json
{
  "id": "number",
  "name": "string"
}
```

### Get Family Categories
```
GET /api/categories/family
```

**Response (200 OK):**
```json
[
  {
    "id": "number",
    "name": "string"
  }
]
```

## Transaction Management

### Add Transaction
```
POST /api/transactions/add
```

**Request Body:**
```json
{
  "amount": "number",
  "categoryId": "number",
  "description": "string",
  "date": "string (yyyy-MM-dd)",
  "transactionType": "EXPENSE | INCOME"
}
```

**Response (201 Created):**
```json
{
  "id": "number",
  "amount": "number",
  "category": "string",
  "description": "string",
  "date": "string",
  "transactionType": "EXPENSE | INCOME"
}
```

### Get Family Transactions
```
GET /api/transactions/family
```

**Response (200 OK):**
```json
[
  {
    "id": "number",
    "amount": "number",
    "category": "string",
    "description": "string",
    "date": "string",
    "transactionType": "EXPENSE | INCOME"
  }
]
```

## Error Responses

All endpoints may return the following error responses:

**401 Unauthorized:**
- When JWT token is missing or invalid

**400 Bad Request:**
- When request validation fails

**404 Not Found:**
- When requested resource is not found

**500 Internal Server Error:**
- When server encounters an unexpected condition

