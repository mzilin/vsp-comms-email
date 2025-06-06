# Video Streaming Platform – Email Service

![Build](https://img.shields.io/github/actions/workflow/status/mzilin/vsp-comms-email/build.yml?label=Build&logo=github&logoColor=white&style=flat)
![Coverage](https://img.shields.io/codecov/c/github/mzilin/vsp-comms-email?label=Coverage&logo=codecov&logoColor=white&style=flat)
![Status](https://img.shields.io/badge/status-in_progress-yellow?label=Status)


This repository contains the **Email Service** microservice for the **Video Streaming Platform**, deployed in the **Comms** cluster. It is responsible for sending transactional and marketing emails that keep users engaged and informed.

For a complete system overview and links to all microservices, please refer to the [Microservices Hub Repository](https://github.com/mzilin/vsp-microservices-hub).


## Table of Contents

* [Introduction](#introduction)
* [Technology Stack](#technology-stack)
* [Dependencies](#dependencies)
* [Setting Up Your Environment](#setting-up-your-environment)
  * [Prerequisites](#prerequisites)
  * [Installation & Running](#installation--running)
  * [Environment Variables](#environment-variables)
* [Testing](#testing)
* [Endpoints](#endpoints)
* [CI/CD & Deployment](#cicd--deployment)
* [Future Improvements](#future-improvements)
* [License](#license)
* [Contact](#contact)


## Introduction

The **Email Service** is a core component of the **Video Streaming Platform**. It manages the sending of transactional emails (such as account confirmations, password resets, and notifications) as well as marketing communications. It integrates with Amazon SES to ensure reliable and scalable email delivery. Key responsibilities include:

- **Transactional Emails**: Sends critical user emails like account activation, password resets and important notifications.
- **Marketing Emails**: Delivers promotional and engagement-focused content to users, supporting marketing campaigns and retention efforts.
- **Template Management**: Utilises Thymeleaf to generate dynamic, personalised email templates.

By centralising email management, the **Email Service** ensures consistent communication across the platform, supporting both operational and marketing needs.


## Technology Stack

This service is built using a modern, cloud-native Java stack, optimised for reactive, scalable microservices:

- **Java** `21`: LTS version with enhanced performance and modern language features.
- **Spring Boot** `3.4.5`: Rapid development framework for standalone, production-ready Java apps.
- **Spring Cloud** `2024.0.0`: Provides essential microservice components like config management, service discovery and API routing.
- **Gradle** `8.14`: Powerful build tool with fast incremental builds and powerful dependency management.
- **Docker**: Containerises apps for consistent, portable development and deployment.


## Dependencies

This project relies on a set of key libraries and frameworks that support its core functionality, infrastructure and testing:

- **Spring Boot**
  - **Actuator**: Exposes app health, metrics, and monitoring endpoints.
  - **RabbitMQ (AMQP)**: Facilitates robust, asynchronous messaging between microservices, enhancing scalability and decoupling.
  - **Validation**: Provides declarative validation using JSR-380 annotations.
  - **Web**: Supports building RESTful endpoints and traditional MVC-based web applications.
  - **Thymeleaf**: Enables dynamic, template-based rendering for HTML emails and views.

- **Spring Cloud**
  - **Config Client**: Integrates with a centralised Spring Cloud Config Server for dynamic configuration management.
  - **Netflix Eureka Client**: Integrates with the Eureka Server for service registration and discovery.

- **AWS**
  - **AWS Java SDK SES**: Provides integration with Amazon SES for sending emails using AWS's secure and scalable infrastructure.

- **Developer Experience**
  - **Lombok**: Reduces boilerplate with annotations for getters, setters, and constructors.
  - **DevTools**: Enables hot reloading for faster development iterations.

- **Testing**
  - **JUnit**: Framework for writing and running unit and integration tests in Java.
  - **Mockito**: Allows mocking and stubbing dependencies to isolate components during testing.

- **Code Quality**
  - **Jacoco**: Generates code coverage reports for ensuring test completeness.


## Setting Up Your Environment

Follow the steps below to set up your local development environment and run the application.


### Prerequisites

Ensure you have the following installed on your machine:
- [Java JDK 21](https://www.oracle.com/uk/java/technologies/downloads/#java21)
- [Gradle 8.14](https://gradle.org/)
- [Docker](https://docs.docker.com/get-started/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/)


### Installation & Running

1. Clone the repository:
    ```bash
    git clone https://github.com/mzilin/vsp-comms-email.git
    ```

2. Switch to the `main` branch:
    ```bash
    cd vsp-comms-email
    ```

3. Build the project and run tests:
    ```bash
    ./gradlew clean build
    ```

4. Start the service:
    ```bash
    ./gradlew bootRun
    ```

   The service will start on http://localhost:8160 using the embedded Tomcat web server.


### Environment Variables

This microservice requires the following environment variable to be configured:

TBC


## Testing

This project uses a combination of **unit tests** and **integration tests** to ensure reliability, correctness and maintainability.

To execute all tests, run:
```bash
./gradlew test
```

This setup ensures that changes can be safely verified and that the codebase remains robust, maintainable and well-documented.


## Endpoints

TBC


## CI/CD & Deployment

This project uses **GitHub Actions** to automate the build, test and deployment processes, ensuring continuous integration and delivery.

- The **Build** workflow runs on every push and pull request to the `main` and `prod` branches. This step verifies that all commits compile successfully and pass unit tests before they are merged, maintaining code quality.
- The **Codecov** workflow runs on pushes to the main branch, where it executes tests, generates a Jacoco coverage report and uploads the results to Codecov.
- Finally, a **Deploy** workflow is triggered when a pull request is merged into the `prod` branch. It builds a Docker image of the microservice, pushes it to the **AWS ECR** container registry and deploys it to the **AWS ECS** environment.

Branch protections and **GitHub Secrets** are used to manage credentials and safeguard the CI/CD pipelines.


## Future Improvements

TBC


## License

This project is private and proprietary. Unauthorised copying, modification, distribution or use of this software, via any medium, is strictly prohibited without explicit written permission from the owner.


## Contact

For any questions or clarifications about the project, please [reach out](https://www.mariuszilinskas.com/contact) to the project owner.


------
###### © 2024–present Marius Zilinskas. All rights reserved.