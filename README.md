# Veterinary Management System
## Spring Boot - Veterinary Management System BE Bootcamp Graduation Project

### Introduction
This project aims to develop an API to manage veterinary clinic operations effectively, enabling staff to perform various tasks.

### Project Description
The software facilitates veterinary clinic operations, including managing veterinarians, customers, animals, vaccines, appointments, and doctors. API endpoints are provided for managing these components.

## How to Run
1. Ensure Java 17 or later is installed.
2. Install PostgreSQL. Use the `veterinary_management.sql` file located in the `Veterinary-Management-System` folder to set up the database.
3. Import the SQL file into PostgreSQL.
4. Clone this repository.
5. Open the project in your preferred IDE (e.g., IntelliJ IDEA).
6. Build and run the project.

## System Requirements
- Java 17 or later
- PostgreSQL

## Technologies Used
- Object-Oriented Programming (OOP): Follows object-oriented programming principles to design the system.
- Java: Used for backend development, leveraging its robustness and portability.
- Spring Boot: Provides a framework for building production-ready applications quickly with a convention-over-configuration approach.
- Spring Data JPA: Facilitates the implementation of JPA-based repositories, making database access easy.
- Spring Web: Simplifies the creation of web applications and RESTful services.
- Maven: Used for project management and build automation, handling dependencies and project configurations.
- PostgreSQL: Relational database management system used for data storage and retrieval.
- pgAdmin 4: Management tool for PostgreSQL, providing a graphical interface for database management.
- SQL: Used for executing database queries and operations.
- IntelliJ IDEA: Integrated development environment (IDE) used for Java development, offering powerful coding assistance and tools.
- Git: Version control system used for tracking changes in source code during development.
- GitHub: Platform for hosting project files, collaboration, and version control.


### Project Components
- Animal: Manage animals and their owners.
- Customer: Manage customer details.
- Vaccine: Manage vaccine records.
- Doctor: Manage doctor details.
- AvailableDate: Manage doctors' available dates.
- Appointment: Manage appointments.

### API Features
- Manage animals and owners.
- Manage administered vaccines.
- Manage appointments.
- Manage doctors.

## UML Diagram
<img src="https://github.com/wvazabi/Veterinary-Management-System/blob/main/Images/Veterinary%20Management%20System%20UML%20Diagram.png" alt="UML Diagram" width="700" height="400">

## Endpoints
### VaccineController
| No. | Method | URL                                                                   | Description                          |
|-----|--------|-----------------------------------------------------------------------|--------------------------------------|
| 1   | POST   | /v1/vaccines                                                          | Creates a new vaccine record         |
| 2   | GET    | /v1/vaccines/{id}                                                     | Retrieves a specific vaccine by ID   |
| 3   | PUT    | /v1/vaccines/{id}                                                     | Updates a vaccine record             |
| 4   | DELETE | /v1/vaccines/{id}                                                     | Deletes a vaccine record             |
| 5   | GET    | /v1/vaccines/vaccine_calendar?startDate={startDate}&endDate={endDate} | Retrieves upcoming vaccines          |
| 6   | GET    | /v1/vaccines/animalId={animalId}                                      | Retrieves vaccines by animal ID      |
| 7  | GET    | /v1/appointments?page=0&pageSize=1000                                                       | Retrieves all appointments with pagination |


### AnimalController
| No. | Method | URL                                            | Description                          |
|-----|--------|------------------------------------------------|--------------------------------------|
| 1   | POST   | /v1/animals                                    | Creates a new animal record          |
| 2   | GET    | /v1/animals/{id}                               | Retrieves a specific animal by ID    |
| 3   | PUT    | /v1/animals                                    | Updates an animal record             |
| 4   | DELETE | /v1/animals/{id}                               | Deletes an animal record             |
| 5   | GET    | /v1/animals?page=0&pageSize=200                | Retrieves all animals with pagination|
| 6   | GET    | /v1/animals/name={name}                        | Retrieves animals by name            |
| 7   | GET    | /v1/animals/customer_name={customerName}       | Retrieves animals by customer name   |


### DoctorController
| No. | Method | URL                     | Description                                    |
|-----|--------|-------------------------|------------------------------------------------|
| 1   | POST   | /v1/doctors             | Creates a new doctor record                    |
| 2   | GET    | /v1/doctors/{id}        | Retrieves a specific doctor by ID              |
| 3   | PUT    | /v1/doctors             | Updates a doctor record                        |
| 4   | DELETE | /v1/doctors/{id}        | Deletes a doctor record                        |
| 5   | GET    | /v1/doctors             | Retrieves all doctors                          |
| 6   | GET    | /v1/doctors?page=0&pageSize=1000 | Retrieves all doctors with pagination |


### AppointmentController
| No. | Method | URL                                                                                        | Description                                 |
|-----|--------|--------------------------------------------------------------------------------------------|---------------------------------------------|
| 1   | POST   | /v1/appointments                                                                            | Creates a new appointment record           |
| 2   | GET    | /v1/appointments/{id}                                                                       | Retrieves a specific appointment by ID     |
| 3   | PUT    | /v1/appointments                                                                            | Updates an appointment record              |
| 4   | DELETE | /v1/appointments/{id}                                                                       | Deletes an appointment record              |
| 5   | GET    | /v1/appointments/doctor/{doctorId}?startDate={startDate}&endDate={endDate}                  | Retrieves appointments by doctor and date  |
| 6   | GET    | /v1/appointments/animal/{animalId}?startDate={startDate}&endDate={endDate}                  | Retrieves appointments by animal and date  |
| 7   | GET    | /v1/appointments                                                                            | Retrieves all appointments                 |
| 8   | GET    | /v1/appointments?page=0&pageSize=1000                                                       | Retrieves all appointments with pagination |


### AvailableDateController
| No. | Method | URL                       | Description                            |
|-----|--------|---------------------------|----------------------------------------|
| 1   | POST   | /v1/available-dates       | Creates a new available date record    |
| 2   | GET    | /v1/available-dates/{id}  | Retrieves a specific available date by ID |
| 3   | PUT    | /v1/available-dates       | Updates an available date record       |
| 4   | DELETE | /v1/available-dates/{id}  | Deletes an available date record       |
| 5   | GET    | /v1/available-dates       | Retrieves all available dates          |
| 6   | GET    | /v1/available-dates?page=0&pageSize=1000 | Retrieves available dates with pagination |


## Postman API Test Screenshot
![Postman Test](https://github.com/wvazabi/Veterinary-Management-System/blob/main/Images/Postman.png)

## Veterinary Management System - Project Demonstration Video
[![Veterinary Management System - Project Demonstration Video](https://img.youtube.com/vi/RtjSzFo8omM/0.jpg)](https://www.youtube.com/watch?v=RtjSzFo8omM)

