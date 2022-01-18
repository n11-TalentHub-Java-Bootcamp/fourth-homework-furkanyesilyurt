# fourth-homework
n11-TalentHub-Java-Bootcamp

### Project Description
This project is a simulation of a small e-banking system. 
___________________________________________________________________
### Project Structure
* Entity
* Dto
  * Debt
  * Payment
  * User
* Dao
* Enums
* Exception
  * DebtException
  * PaymentException
  * UserException
* Service
  * EntityService
* Converter
* Controller
___________________________________________________________________
### Built With
* Java
* Jpa
* Spring Boot
* PostgreSQL
* MapStruct
* Lombok
* Swagger
___________________________________________________________________
### Project End Points

[UserController](https://github.com/n11-TalentHub-Java-Bootcamp/fourth-homework-furkanyesilyurt/blob/b492acb2d67fe2991246c3adf3719e9ed9d749b3/src/main/java/com/furkanyesilyurt/fourthHomework/controller/UserController.java)

| Method | URI                                 | Request Body                                                 | Description                 |
| ------ | ----------------------------------- | :----------------------------------------------------------- | --------------------------- |
| GET    | /api/v1/users                       |                                                              | Get all users               |
| GET    | /api/v1/users/{id}                  |                                                              | Get a user using id         |
| GET    | /api/v1/users/firstName/{firstName} |                                                              | Get a user using first name |
| POST   | /api/v1/users                       | {   "firstName": "Furkan",   <br/>	"lastName": "Yesilyurt",   <br/>	"email": "f.yesilyurt",   <br/>	"phone": "5381234567",   <br/>	"birthday": "2022-01-18T17:18:51.243Z" } | Save a user                 |
| PUT    | /api/v1/users/{id}                  | {   "firstName": "Ahmet",<br/>	"lastName": "Yılmaz",<br/>	"email": "a.yilmaz",<br/>	"phone": "5351234567",<br/>	"birthday": "2022-01-18T17:19:47.376Z" } | Update a user using id      |
| DELETE | /api/v1/users/{id}                  |                                                              | Delete a user using id      |

[DebtController](https://github.com/n11-TalentHub-Java-Bootcamp/fourth-homework-furkanyesilyurt/blob/b492acb2d67fe2991246c3adf3719e9ed9d749b3/src/main/java/com/furkanyesilyurt/fourthHomework/controller/DebtController.java)

| Method | URI                                | Request Body                                                 | Description                       |
| ------ | ---------------------------------- | ------------------------------------------------------------ | --------------------------------- |
| GET    | /api/v1/debts                      |                                                              | Get all debts                     |
| GET    | /api/v1/debtsdebtI                 |                                                              | Get a debt using id               |
| POST   | /api/v1/debts                      | {  "userId": 1,<br/>	  "mainDebt": 123.50,<br/>	  "remainingDebt": 123.50,<br/>	  "expiryDate": "2022-01-18T18:32:39.190Z",<br/>	  "debt": 0} | Save a debt                       |
| GET    | /api/v1/debts/expirydate           |                                                              | Get debts between two dates       |
| GET    | /api/v1/debts/userId               |                                                              | Get debts using user id           |
| GET    | /api/v1/debts/userId/expirydate    |                                                              | Get overdue debts of the user     |
| GET    | /api/v1/debts/totalDebt            |                                                              | Get sum debts of the user         |
| GET    | /api/v1/debts/totalDebt/expirydate |                                                              | Get sum overdue debts of the user |
| GET    | /api/v1/debts/delaydebt            |                                                              | Get delay hike of the user        |
| DELETE | /api/v1/debts                      |                                                              | Delete a debt using user id       |
| PUT    | /api/v1/debts                      | {  "userId": 5,<br/>	  "mainDebt": 123.50,<br/>	  "remainingDebt": 123.50,<br/>	  "expiryDate": "2022-01-18T18:32:39.190Z",<br/>	  "debt": 0} | Update a debt using id            |

[PaymentController](https://github.com/n11-TalentHub-Java-Bootcamp/fourth-homework-furkanyesilyurt/blob/b492acb2d67fe2991246c3adf3719e9ed9d749b3/src/main/java/com/furkanyesilyurt/fourthHomework/controller/PaymentController.java)

| Method | URI                         | Request Body                                                 | Description                              |
| ------ | --------------------------- | ------------------------------------------------------------ | ---------------------------------------- |
| GET    | /api/v1/payments            |                                                              | Gel all payments                         |
| POST   | /api/v1/payments            | {  "debtId": 1,<br/>  "paymentDate": "2022-01-18T18:57:56.876Z",<br/>  "userId": 1} | Save a payment                           |
| GET    | /api/v1/payments/expirydate |                                                              | Get payments between two dates           |
| GET    | /api/v1/payments/userId     |                                                              | Get a payment using user id              |
| DELETE | /api/v1/payments            |                                                              | Delete a payment                         |
| PUT    | /api/v1/payments            | {"debtId": 1,<br/>  "paymentDate": "2022-01-18T18:57:56.876Z", <br/>  "userId": 1} | Update a payment                         |
| GET    | /api/v1/payments/delayDebt  |                                                              | Get total paid delay debts using user id |


### Swagger Api-Doc
[Api-docs.json](https://github.com/n11-TalentHub-Java-Bootcamp/fourth-homework-furkanyesilyurt/blob/5607d29ff0bd34510b688285d20cae93dc89fa36/src/main/resources/api-docs.json)

___________________________________________________________________
### References
* [https://blog.fireheart.in/a?ID=00950-af381d8b-ebcd-4370-b163-8727ed608e75](https://blog.fireheart.in/a?ID=00950-af381d8b-ebcd-4370-b163-8727ed608e75)

