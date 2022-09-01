# TopJava graduate project [![Codacy Badge](https://app.codacy.com/project/badge/Grade/93c9f23df5b244059084d6d3bea9e398)](https://www.codacy.com/gh/YuriyPl/lunch-voting/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=YuriyPl/lunch-voting&amp;utm_campaign=Badge_Grade)

---

Java 17, Spring Boot 2.7 (web, security, data jpa, test, cache), Maven, JUnit Jupiter, H2, Lombok, Swagger/OpenAPI 3.0

---
## Technical requirement:

Design and implement a REST API using Hibernate/Spring/SpringMVC (Spring-Boot preferred!) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote on which restaurant they want to have lunch at
* Only one vote counted per user
* If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

---

You can access REST API documentation here: http://localhost:8080/swagger-ui/index.html

---

### Test credentials:

admin@gmail.com | admin123

user@yandex.ru | user11