# Inodevs - Back-End Spring
Repository for Back-end application in Spring for data persistence.

# Local Installation Guide

## Tools:

- [Java](https://www.java.com/pt-BR/download/ie_manual.jsp?locale=pt_BR) - It may be the last version, but we recommend 17;
- [Spring](https://start.spring.io/) (used only for the initial creation of the project);
- [Maven](https://maven.apache.org/download.cgi);
- [Mysql](https://dev.mysql.com/downloads/) - download the'MySQL Community Server';
- [VScode](https://code.visualstudio.com/download).

> **Important:** Place the maven and mysql bin folder in the environment variables.

- Also install the 'Extension Pack for Java' extension in VSCode.

## Local installation manual:

1. Clone github:
```console
git clone https://github.com/Inodevs-6/Inodevs-back-spring.git
```

2. Enter the cloned folder:
```console
cd Inodevs-back-spring
```

3. Create the database:
```console
mysql -u root -p < DDL.sql
```

4. Test the environment to check if everything is configured:
```console
mvn clean test
```

5. Run the application:
```console
mvn spring-boot:run
```
