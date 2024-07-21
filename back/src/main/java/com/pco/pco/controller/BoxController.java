package com.pco.pco.controller;

import com.pco.pco.entities.Box;
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
    public Optional<Box> findBox (@RequestParam String boxNumber) {
        int code = 0;
        Iterable<Box> boxes = getAllBoxes();
        for(Box aux : boxes){
            if(aux.getBoxNumber().equals(boxNumber)) {
                return boxRepository.findById(code);
            }
            else code++;
        }
        return boxRepository.findById(code);
    }

    @PostMapping(path="/add")
    public @ResponseBody Box addNewBox (@RequestParam String boxNumber) {
        Box b = new Box();
        b.setBoxNumber(boxNumber);
        boxRepository.save(b);
        return b;
    }
}
