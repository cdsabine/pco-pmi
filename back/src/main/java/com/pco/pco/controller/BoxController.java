package com.pco.pco.controller;

import com.pco.pco.entities.Box;
import com.pco.pco.entities.Product;
import com.pco.pco.repository.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/box")
public class BoxController {
    @Autowired
    private BoxRepository boxRepository;
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Box> getAllBoxes() {
        return boxRepository.findAll();
    }

    @PostMapping(path="/findBox")
    public @ResponseBody Box findBox (@RequestParam String boxNumber) {
        Iterable<Box> boxes = getAllBoxes();
        for(Box aux : boxes){
            if(aux.getBoxNumber().equals(boxNumber)) {
                return boxRepository.findById(boxNumber).get();
            }
        }
        Box b = new Box(boxNumber, 0,0,0, false);
        boxRepository.save(b);
        return boxRepository.findById(boxNumber).get();
    }

    @PostMapping(path="/add")
    public @ResponseBody Box addNewBox (@RequestParam String boxNumber) {
        Box b = new Box();
        b.setBoxNumber(boxNumber);
        boxRepository.save(b);
        return b;
    }

    public Box addProductToBox(Box b, Product p){
        Box selectedBox = boxRepository.findById(b.getBoxNumber()).get();
        selectedBox.setProducts(p);
        selectedBox.addToQuantityDraft();
        selectedBox.addToQuantityTotal();

        boxRepository.save(selectedBox);

        return selectedBox;
    }
}
