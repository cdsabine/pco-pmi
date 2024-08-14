package com.pco.pco.controller;

import com.pco.pco.entities.Product;
import com.pco.pco.entities.Team;
import com.pco.pco.repository.ProductRepository;
import com.pco.pco.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/team")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ProductRepository productRepository;

    Map<String, String> teamCombos = new HashMap<>() {
        {
            put("Cycling Australia", "Australia");
            put("FDJ", "France");
            put("Bike Exchange", "England");
            put("Education First", "America");
            put("Novo Nordisk", "Norway");
            put("Ineos Grenadiers", "England");
            put("Trek Segafredo", "America");
            put("Israel Premier Tech", "Israel");
            put("Bora Hansgrohe", "Germany");
        }
    };

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody Team addNewTeam (@RequestParam String teamName, @RequestParam String nationality) {
        Team t = new Team();
        t.setTeamName(teamName);
        t.setNationality(nationality);
        teamRepository.save(t);
        return t;
    }

    @PostMapping(path="/initaliseTeams")
    public @ResponseBody Iterable<Team> initaliseTeams () {
        for(Map.Entry<String,String> entry : teamCombos.entrySet()){
            teamRepository.save(addNewTeam(entry.getKey(), entry.getValue()));
        }
        return teamRepository.findAll();
    }
    @GetMapping(path="/all")
    public @ResponseBody List<Team> getAllTeams() {
        return (List<Team>) teamRepository.findAll();
    }

    @GetMapping(path="/allProductsTeamCount")
    public @ResponseBody Map<String, Map<String, Integer>> getAllTeamProducts() {
        Map<String, Map<String, Integer>> teamCountMap = new HashMap<>();
        List<Team> auxTeam = (List<Team>) teamRepository.findAll();
        for(Team t : auxTeam){
            Map<String, Integer> productCountMap = new HashMap<>();
            List<Product> auxProduct = t.getProductList();
            for(Product p : auxProduct){
                String name = p.getClass().toString();
                String delimiter = "productchildren.";
                name = name.substring(name.indexOf("productchildren.") + delimiter.length());
                productCountMap.put(name, productCountMap.getOrDefault(name, 0) + 1);
            }
            teamCountMap.put(t.getTeamName(), productCountMap);
        }
        return teamCountMap;
    }
    @GetMapping(path="/allProductsTeamValue")
    public @ResponseBody Map<String, Double> getAllTeamProductValue() {
        Map<String, Double> teamTotalValueMap = new HashMap<>();
        List<Team> auxTeam = (List<Team>) teamRepository.findAll();
        for(Team t : auxTeam){
            double teamTotalValue = 0.0;
            List<Product> auxProduct = t.getProductList();
            for(Product p : auxProduct){
                teamTotalValue = teamTotalValue + p.getPrice();
            }
            teamTotalValueMap.put(t.getTeamName(), teamTotalValue);
        }
        return teamTotalValueMap;
    }


    @GetMapping(path="/findTeam")
    public @ResponseBody Team returnTeam(@RequestParam String teamName) {
        return findTeam(teamName).get();
    }

    public Optional<Team> findTeam (String teamName) {
        Long code = -1L;
        Iterable<Team> teams = getAllTeams();
        for (Team aux : teams) {
            if (aux.getTeamName().equals(teamName)) {
                code = aux.getTeamCode();
                return teamRepository.findById(code.intValue());
            }
        }
        return teamRepository.findById(code.intValue());
    }
    public String findTeamNameInTitle(String title){
        for(Map.Entry<String,String> entry : teamCombos.entrySet()){
            if(title.contains(entry.getKey())) return entry.getKey();
        }
        return "N/A";
    }
}
