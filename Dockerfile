# syntax=docker/dockerfile:1
# which " offical java image" ?
FROM openjdk:oraclelinux8

# working directory
WORKDIR /app

#copy from your host( pc, laptop)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# run this inside the image
RUN ./mvnw dependency:go-offline
COPY src ./src

# run inside container
CMD ["mvnw", "spring-boot:run"]

#   docker run -dp 5410:5432
#   --name some-prostgres-releas-copy1
#   -e POSTGRES_PASSWORD=1234
#   --mount source=my-name-volume,target=/var/lib/postgresql/data
#   postgres:latest