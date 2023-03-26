package dev.cmjb.main.services;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OCRService {


    public OCRService() {
    }

    public String toString(File targetFile) {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/ocrdata");
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        try {
            return tesseract.doOCR(targetFile);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }
    }
}
