#!/bin/bash

# Update package index
apt-get update

# Install Java (OpenJDK 11)
apt-get install -y openjdk-11-jdk

# Install Maven
apt-get install -y maven

# Verify installations
java -version
mvn -version