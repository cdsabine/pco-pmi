package com.pco.pco.controller;

import com.pco.pco.entities.Brand;
import com.pco.pco.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/brand")
public class BrandController {
    @Autowired
    private BrandRepository brandRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody Brand addNewBrand (@RequestParam String brandName, @RequestParam String nationality) {
        Brand b = new Brand();
        b.setBrandName(brandName);
        b.setNationality(nationality);
        brandRepository.save(b);
        return b;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @GetMapping(path="/findBrand")
    public @ResponseBody Brand returnBrand(@RequestParam String brandName) {
        return findBrand(brandName).get();
    }

    public Optional<Brand> findBrand (String brandName) {
        Long code = -1L;
        Iterable<Brand> brands = getAllBrands();
        for(Brand aux : brands){
            if(aux.getBrandName().equals(brandName)) {
                code = aux.getBrandCode();
                return brandRepository.findById(code.intValue());
            }
        }
        return brandRepository.findById(code.intValue());
    }
}
