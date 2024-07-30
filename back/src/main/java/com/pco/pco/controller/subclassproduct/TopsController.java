package com.pco.pco.controller.subclassproduct;


import com.pco.pco.controller.BrandController;
import com.pco.pco.controller.ClientOrderController;
import com.pco.pco.controller.ProductController;
import com.pco.pco.controller.TeamController;
import com.pco.pco.entities.productchildren.Tops;
import com.pco.pco.repository.ProductRepository;
import com.pco.pco.repository.TopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/product/tops")
public class TopsController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TopsRepository topsRepository;
    @Autowired
    private ProductController productController;
    @Autowired
    private BrandController bc;
    @Autowired
    private TeamController tc;
    @Autowired
    private ClientOrderController coc;
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addTop (@RequestParam String SKU, @RequestParam String title, @RequestParam String brandName, @RequestParam String teamName, @RequestParam long coCode,
                                        @RequestParam double price, @RequestParam boolean activeProduct, @RequestParam String colour, @RequestParam String size, @RequestParam int quantity,
                                        @RequestParam String prodCondition, @RequestParam String vendorCode, @RequestParam String sleeves, @RequestParam boolean thermal, @RequestParam boolean aero) {

        Tops t = new Tops(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode, sleeves, thermal,aero);

        productController.decideBrand(t, brandName);
        productController.decideTeam(t, teamName);
        productController.decideCoC(t, coCode);

        /*
        if(!bc.findBrand(brandName).isPresent()){
            t.setBrand(bc.addNewBrand(brandName, "Undefined"));
        }
        else{
            t.setBrand(bc.findBrand(brandName).get());
        }
       if(!tc.findTeam(teamName).isPresent()){
            t.setTeam(tc.addNewTeam(teamName, "Undefined"));
        }
        else{
            t.setTeam(tc.findTeam(teamName).get());
        }
        if(coCode == -1) {
            t.setClientOrder(null);
        }
        else{
            t.setClientOrder(coc.addNewClientOrder(coCode,"",false));
        }
        */

        productRepository.save(t);
        topsRepository.save(t);

        return "Saved";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Tops> getAllUsers() {
        // This returns a JSON or XML with the users
        return topsRepository.findAll();
    }
}
