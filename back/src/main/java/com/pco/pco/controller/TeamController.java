package com.pco.pco.controller;

import com.pco.pco.entities.Team;
import com.pco.pco.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path="/team")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

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
    public @ResponseBody Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
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
}
