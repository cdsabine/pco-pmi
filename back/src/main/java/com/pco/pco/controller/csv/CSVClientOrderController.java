package com.pco.pco.controller.csv;

import com.pco.pco.entities.ClientOrder;
import com.pco.pco.helper.CSVHelperProducts;
import com.pco.pco.repository.ClientOrderRepository;
import com.pco.pco.service.CSVServiceClientOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/csvClientOrder")
public class CSVClientOrderController {
    @Autowired
    CSVServiceClientOrder csvServiceClientOrder;
    @Autowired
    ClientOrderRepository clientOrderRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (CSVHelperProducts.hasCSVFormat(file)) {
            try {
                csvServiceClientOrder.save(file);
                return ResponseEntity.status(HttpStatus.OK).body("File uploaded");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload file");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Upload CSV");
    }

    @GetMapping(path="/all")
    public @ResponseBody List<ClientOrder> getAllCOs() {
        return (List<ClientOrder>) clientOrderRepository.findAll();
    }
}
