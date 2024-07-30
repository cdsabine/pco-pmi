package com.pco.pco.controller.subclassproduct;

import com.pco.pco.controller.ProductController;
import com.pco.pco.entities.productchildren.Components;
import com.pco.pco.repository.ComponentsRepository;
import com.pco.pco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/product/components")
public class ComponentsController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ComponentsRepository componentsRepository;
    @Autowired
    private ProductController productController;

    @PostMapping(path="/add")
    public @ResponseBody String addComponents (@RequestParam String SKU, @RequestParam String title, @RequestParam String brandName, @RequestParam String teamName, @RequestParam long coCode,
                                              @RequestParam double price, @RequestParam boolean activeProduct, @RequestParam String colour, @RequestParam String size, @RequestParam int quantity,
                                              @RequestParam String prodCondition, @RequestParam String vendorCode) {

        Components c = new Components(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode);

        productController.decideBrand(c, brandName);
        productController.decideTeam(c, teamName);
        productController.decideCoC(c, coCode);

        productRepository.save(c);
        componentsRepository.save(c);

        return "Saved";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Components> getAllComponents() {
        // This returns a JSON or XML with the users
        return componentsRepository.findAll();
    }
}
