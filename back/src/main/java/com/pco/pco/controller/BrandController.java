package com.pco.pco.controller;

import com.pco.pco.entities.Brand;
import com.pco.pco.entities.Team;
import com.pco.pco.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/brand")
public class BrandController {
    @Autowired
    private BrandRepository brandRepository;

    Map<String, String> brandCombos = new HashMap<>() {
        {
            put("Rapha", "United Kingdom");
            put("Castelli", "Italy");
            put("Oakley", "America");
            put("Santini", "Italy");
            put("GoRigoGo", "Australia");
            put("Shimano", "Japan");
            put("Assos", "Switzerland");
            put("Craft", "Sweden");
            put("Bioracer", "Belgium");
            put("Pactimo", "America");
            put("Jinga", "Portugal");
            put("Ekoi", "France");
            put("Clique", "Sweden");
            put("Dare 2B", "America");
        }
    };

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody Brand addNewBrand (@RequestParam String brandName, @RequestParam String nationality) {
        Brand b = new Brand();
        b.setBrandName(brandName);
        b.setNationality(nationality);
        brandRepository.save(b);
        return b;
    }
    @PostMapping(path="/initaliseBrands")
    public @ResponseBody Iterable<Brand> initaliseBrands () {
        for(Map.Entry<String,String> entry : brandCombos.entrySet()){
            brandRepository.save(addNewBrand(entry.getKey(), entry.getValue()));
        }
        return brandRepository.findAll();
    }

    @GetMapping(path="/all")
    public @ResponseBody List<Brand> getAllBrands() {
        return (List<Brand>) brandRepository.findAll();
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

    public String findBrandNameInTitle(String title){
        for(Map.Entry<String,String> entry : brandCombos.entrySet()){
            if(title.contains(entry.getKey())) return entry.getKey();
        }
        return "N/A";
    }
}
