package com.pco.pco.service;

import com.pco.pco.controller.ProductController;
import com.pco.pco.entities.Product;
import com.pco.pco.helper.CSVHelperProducts;
import com.pco.pco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVServiceProduct {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ProductController pc;

    public void save(MultipartFile file) {
        try {
            List<Product> products = CSVHelperProducts.csvToProduct(file.getInputStream());
            for(Product aux : products){
                pc.decideBrand(aux, aux.getTitle());
                pc.decideTeam(aux, aux.getTitle());
                pc.decideCoC(aux, -1L);
                pc.decideBox(aux, aux.getSKU());
                pc.decideColour(aux, aux.getTitle());
                productRepository.save(aux);
            }
            productRepository.saveAll(products);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
