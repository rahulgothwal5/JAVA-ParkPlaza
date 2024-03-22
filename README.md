# ParkPlaza: A Central Hub for Vehicle Registry with Elasticsearch

ParkPlaza is a Spring Boot application integrated with Elasticsearch, designed for managing vehicle registry and parking data. This project provides a centralized platform for storing, querying, and managing information related to vehicles and their parking details.

## Postman Collection

Explore the ParkPlaza APIs using the Postman collection provided below:

[![Run in Postman](https://run.pstmn.io/button.svg)](https://www.getpostman.com/collections/33686b3b-8845-44cb-8bdf-9da6ae1377ae)

### Endpoints

| Name                            | Method | Description                          | URL                                               | Body (Raw)                                                                                     |
|---------------------------------|--------|--------------------------------------|---------------------------------------------------|------------------------------------------------------------------------------------------------|
| Save Person                     | POST   | Save a new person.                   | `localhost:8080/api/person`                       | ```json { "id": "45", "name": "Gopsi" }```                                                    |
| Fetch Person                    | GET    | Retrieve a person by ID.             | `localhost:8080/api/person/45`                    | -                                                                                              |
| Fetch all Persons               | GET    | Retrieve all persons.                | `localhost:8080/api/person`                       | -                                                                                              |
| Save Vehicle                    | POST   | Save a new vehicle.                  | `localhost:8080/api/vehicle`                      | ```json { "id": "45", "number": "RJ-14-HY-0052" }```                                           |
| Fetch Vehicle                   | GET    | Retrieve a vehicle by ID.            | `localhost:8080/api/vehicle/45`                   | -                                                                                              |
| Fetch all Vehicles              | GET    | Retrieve all vehicles.               | `localhost:8080/api/vehicle`                      | -                                                                                              |
| Delete All Indices              | POST   | Delete all Elasticsearch indices.    | `localhost:8080/api/index/recreate`               | -                                                                                              |
| Search Vehicles                 | POST   | Search vehicles by term.             | `localhost:8080/api/vehicle/search?query=RJ&page=0&size=2` | ```json { "searchTerm": "rj", "fields": ["number"] }```                                       |
| Search Vehicles by Dates        | GET    | Search vehicles by dates.            | `localhost:8080/api/vehicle/search/2006-12-09`    | -                                                                                              |
| Search Vehicles with Sorting and paging | POST | Search vehicles with sorting and paging. | `localhost:8080/api/vehicle/search`          | ```json { "searchTerm": "audi", "fields": ["name"], "sortBy": "created", "order": "ASC", "page": 0, "size": 20 }``` |
| Search Vehicles with Sorting, paging  and date Range | POST | Search vehicles with sorting, paging, and date range. | `localhost:8080/api/vehicle/searchcreatedsince/2006-12-09` | ```json { "searchTerm": "audi", "fields": ["name"], "sortBy": "created", "order": "ASC", "page": 0, "size": 20 }``` |

## Technologies Used

- Spring Boot
- Elasticsearch
- Docker
- Java Persistence API (JPA)
- RESTful APIs

## Installation

1. Clone the repository: `git clone https://github.com/your-username/ParkPlaza.git`
2. Navigate to the project directory: `cd ParkPlaza`
3. Build the project: `mvn clean install`
4. Run the application: `java -jar target/ParkPlaza-0.0.1-SNAPSHOT.jar`

## Usage

1. Access the Postman collection provided above to explore and interact with the APIs.
2. Use the endpoints to save, retrieve, and search for persons and vehicles.
3. Ensure Elasticsearch is running and properly configured for the application to work.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
