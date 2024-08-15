package com.pco.pco.controller.csv;

import com.pco.pco.helper.CSVHelperAppUser;
import com.pco.pco.service.CSVServiceAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/csvAppUser")
public class CSVAppUserController {
    @Autowired
    CSVServiceAppUser csvServiceAppUser;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (CSVHelperAppUser.hasCSVFormat(file)) {
            try {
                csvServiceAppUser.save(file);
                return ResponseEntity.status(HttpStatus.OK).body("File uploaded");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload file");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Upload CSV");
    }
}
