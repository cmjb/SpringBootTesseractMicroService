FROM eclipse-temurin:19-jdk-jammy
MAINTAINER cmjb.dev

RUN apt update && DEBIAN_FRONTEND=noninteractive apt install -y tesseract-ocr tesseract-ocr-eng tesseract-ocr-osd

COPY ./build/libs/main-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]