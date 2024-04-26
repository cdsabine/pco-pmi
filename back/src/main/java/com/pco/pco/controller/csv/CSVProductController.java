package com.pco.pco.controller.csv;

import com.pco.pco.helper.CSVHelperAppUser;
import com.pco.pco.helper.CSVHelperProducts;
import com.pco.pco.service.CSVServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path="/csvProduct")
public class CSVProductController {
    @Autowired
    CSVServiceProduct csvServiceProduct;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String teamName) {
        if (CSVHelperProducts.hasCSVFormat(file)) {
            try {
                csvServiceProduct.save(file, teamName);
                return ResponseEntity.status(HttpStatus.OK).body("File uploaded");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload file");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Upload CSV");
    }
}
