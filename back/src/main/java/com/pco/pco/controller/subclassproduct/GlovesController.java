package com.pco.pco.controller.subclassproduct;

import com.pco.pco.controller.ProductController;
import com.pco.pco.entities.productchildren.Gloves;
import com.pco.pco.repository.GlovesRepository;
import com.pco.pco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/product/gloves")
public class GlovesController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GlovesRepository glovesRepository;
    @Autowired
    private ProductController productController;

    @PostMapping(path="/add")
    public @ResponseBody String addGloves (@RequestParam String SKU, @RequestParam String title, @RequestParam String brandName, @RequestParam String teamName, @RequestParam long coCode,
                                             @RequestParam double price, @RequestParam boolean activeProduct, @RequestParam String colour, @RequestParam String size, @RequestParam int quantity,
                                             @RequestParam String prodCondition, @RequestParam String vendorCode, @RequestParam boolean waterproof, @RequestParam boolean thermal, @RequestParam boolean neoprene) {

        Gloves g = new Gloves(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode, waterproof, thermal, neoprene);

        productController.decideBrand(g, brandName);
        productController.decideTeam(g, teamName);
        productController.decideCoC(g, coCode);

        productRepository.save(g);
        glovesRepository.save(g);

        return "Saved";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Gloves> getAllComponents() {
        // This returns a JSON or XML with the users
        return glovesRepository.findAll();
    }
}
