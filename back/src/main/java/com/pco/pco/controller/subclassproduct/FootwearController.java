package com.pco.pco.controller.subclassproduct;

import com.pco.pco.controller.ProductController;
import com.pco.pco.entities.productchildren.Footwear;
import com.pco.pco.repository.FootwearRepository;
import com.pco.pco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/product/footwear")
public class FootwearController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FootwearRepository footwearRepository;
    @Autowired
    private ProductController productController;

    @PostMapping(path="/add")
    public @ResponseBody String addFootwear (@RequestParam String SKU, @RequestParam String title, @RequestParam String brandName, @RequestParam String teamName, @RequestParam long coCode,
                                               @RequestParam double price, @RequestParam boolean activeProduct, @RequestParam String colour, @RequestParam String size, @RequestParam int quantity,
                                               @RequestParam String prodCondition, @RequestParam String vendorCode, @RequestParam String discipline) {

        Footwear f = new Footwear(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode, discipline);

        productController.decideBrand(f, brandName);
        productController.decideTeam(f, teamName);
        productController.decideCoC(f, coCode);

        productRepository.save(f);
        footwearRepository.save(f);

        return "Saved";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Footwear> getAllComponents() {
        // This returns a JSON or XML with the users
        return footwearRepository.findAll();
    }
}
