package dev.cmjb.main.controller;


import dev.cmjb.main.exceptions.StorageFileNotFoundException;
import dev.cmjb.main.services.IStorageService;
import dev.cmjb.main.services.OCRService;
import dev.cmjb.main.services.UploadedFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

@Controller
public class FileUploadController {

    private final IStorageService storageService;

    @Autowired
    public FileUploadController(IStorageService storageService) {
        this.storageService = storageService;
        System.out.println(storageService.toString());
    }

    @PostMapping("/")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {
        Path location = storageService.store(file);

        File proc = new File("src/main/resources/tempFile.jpg");

        try (OutputStream os = new FileOutputStream(proc)) {
            os.write(file.getBytes());
        }

        OCRService ocr = new OCRService();
        String result = ocr.toString(proc);

        return ResponseEntity.ok(result);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
