# Belong
Belong is a Telecom operator. In our database, we are starting to store phone numbers associated to customers
(1 customer: N phone numbers) and we will provide interfaces to manage them.
We need to provide the below capabilities:
* get all phone numbers
* get all phone numbers of a single customer
* activate a phone number

## Technologies Used

The project utilises the following technologies:
- Java 8
- Spring Boot
- Gradle
- H2 in memory as the data store (for simplicity)
- API specifications is provided as swagger file under 'openAPI' folder

## Design considerations
The following design considerations were made:
- A customer can have many phone numbers
- Phone number is unique and belong to a single customer

## To do
Given the time constraints, the following are to be considered in future release:
- Add security to endpoints
- Replace h2 in memory with proper db instance
- Containerize for improved deployment
- CRUD operations

## Build & Run
The project utilises Gradle Wrapper.
To build the project, browse to the project root directory and simply execute:
```
./gradlew build
```
To run the application, execute:
```
./gradlew bootRun
```
This will initialise SpringBoot on port 8080 where the API can be accessed via:
```
http://localhost:8080
```

The project is preloaded with sample customer data with associated phone numbers:
### Customers
| id | first_name | last_name |
| --- | --- | --- |
| 1001   | John | Doe |
| 1002   | Jane | Doe |

### Phone numbers
| id | customer_id | number | status |
| --- | --- | --- | --- |
| 1001 | 1001 | 0411000111 | inactive |
| 1002 | 1001 | 0411000222 | inactive |
| 1003 | 1002 | 0411111111 | inactive |

## Endpoints
Get a customer's phone numbers
```shell
http://localhost:8080/v1/customers/1001
```
Get all phone numbers
```shell
http://localhost:8080/v1/phone_numbers
```
Activate a phone number
```shell
http://localhost:8080/v1/phone_numbers/1001/activate
```