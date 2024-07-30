package com.pco.pco.controller.subclassproduct;

import com.pco.pco.controller.ProductController;
import com.pco.pco.entities.productchildren.Baselayers;
import com.pco.pco.repository.BaselayersRepository;
import com.pco.pco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/product/baselayers")
public class BaselayersController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BaselayersRepository baselayersRepository;
    @Autowired
    private ProductController productController;

    @PostMapping(path="/add")
    public @ResponseBody String addBaselayer (@RequestParam String SKU, @RequestParam String title, @RequestParam String brandName, @RequestParam String teamName, @RequestParam long coCode,
                                        @RequestParam double price, @RequestParam boolean activeProduct, @RequestParam String colour, @RequestParam String size, @RequestParam int quantity,
                                        @RequestParam String prodCondition, @RequestParam String vendorCode, @RequestParam String sleeves, @RequestParam boolean thermal, @RequestParam boolean merino) {

        Baselayers b = new Baselayers(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode, sleeves, thermal, merino);

        productController.decideBrand(b, brandName);
        productController.decideTeam(b, teamName);
        productController.decideCoC(b, coCode);

        productRepository.save(b);
        baselayersRepository.save(b);

        return "Saved";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Baselayers> getAllBaselayers() {
        // This returns a JSON or XML with the users
        return baselayersRepository.findAll();
    }
}
