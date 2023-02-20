# Start with a base image containing Java 11
FROM adoptopenjdk/openjdk11:jdk-11.0.12_7-alpine-slim

# Add the MongoDB configuration file to the container
COPY mongod.conf /etc/

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml file into the container at /app
COPY pom.xml /app

# Add MongoDB packages to apk repositories
RUN echo 'http://dl-cdn.alpinelinux.org/alpine/v3.6/main' >> /etc/apk/repositories
RUN echo 'http://dl-cdn.alpinelinux.org/alpine/v3.6/community' >> /etc/apk/repositories
RUN apk update

# Install Maven and MongoDB
RUN apk add --no-cache maven mongodb mongodb-tools

# Download project dependencies
RUN mvn dependency:go-offline

# Copy the project source code into the container at /app
COPY src /app/src

# Build the application and copy the JAR file to the /app directory
RUN mvn package -DskipTests

# Create the data directory for MongoDB
RUN mkdir -p /data/db

# Expose the default MongoDB port (27017)
EXPOSE 27017

# Expose the application port
EXPOSE 8080

# Run MongoDB and the application
CMD mongod --config /etc/mongod.conf & java -jar /app/target/assignment.jar
