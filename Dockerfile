FROM openjdk:latest

WORKDIR /app
COPY . /app
RUN javac -d bin src/*.java
CMD ["java", "MainBank"]