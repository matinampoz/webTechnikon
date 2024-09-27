# WebTechnikon2 API Documentation

## Overview
This project implements a RESTful API for managing owners, properties, and repairs. It utilizes Jakarta EE for the back-end services and data handling, following an MVC architecture.

---

## Resources

The main resources in this application are:
1. **OwnerResource**: Manages property owners.
2. **PropertyResource**: Manages properties.
3. **RepairResource**: Manages repairs.

Each resource provides endpoints for CRUD (Create, Read, Delete) operations. Update will be availiable in the upcoming release.

---

## Endpoints

### 1. **OwnerResource**

Path: `/owner`

  
- **GET** `/owner/properyOwners`: Retrieves a list of all property owners.
  - **Response**: `200 OK`, JSON list of `OwnerDTO`.

- **GET** `/owner/id/{ownerId}`: Retrieves a property owner by ID.
  - **Path Parameter**: `ownerId` - the ID of the owner.
  - **Response**: `200 OK`, JSON object of `OwnerDTO`.

- **GET** `/owner/vat/{vat}`: Retrieves a property owner by VAT number.
  - **Path Parameter**: `vat` - the VAT number of the owner.
  - **Response**: `200 OK`, JSON object of `OwnerDTO`.

- **GET** `/owner/email/{email}`: Retrieves a property owner by email.
  - **Path Parameter**: `email` - the email of the owner.
  - **Response**: `200 OK`, JSON object of `OwnerDTO`.

- **POST** `/owner/add`: Creates a new property owner.
  - **Request Body**: JSON object of `Owner`.
  - **Response**: `201 Created`, the ID of the created owner.

- **DELETE** `/owner/delete/{ownerId}`: Deletes a property owner by ID.
  - **Path Parameter**: `ownerId` - the ID of the owner to delete.
  - **Response**: `200 OK`, `true` if the owner is deleted.

---

### 2. **PropertyResource**

Path: `/property`

- **GET** `/property/properties`: Retrieves a list of all properties.
  - **Response**: `200 OK`, JSON list of `PropertyDTO`.

- **GET** `/property/propertiesOfOwner/{ownersId}`: Retrieves a property by the owner's ID.
  - **Path Parameter**: `ownersId` - the ID of the owner.
  - **Response**: `200 OK`, JSON object of `PropertyDTO`.

- **GET** `/property/propertiesOfOwnerWithVat/{vat}`: Retrieves a property by the owner's VAT number.
  - **Path Parameter**: `vat` - the VAT number of the owner.
  - **Response**: `200 OK`, JSON object of `PropertyDTO`.

- **GET** `/property/{propertyId}`: Retrieves a property by property ID.
  - **Path Parameter**: `propertyId` - the ID of the property.
  - **Response**: `200 OK`, JSON object of `PropertyDTO`.

- **POST** `/property/add`: Creates a new property.
  - **Request Body**: JSON object of `Property`.
  - **Response**: `201 Created`, the ID of the created property.

- **DELETE** `/property/delete/{propertyId}`: Deletes a property by property ID.
  - **Path Parameter**: `propertyId` - the ID of the property to delete.
  - **Response**: `200 OK`, `true` if the property is deleted.

---

### 3. **RepairResource**

Path: `/repair`

- **GET** `/repair/repairs`: Retrieves a list of all repairs.
  - **Response**: `200 OK`, JSON list of `RepairDTO`.

- **GET** `/repair/{repairId}`: Retrieves a repair by its ID.
  - **Path Parameter**: `repairId` - the ID of the repair.
  - **Response**: `200 OK`, JSON object of `RepairDTO`.

- **GET** `/repair/repairsOfOwner/{ownersId}`: Retrieves a list of repairs by the owner's ID.
  - **Path Parameter**: `ownersId` - the ID of the owner.
  - **Response**: `200 OK`, JSON list of `RepairDTO`.

- **POST** `/repair/add`: Creates a new repair.
  - **Request Body**: JSON object of `Repair`.
  - **Response**: `201 Created`, the ID of the created repair.

- **DELETE** `/repair/delete/{repairId}`: Deletes a repair by its ID.
  - **Path Parameter**: `repairId` - the ID of the repair to delete.
  - **Response**: `200 OK`, `true` if the repair is deleted.

---

## DTO Classes
DTOs (Data Transfer Objects) are implemented to avoid issues with back-references during JSON serialization, which can lead to infinite loops when dealing with bidirectional relationships. By using DTOs, only the required data is exposed

### 1. **OwnerDTO**
Contains fields like:
- `ownerId`: ID of the owner.
- `name`: Name of the owner.
- `email`: Email of the owner.
- Maps the `Owner` entity to a Data Transfer Object for JSON serialization.

### 2. **PropertyDTO**
Contains fields like:
- `propertyId`: ID of the property.
- `address`: Address of the property.
- `typeOfProperty`: Enum value for property type.
- `owner`: Contains the `OwnerDTO` object, mapping the owner of the property.

### 3. **RepairDTO**
Contains fields like:
- `repairId`: ID of the repair.
- `typeOfRepair`: Enum value for repair type.
- `description`: Description of the repair.
- `property`: Contains the `PropertyDTO` object, mapping the property of the repair.

---

## Exception Handling

The application handles exceptions using custom exceptions such as `OwnerException`, `PropertyException`, and `RepairException`. These exceptions are caught and mapped to corresponding HTTP error responses, such as:
- `404 Not Found`: For resource not found scenarios.
- `500 Internal Server Error`: For any internal server errors.
  
Each exception returns an appropriate error message, which helps the client understand the nature of the issue.


