package com.pco.pco.controller;

import com.pco.pco.entities.Box;
import com.pco.pco.entities.Product;
import com.pco.pco.entities.productLocationChanges;
import com.pco.pco.repository.BoxRepository;
import com.pco.pco.repository.ProductLocationChangeRepository;
import com.pco.pco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping(path="/pLocationChangeController")
public class pLocationChangeController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BoxRepository boxRepository;
    @Autowired
    private ProductLocationChangeRepository productLocationChangeRepository;
    @Autowired
    private BoxController boxController;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<productLocationChanges> getAllChanges() {
        return productLocationChangeRepository.findAll();
    }

    @PostMapping(path="/moveProductBox")
    public @ResponseBody productLocationChanges changeBox(@RequestParam String SKU, @RequestParam String newBoxNumber, @RequestParam String currentDate){
        Product p = productRepository.findById(SKU).get();

        Box oldBox = p.getBox();
        Box newBox = boxController.findBox(newBoxNumber);

        boxController.removeProductFromBox(oldBox, p);
        boxController.addProductToBox(newBox, p);

        p.setBox(newBox);
        //SKU = SKU.substring(0, SKU.indexOf(' ')) + " " + newBoxNumber;
        //p.setSKU(SKU);

        productRepository.save(p);

        LocalDate date = LocalDate.parse(currentDate);
        productLocationChanges plc = new productLocationChanges(oldBox.getBoxNumber(), date);
        plc.setBox(newBox);
        plc.setProduct(p);

        return productLocationChangeRepository.save(plc);
    }
}
