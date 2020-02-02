# TestCrowd

This web application serves as a platform for cooperative testing.

Companies can create some tests here and let users work on them.

Users can perform tests according to test scenarios and then enter test results.

As a result of these tests, users will be paid in the form of credits earned by the company for the test on their account.

There are also reviews of users, tests and companies.

## Installation & run

- Start Docker

- Set shared drive for Docker Hyper-V
  - go to Docker > Settings > Shared Drives
  - check drive with your project files

- Build Maven project (Execute Maven Goal)
    - With tests
        ```
        mvn package
        ```
    - Without tests
       ```
       mvn -Dmaven.test.skip=true package
       ```

- In terminal run
```
docker-compose up -d --build
```

- Go to [localhost:8080](http://localhost:8080/)

- Live demo at [testcrowd-app.westeurope.cloudapp.azure.com](http://testcrowd-app.westeurope.cloudapp.azure.com:8080/)

## Authors
- Bc. Tomáš Novák (tomas.novak.3@uhk.cz)
- Bc. Libor Plíšek (libor.plisek@uhk.cz)

## License
[The Unlicense](LICENSE)
