package com.pco.pco.controller;

import com.pco.pco.entities.Product;
import com.pco.pco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Product> getAllUsers() {
        // This returns a JSON or XML with the users
        return productRepository.findAll();
    }

}
