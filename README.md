# Automation Testing Framwork

The testing framework for both API and UI automation


## Table of Contents
> * [Prerequisites](#Prerequisites)
> * [Technical Stacks](#Technical-Stacks)
> * [Plugins](#Plugins)
> * [Usage](#Usage)
> * [Contributing](#Contributing)


### Prerequisites

  - [OpenJDK 1.8.0]+
  - [Apache Maven 3.5.4]+


### Technical Stacks

Automation for Sample Project uses a number of open source projects to work properly:

  * [Spring Boot]   - The generic and annotation-driven units of Spring framework
  * [Cucumber-JVM]  - Behaviour-Driven Development (BDD)
  * [Rest-Assured]  - RestFUL webservices testing library


### Plugins

Using with IntelliJ following below plugins:

  | Plugin | Detail |
  | ------ | ------ |
  | Cucumber for Java | [jetbrains.com/plugin/cucumber-for-java][PlgJava] |
  | Lombok | [jetbrains.com/plugin/lombok][PlgLombok] |


### Usage

  1. Clone this project
  2. Navigate to **test** folder and run cli
  ```sh
  mvn clean test
  ```


### Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
  1. Fork the Project
  2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
  3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
  4. Push to the Branch (`git push origin feature/AmazingFeature`)
  5. Open a Pull Request

<!--- LINK REFERENCES --->
[OpenJDK 1.8.0]: <https://adoptopenjdk.net/archive.html>
[Apache Maven 3.5.4]: <https://maven.apache.org/docs/3.5.4/release-notes.html>
[Cucumber-JVM]: <https://cucumber.io/docs/installation/>
[Spring Boot]: <https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/testing.html#testcontext-framework>
[Rest-Assured]: <http://rest-assured.io/>
[PlgJava]: <https://plugins.jetbrains.com/plugin/7212-cucumber-for-java>
[PlgLombok]: <https://plugins.jetbrains.com/plugin/6317-lombok>
