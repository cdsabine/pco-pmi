package com.pco.pco.controller;


import com.pco.pco.entities.Product;
import com.pco.pco.entities.Tops;
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
    private BrandController bc;
    @Autowired
    private TeamController tc;
    @Autowired
    private ClientOrderController coc;
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addTop (@RequestParam String SKU, @RequestParam String brandName, @RequestParam String teamName, @RequestParam long clientOrderCode,
                                        @RequestParam double price, @RequestParam boolean activeProduct, @RequestParam String colour,
                                        @RequestParam String sleeves, @RequestParam boolean thermal, @RequestParam boolean aero) {
        Tops t = new Tops();
        t.setSKU(SKU);
        t.setPrice(price);
        t.setActiveProduct(activeProduct);
        t.setColour(colour);
        t.setSleeves(sleeves);
        t.setThermal(thermal);
        t.setAero(aero);

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

        if(clientOrderCode == -1) {
            t.setClientOrder(null);
        }
        else{
            t.setClientOrder(coc.addNewClientOrder(clientOrderCode,"",false));
        }

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
