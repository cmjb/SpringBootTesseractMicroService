FROM eclipse-temurin:19-jdk-jammy
MAINTAINER cmjb.dev

RUN apt update && DEBIAN_FRONTEND=noninteractive apt install -y tesseract-ocr tesseract-ocr-eng tesseract-ocr-osd

COPY . /tmp

WORKDIR /tmp

RUN ./gradlew build

RUN cp /tmp/build/libs/main-0.0.1-SNAPSHOT.jar /app.jar

RUN rm -rf /tmp/*

WORKDIR /

ENTRYPOINT ["java","-jar","/app.jar"]