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

        String os = System.getProperty("os.name");

        // FIXME: Get data from Environment
        Tesseract tesseract = new Tesseract();
        if(os.startsWith("Windows")){
            tesseract.setDatapath("C:\\Tesseract");
        }else {
            tesseract.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata/");
        }

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
