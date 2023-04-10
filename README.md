# SpringBootTesseractMicroService [![Docker](https://github.com/cmjb/SpringBootTesseractMicroService/actions/workflows/docker-publish.yml/badge.svg)](https://github.com/cmjb/SpringBootTesseractMicroService/actions/workflows/docker-publish.yml) [![Java CI with Gradle](https://github.com/cmjb/SpringBootTesseractMicroService/actions/workflows/gradle.yml/badge.svg)](https://github.com/cmjb/SpringBootTesseractMicroService/actions/workflows/gradle.yml)

This is a microservice running on Spring Boot. This microservice uses the Tesseract OCR (Optical Character Recognition) application to scan images and extract text from them.

The purpose of this project is to give you access to Tesseract's fast english engine scanning capabilities in a small microservice to call on.

## Running the microservice

You can run it via docker by running:

```
docker pull ghcr.io/cmjb/springboottesseractmicroservice:sha256-ab91d45527de9dc436f14911831c82231f46d8c693b1418260c5f0df88ef1f06.sig
docker run -p 8080 ghcr.io/cmjb/springboottesseractmicroservice 
```

The microservice should be accessible via `http://localhost:8080/` once you've done that.

## Example code

The following code is some example code written in Java that uses the `Apache HttpClient` maven package.


```
   File file = new File(); // Replace this with the image file that you'll want to scan.
   
   HttpEntity entity = MultipartEntityBuilder.create()
            .addPart("file", new FileBody(File))
            .build();

    HttpPost request = new HttpPost("http://localhost:8080/"); // Or the address of the microservice
    request.setEntity(entity);

    HttpClient client = HttpClientBuilder.create().build();
    ClassicHttpResponse response = (ClassicHttpResponse) client.execute(request);
    HttpEntity entityResponse = response.getEntity();
    if(entityResponse != null) {
        String responseString = EntityUtils.toString(entityResponse, "UTF-8");
        System.out.println(responseString);
    }
```
