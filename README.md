📌 Project Overview
FlatWing is a microservices-based Flight Booking backend system built using Spring Boot and Spring Cloud.
The system is designed to handle flight search, seat reservation, booking, and cancellation using independently
deployable services coordinated via an API Gateway and Service Registry.

This project demonstrates real-world microservices concepts such as service discovery, API routing, inter-service
communication, and transactional consistency.

🧩 Microservices Structure
flight-booking-system/
│
├── api-gateway
├── service-registry
├── flight-service
├── flight-search-service
├── booking-service

🛠 Tech Stack
• Java
• Spring Boot
• Spring Cloud
• Spring Cloud Gateway
• Eureka Service Registry
• REST APIs
• PostgreSQL
• Maven
• Lombok

🏗 System Architecture
Client
  ↓
API Gateway
  ↓
Service Registry (Eureka)
  ↓
--------------------------------
| Flight Service               |
| Flight Search Service        |
| Booking Service              |
--------------------------------
• API Gateway → Central entry point
• Service Registry → Dynamic service discovery
• Microservices → Independent business logic

🔁 Service Responsibilities
🔹 API Gateway
• Central routing for all incoming requests
• Load balancing between services
• Gateway-level abstraction for clients

🔹 Service Registry (Eureka)
• Registers all microservices
• Enables dynamic discovery
• Eliminates hard-coded service URLs

✈️ Flight Service
• Responsible for flight inventory and seat management.

📍 Base Path
/v1/api/flights

🔌 Endpoints
Method	Endpoint	Description
POST	/v1/api/flights	Create a new flight
PUT	/v1/api/flights	Update flight details
GET	/v1/api/flights	Fetch all flights
POST	/v1/api/flights/search	Search flights
PUT	/v1/api/flights/{id}/reserve?seats=	Reserve seats
PUT	/v1/api/flights/{id}/restore?seats=	Restore seats

📌 Key Concepts Used
• Seat reservation & restoration logic
• Idempotent update operations
• Inventory consistency

🔍 Flight Search Service

Responsible for searching available flights based on user criteria.

📍 Base Path
/v1/api/search

🔌 Endpoints
Method	Endpoint	Description
POST	/v1/api/search/flights	Search flights based on filters

📌 Purpose
• Read-only optimized search
• Separation of search logic from core flight management

🧾 Booking Service
• Responsible for booking lifecycle management.

📍 Base Path
/v1/api/bookings

🔌 Endpoints
Method	Endpoint	Description
• POST	/v1/api/bookings	Create a booking
• PUT	/v1/api/bookings/cancel	Cancel an existing booking

📌 Booking Flow
• Validate booking request
• Reserve seats via Flight Service
• Create booking record
• Restore seats on cancellation

🔐 Error Handling & Responses
• Uses sealed response models
• Differentiates between:
• Success responses
• Business failures
• Not found scenarios
• Clean HTTP status mapping (201, 204, 404, 422)

🗄 Database
• Each microservice maintains its own database
• PostgreSQL used for persistence
• Loose coupling between services

📊 Project Scale
✅ 15+ REST APIs
✅ 3 Core Business Microservices
✅ API Gateway + Service Registry
✅ Seat reservation & restoration logic
✅ Microservices-based architecture

🚀 How to Run Locally
• Prerequisites
• Java 17+
• PostgreSQL
• Maven

Start Order
1. Service Registry
2. API Gateway
3. Flight Service
4. Flight Search Service
5. Booking Service
 
Run each service:
mvn spring-boot:run

📌 Project Status

✅ Core microservices implemented
🚧 Security & distributed tracing can be added
🚧 Dockerization pending

👩‍💻 Author

Anjali Tiwari
Java Backend Developer | Microservices | Spring Boot
