package com.pco.pco.controller.subclassproduct;

import com.pco.pco.controller.BrandController;
import com.pco.pco.controller.ClientOrderController;
import com.pco.pco.controller.TeamController;
import com.pco.pco.entities.Bottoms;
import com.pco.pco.repository.ProductRepository;
import com.pco.pco.repository.BottomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/product/bottoms")
public class BottomsController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BottomsRepository bottomsRepository;
    @Autowired
    private BrandController bc;
    @Autowired
    private TeamController tc;
    @Autowired
    private ClientOrderController coc;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addBottom (@RequestParam String SKU, @RequestParam String title, @RequestParam String brandName, @RequestParam String teamName, @RequestParam long coCode,
                                        @RequestParam double price, @RequestParam boolean activeProduct, @RequestParam String colour, @RequestParam String size,
                                        @RequestParam boolean chamois, @RequestParam boolean thermal, @RequestParam boolean aero) {
        Bottoms b = new Bottoms();
        b.setSKU(SKU);
        b.setTitle(title);
        b.setPrice(price);
        b.setActiveProduct(activeProduct);
        b.setColour(colour);
        b.setSize(size);
        b.setChamois(chamois);
        b.setThermal(thermal);
        b.setAero(aero);

        if(!bc.findBrand(brandName).isPresent()){
            b.setBrand(bc.addNewBrand(brandName, "Undefined"));
        }
        else{
            b.setBrand(bc.findBrand(brandName).get());
        }

        if(!tc.findTeam(teamName).isPresent()){
            b.setTeam(tc.addNewTeam(teamName, "Undefined"));
        }
        else{
            b.setTeam(tc.findTeam(teamName).get());
        }

        if(coCode == -1) {
            b.setClientOrder(null);
        }
        else{
            b.setClientOrder(coc.addNewClientOrder(coCode,"",false));
        }

        productRepository.save(b);
        bottomsRepository.save(b);

        return "Saved";
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Bottoms> getAllUsers() {
        // This returns a JSON or XML with the users
        return bottomsRepository.findAll();
    }
}
