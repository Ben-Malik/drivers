# Drivers Tracking Java + Spring boot + Thymeleaf Web RestAPI App

This is aimed at keeping track of the drivers in the company and adding new ones when needed.

A driver record consists of the following information:
- A unique ID for the driver :Long
- First Name: String
- Last Name: String
- Date of Birth: Date
- Creation Date: Date

Details of drivers are stored in a simple flat file available at `resources/db/drivers.json` file.

## To get Tomcat and the App running.

1. Run $`git clone https://github.com/ben-malik/drivers`.
2. $ `cd drivers`
3. $ `git checkout develop`. The pr is to be merged to `master` branch.
4. $ `./mvnw clean install spring-boot:run`
5. Go to [localhost:8084](http://localhost:8084/)

## Endpoints

1. ### [/drivers](http://localhost:8084/drivers): GET ~ Return a list of drivers as json objects.
2. ### [/drivers/{created_after}](http://localhost:8084/drivers/2022-01-01): GET ~ returns all drivers created after the given date.
3. ### [/driver/create](http://localhost:8084/driver/create): POST ~ Creates a new driver.
