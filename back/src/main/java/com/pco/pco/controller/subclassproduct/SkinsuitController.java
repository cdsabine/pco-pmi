package com.pco.pco.controller.subclassproduct;

import com.pco.pco.controller.ProductController;
import com.pco.pco.entities.Product;
import com.pco.pco.entities.productchildren.Skinsuits;
import com.pco.pco.entities.productchildren.Tops;
import com.pco.pco.repository.SkinsuitsRepository;
import com.pco.pco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path="/product/skinsuits")
public class SkinsuitController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SkinsuitsRepository skinsuitsRepository;
    @Autowired
    private ProductController productController;

    @PostMapping(path="/add")
    public @ResponseBody String addSkinsuits (@RequestParam String SKU, @RequestParam String title, @RequestParam String brandName, @RequestParam String teamName, @RequestParam long coCode,
                                             @RequestParam double price, @RequestParam boolean activeProduct, @RequestParam String colour, @RequestParam String size, @RequestParam int quantity,
                                             @RequestParam String prodCondition, @RequestParam String vendorCode, @RequestParam String sleeves, @RequestParam boolean thermal, @RequestParam boolean aero, @RequestParam boolean timetrial) {

        Skinsuits s = new Skinsuits(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode, sleeves, thermal, aero, timetrial);

        productController.decideBrand(s, brandName);
        productController.decideTeam(s, teamName);
        productController.decideCoC(s, coCode);

        productRepository.save(s);
        skinsuitsRepository.save(s);

        return "Saved";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Skinsuits> getAllSkinsuits() {
        // This returns a JSON or XML with the users
        return skinsuitsRepository.findAll();
    }
    private List<String> sleeves = Arrays.asList("long sleeve","short sleeve","sleeveless");
    public Product decideSleeves(Skinsuits s, String title){
        String aux = "No sleeves";
        for(String sleeve : sleeves){
            if (title.toLowerCase().contains(sleeve)) aux = sleeve;
        }
        s.setSleeves(aux);
        return s;
    }
}
