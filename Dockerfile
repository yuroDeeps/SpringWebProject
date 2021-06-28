From openjdk:11
copy ./target/crud-and-login-exercise-page-0.0.1-SNAPSHOT.jar crud-and-login-exercise-page-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","crud-and-login-exercise-page-0.0.1-SNAPSHOT.jar"]
