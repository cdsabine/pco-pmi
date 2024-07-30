package com.pco.pco.controller.subclassproduct;

import com.pco.pco.controller.ProductController;
import com.pco.pco.entities.productchildren.Warmers;
import com.pco.pco.repository.ProductRepository;
import com.pco.pco.repository.WarmersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/product/warmers")
public class WarmersController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarmersRepository warmersRepository;
    @Autowired
    private ProductController productController;

    @PostMapping(path="/add")
    public @ResponseBody String addWarmers (@RequestParam String SKU, @RequestParam String title, @RequestParam String brandName, @RequestParam String teamName, @RequestParam long coCode,
                                              @RequestParam double price, @RequestParam boolean activeProduct, @RequestParam String colour, @RequestParam String size, @RequestParam int quantity,
                                              @RequestParam String prodCondition, @RequestParam String vendorCode, @RequestParam boolean waterproof, @RequestParam String bodypart,  @RequestParam boolean thermal) {

        Warmers w = new Warmers(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode, waterproof, bodypart, thermal);

        productController.decideBrand(w, brandName);
        productController.decideTeam(w, teamName);
        productController.decideCoC(w, coCode);

        productRepository.save(w);
        warmersRepository.save(w);

        return "Saved";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Warmers> getAllWarmers() {
        // This returns a JSON or XML with the users
        return warmersRepository.findAll();
    }
}
