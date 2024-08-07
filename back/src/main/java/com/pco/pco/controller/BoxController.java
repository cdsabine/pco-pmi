package com.pco.pco.controller;

import com.pco.pco.entities.AppUser;
import com.pco.pco.entities.Box;
import com.pco.pco.entities.Product;
import com.pco.pco.repository.BoxRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/box")
public class BoxController {
    @Autowired
    private BoxRepository boxRepository;
    @GetMapping(path="/all")
    public @ResponseBody List<Box> getAllBoxes() {
        return (List<Box>) boxRepository.findAll();
    }

    @PostMapping(path="/findBox")
    public @ResponseBody Box findBox (@RequestParam String boxNumber) {
        Iterable<Box> boxes = getAllBoxes();
        for(Box aux : boxes){
            if(aux.getBoxNumber().equals(boxNumber)) {
                aux.addToQuantityDraft();
                aux.addToQuantityTotal();
                boxRepository.save(aux);
                return boxRepository.findById(boxNumber).get();
            }
        }
        Box b = new Box();
        b.setBoxNumber(boxNumber);
        b.addToQuantityDraft();
        b.addToQuantityTotal();
        boxRepository.save(b);
        return b;
    }

    @PostMapping(path="/add")
    public @ResponseBody Box addNewBox (@RequestParam String boxNumber) {
        Box b = new Box();
        b.setBoxNumber(boxNumber);
        boxRepository.save(b);
        return b;
    }

    public Box addProductToBox(Box b, Product p){
        b.addToQuantityTotal();
        if(p.getActiveProduct()){
            b.addToQuantityActive();
        }
        else b.addToQuantityDraft();
        return boxRepository.save(b);
    }

    public Box removeProductFromBox(Box b, Product p){
        b.removeFromQuantityTotal();
        if(p.getActiveProduct()){
            b.removeFromQuantityActive();
        }
        else b.removeFromQuantityDraft();
        return boxRepository.save(b);
    }
}
