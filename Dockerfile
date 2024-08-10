FROM eclipse-temurin:17 AS builder

WORKDIR /online_marketplace

# Copy the Maven wrapper properties
COPY .mvn/ .mvn

# Copy the Pom file that contains information of project and configuration information for the maven
# to build the project such as dependencies, build directory, source directory, test source directory, plugin, goals etc.
COPY pom.xml ./
COPY mvnw ./

# Copy the Maven wrappers for both SO, so you can use whatnever you want to
COPY mvnw.cmd ./

# Run the instructions trigger to resolve all the project dependencies including plugins and reports.
RUN ./mvnw dependency:go-offline

# Next we copy the src directory in the pre-enviroment
COPY src ./src

CMD ["./mvnw", "spring-boot:run"]

