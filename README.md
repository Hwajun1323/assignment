# Running the Java 11 Docker Environment

This Docker environment contains a Java 11 runtime environment and is set up to build and run a Maven-based Java application. To use this environment, follow the steps below:

## Prerequisites

To use this environment, you need to have Docker installed on your system. You can download and install Docker from the official [Docker website](https://www.docker.com/get-started)

```bash
pip install foobar
```

## Build the Docker Image
To build the Docker image, navigate to the directory that contains the Dockerfile and run the following command:

```bash
docker build -t my-java-11-app .
```
This will build the Docker image and tag it with the name "my-java-11-app". The `.` at the end of the command indicates that the build context is the current directory.

## Run the Docker Container
To run the Docker container, use the following command:
```bash
docker run -p 8080:8080 my-java-11-app
```
This will start a Docker container using the "my-java-11-app" image and map port 8080 in the container to port 8080 on your local machine.

## Access the Running Application

Once the container is running, you can access the application by opening a web browser and navigating to [http://localhost:8080/](http://localhost:8080/).

If the application is running correctly, you should see the application's landing page or other output on the screen.

## Stopping the Docker Container
To stop the Docker container, press `CTRL+C` in the terminal window where the container is running. This will stop the container and return you to the command prompt.

## Removing the Docker Image
To remove the Docker image, use the following command:
```bash
docker rmi my-java-11-app
```
This will remove the "my-java-11-app" image from your system.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/25511860-90033aa6-a6cc-422a-9e3d-ddf1a2c0fcbf?action=collection%2Ffork&collection-url=entityId%3D25511860-90033aa6-a6cc-422a-9e3d-ddf1a2c0fcbf%26entityType%3Dcollection%26workspaceId%3D8f4253e3-76e6-47cd-862a-1f609aa2b9ba)
