package com.pco.pco.controller.subclassproduct;

import com.pco.pco.controller.ProductController;
import com.pco.pco.entities.productchildren.Headwear;
import com.pco.pco.repository.HeadwearRepository;
import com.pco.pco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/product/headwear")
public class HeadwearController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private HeadwearRepository headwearRepository;
    @Autowired
    private ProductController productController;

    @PostMapping(path="/add")
    public @ResponseBody String addHeadwear (@RequestParam String SKU, @RequestParam String title, @RequestParam String brandName, @RequestParam String teamName, @RequestParam long coCode,
                                           @RequestParam double price, @RequestParam boolean activeProduct, @RequestParam String colour, @RequestParam String size, @RequestParam int quantity,
                                           @RequestParam String prodCondition, @RequestParam String vendorCode, @RequestParam boolean waterproof, @RequestParam boolean visor, @RequestParam boolean thermal) {

        Headwear h = new Headwear(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode, waterproof, visor, thermal);

        productController.decideBrand(h, brandName);
        productController.decideTeam(h, teamName);
        productController.decideCoC(h, coCode);

        productRepository.save(h);
        headwearRepository.save(h);

        return "Saved";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Headwear> getAllComponents() {
        // This returns a JSON or XML with the users
        return headwearRepository.findAll();
    }
}
