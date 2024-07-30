package com.pco.pco.controller.subclassproduct;

import com.pco.pco.controller.ProductController;
import com.pco.pco.entities.productchildren.HealthNutrition;
import com.pco.pco.repository.HealthNutritionRepository;
import com.pco.pco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping(path="/product/healthNutrition")
public class HealthNutritionController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private HealthNutritionRepository healthNutritionRepository;
    @Autowired
    private ProductController productController;

    @PostMapping(path="/add")
    public @ResponseBody String addHealthNutrition (@RequestParam String SKU, @RequestParam String title, @RequestParam String brandName, @RequestParam String teamName, @RequestParam long coCode,
                                                    @RequestParam double price, @RequestParam boolean activeProduct, @RequestParam String colour, @RequestParam String size, @RequestParam int quantity,
                                                    @RequestParam String prodCondition, @RequestParam String vendorCode, @RequestParam LocalDate expirationDate, @RequestParam String flavour) {

        HealthNutrition h = new HealthNutrition(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode, expirationDate, flavour);

        productController.decideBrand(h, brandName);
        productController.decideTeam(h, teamName);
        productController.decideCoC(h, coCode);

        productRepository.save(h);
        healthNutritionRepository.save(h);

        return "Saved";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<HealthNutrition> getAllComponents() {
        // This returns a JSON or XML with the users
        return healthNutritionRepository.findAll();
    }
}
