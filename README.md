# Reading is good

#### Online book retail API

This is the backend server or online book selling application.
Developed by using SpringBoot(2.6.7) and MongoDB.
It runs on an embedded Tomcat via port 8080.

http://localhost:8080/

Server runs with Spring-Security using Bearer Token authentication. Email will be used as `user_name`

#### It has 4 different controller(User, Book, Order, Statistic)

- UserController - login/signup and listing user's orders
- OrderController - create new orders
- BookController - Crud operations for books
- StatisticController - display the monthly Statistics

#### DB structure

```javascript
"users"
:
{
    "id"
:
    "String",
        "firstName"
:
    "String",
        "lastName"
:
    "String",
        "email"
:
    "String"
}
```

```javascript
"books"
:
{
    "id"
:
    "String",
        "name"
:
    "String",
        "desc"
:
    "String",
        "price"
:
    "BigDecimal",
        "stock"
:
    "Integer"
}
```

```javascript
"orders"
:
{
    "id"
:
    "String",
        "userId"
:
    "String",
        "amount"
:
    "BigDecimal",
        "creationDate"
:
    "Date",
        "orderDetails"
:
    [
        "bookId"
:
    "String",
        "quantity"
:
    "Integer"
]
}
```

```javascript
"statistics"
:
{
    "id"
:
    "String",
        "orderCount"
:
    "Long",
        "revenue"
:
    "BigDecimal",
        "soldBookCount"
:
    "Long",
        "month"
:
    "Integer",
        "year"
:
    "Integer"
}
```

## Technologies

* JDK 11
* Spring boot
* Spring Security with JWT
* MongoDB
* Lombok, mapstruct, swagger, mockito, faker

## Setup & Run

This project uses Docker as container. In order to run project you need to pre-install Docker.
https://docs.docker.com/get-docker/

Before running project install Java 11+ and set JAVA_HOME as enviroment variable.

```bash 
  ./mvnw clean install
  docker-compose build --no-cache
  docker-compose up --force-recreate
```

After running project you can visit http://localhost:8080/swagger-ui/index.html for open API specification.

## API endpoints

### User Controller

#### Login to the system

```http
  POST /api/v1/user/login
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required**. email of user. |
| `password` | `string` | **Required**. password of user. |

#### Creates new user

```http
  POST /api/v1/user/new
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required**. email of the user. |
| `firstName` | `string` | **Required**. first name of the user. |
| `lastName` | `string` | **Required**. last name of the user. |
| `password` | `string` | **Required**. password of user. |

#### Displays the Orders created by user with paging

```http
  GET /api/v1/user/order
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `page` | `number` | **Required**. page index of orders |
| `size` | `number` | **Required**. page window size of the orders. |

### Book Controller

#### Getting single book with id

```http
  GET /api/v1/book/{id}
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. id of the book. |

#### Listing all books

```http
  GET /api/v1/book/all
```

#### Create single book

```http
  POST /api/v1/book/new
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | **Required**. name of the book. |
| `desc` | `string` | **Required**. Description of the book. |
| `price` | `number` | **Required**. price of the book. |
| `stock` | `number` | **Required**. available number of book. |

#### Updates single book

```http
  PUT /api/v1/book/update
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. id of the book. |
| `name` | `string` | **Required**. new name of the book. |
| `desc` | `string` | **Required**. new Description of the book. |
| `price` | `number` | **Required**. new price of the book. |
| `stock` | `number` | **Required**. new available number of book. |

### Order Controller

#### Gets single order with specific ID

```http
  GET /api/v1/order/{id}
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. id of order. |

#### Listing all orders of the user

```http
  GET /api/v1/order/all
```

#### List all order between given date

```http
  GET /api/v1/order/list
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `from` | `date` | **Required**. start date.(ISO formatted) |
| `to` | `date` | **Required**. end date.(ISO formatted) |

#### Creates new order

```http
  POST /api/v1/order/new
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `bookId` | `string` | **Required**. id of sold book |
| `quantity` | `number` | **Required**. number of book sold |

### Statistic Controller

#### Gets gets montly statistic

```http
  GET /api/v1/stat/monthly
```

## Test Coverage

![Logo](https://i.ibb.co/g6hNmvW/coverage.png)


  