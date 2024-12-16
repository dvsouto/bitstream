FROM gradle:8.11.1-jdk17-alpine AS builder
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean
RUN gradle installDist

FROM openjdk:17-oracle as runner
RUN microdnf install findutils
COPY --from=builder /home/gradle/src/build/install/src/ /app/
WORKDIR /app

CMD bin/src