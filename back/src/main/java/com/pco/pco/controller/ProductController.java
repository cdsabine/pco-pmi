package com.pco.pco.controller;

import com.pco.pco.entities.Product;
import com.pco.pco.entities.Tops;
import com.pco.pco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping(path="/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandController bc;
    @Autowired
    private TeamController tc;
    @Autowired
    private ClientOrderController coc;
    @Autowired
    private BoxController boxc;
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Product> getAllProducts() {
        // This returns a JSON or XML with the users
        return productRepository.findAll();
    }

    public Product decideBrand(Product p, String title){
        if(bc.findBrandNameInTitle(title).equals("N/A")){
            p.setBrand(bc.addNewBrand("Unknown Brand", "Undefined"));
        }
        else{
            p.setBrand(bc.findBrand(bc.findBrandNameInTitle(title)).get());
        }
        return p;
    }
    public Product decideTeam(Product p, String title){
        if(tc.findTeamNameInTitle(title).equals("N/A")){
            p.setTeam(tc.addNewTeam("Unknown Team", "Undefined"));
        }
        else{
            p.setTeam(tc.findTeam(tc.findTeamNameInTitle(title)).get());
        }
        return p;
    }
    public Product decideCoC(Product p, Long coCode){
        if(coCode == -1) {
            p.setClientOrder(null);
        }
        else{
            p.setClientOrder(coc.addNewClientOrder(coCode,"",false));
        }
        return p;
    }
    public Product decideBox(Product p, String SKU){
        String box = SKU.substring(SKU.indexOf(' ') + 1);
        box = box.replace(" ", "");

        if(!boxc.findBox(box).isPresent()){
            p.setBox(boxc.addNewBox(box));
        }
        else{
            p.setBox(boxc.findBox(box).get());
        }
        return p;
    }

    @PostMapping(path="/classifyProduct")
    public @ResponseBody Class classifyProduct(@RequestParam String productType) throws ClassNotFoundException {
        String entityName = "";
        String entityPath = "com.pco.pco.entities.";

        if(productType.equals("Jackets") || productType.contains("Jersey") || productType.contains("shirt") || productType.contains("tops") || productType.contains("Vest") || productType.contains("Hoodies")){
            entityName = entityPath+"Tops";
        }
        else if(productType.contains("Bib") || productType.contains("Shorts")){
            entityName = entityPath+"Bottoms";
        }
        else if(productType.contains("suit")){
            entityName = entityPath+"Skinsuit";
        }
        else if(productType.equals("Component")){
            entityName = entityPath+"Component";
        }
        else if(productType.equals("layer")){
            entityName = entityPath+"Baselayer";
        }
        else if(productType.equals("Warmer")){
            entityName = entityPath+"Warmer";
        }
        else if(productType.contains("Shoe") || productType.contains("Sock")){
            entityName = entityPath+"Footwear";
        }
        else if(productType.equals("Gloves")){
            entityName = entityPath+"Gloves";
        }
        else if(productType.equals("Helmet") || productType.contains("Cap")){
            entityName = entityPath+"Headwear";
        }
        else if(productType.equals("Healthnutrition")){
            entityName = entityPath+"Healthnutrtion";
        }
        return Class.forName(entityName);
    }

    @PostMapping(path="/returnObject")
    public @ResponseBody Object returnObject(@RequestParam String productType) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //Object aux = classifyProduct(productType).getDeclaredConstructor().newInstance();
        Product aux = (Product) classifyProduct(productType).getDeclaredConstructor().newInstance();

        return aux.getClass();
    }
}
