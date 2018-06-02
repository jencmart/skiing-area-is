# Skiing Area Information System

```
      _    _ _                                     _____  _____ 
     | |  (_|_)               /\                  |_   _|/ ____|
  ___| | ___ _ _ __   __ _   /  \   _ __ ___  __ _  | | | (___  
 / __| |/ / | | '_ \ / _` | / /\ \ | '__/ _ \/ _` | | |  \___ \ 
 \__ \   <| | | | | | (_| |/ ____ \| | |  __/ (_| |_| |_ ____) |
 |___/_|\_\_|_|_| |_|\__, /_/    \_\_|  \___|\__,_|_____|_____/ 
                      __/ |                                     
    by Martin Jenc   |___/                                     
```
Software engineering class team project.
Modeling and documentation team work.
Implemented by myself.
 
## Getting Started
The project has been written in Spring framework with MySQL database. 

With Maven Jetty plugin it can be easily deployed.

### Prerequisites
*   MySQL
*   Maven


### Installing

1. Create database

2. Edit DB connection properties in application.properties accordingly

3. Go to project root folder 

```
mvn clean install
```
```
mvn jetty:run-war
```
Done! project is deployed to localhost:8080

## Usage
Project is far from complete. Only these Use Cases were implemented.
    
    * Sample Customer account - username: user passwd: user
    * Sample Employee account - username: admin passwd: admin


* As customer you can
    *   UC1 - register and log in.
    *   UC2 - anonymously (or logged in) order skipass from e-shop. Basic shopping cart is implemented. No payment or delivery options. (You will get order number with which you'll pay and pick up your order at box office.)

* As employee you can
    *   UC3 - add/remove/edit skipass to e-shop.
    *   UC4 - add/remove/edit RFID cards.
    *   UC5 - mark orders as payed.
    *   UC6 - assign RFID cards to the e-shop orders and lend them to the customers.
    *   UC7 - receive returned RFID cards.
    *   UC8 - create new order.
    *   UC9 - add new employees.
    *   UC10 - watch some base statistics...

## Built With

* [Spring](https://spring.io/) - The web framework used
* [Hibernate](https://hibernate.org/) - ORM
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Ema Holinska**      - *Modelling*
* **Martin Jenc**       - *Modelling*, *Coding*
* **Martin Kupka**      - *Modelling*
* **Irina Sushkova**    - *Modelling*
* **Marek Moucek**      - *Modelling*, *Documentation*

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
