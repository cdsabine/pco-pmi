package com.pco.pco.controller.subclassappuser;

import com.pco.pco.controller.AppUserController;
import com.pco.pco.controller.TeamController;
import com.pco.pco.entities.Team;
import com.pco.pco.entities.Vendor;
import com.pco.pco.repository.AppUserRepository;
import com.pco.pco.repository.TeamRepository;
import com.pco.pco.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user/vendor")
public class VendorController {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private AppUserController auc;
    @Autowired
    private TeamController tc;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewVendor (@RequestParam String vendorCode, @RequestParam String teamName,
                                              @RequestParam String name, @RequestParam String emailAddress, @RequestParam String address, @RequestParam String countryName) {
        Vendor v = new Vendor(name, emailAddress, address, countryName, vendorCode);
        Team t = new Team();
        auc.decideCountry(v, countryName);

        if(tc.findTeamNameInTitle(teamName).equals("N/A")){
            v.setTeams(tc.addNewTeam(teamName, "Undefined"));
            tc.findTeam(tc.findTeamNameInTitle(teamName)).get().setVendors(v);
            t = tc.returnTeam(teamName);
        }
        else{
            v.setTeams(tc.findTeam(tc.findTeamNameInTitle(teamName)).get());
            tc.findTeam(tc.findTeamNameInTitle(teamName)).get().setVendors(v);
            t = tc.returnTeam(teamName);
        }

        teamRepository.save(t);

        appUserRepository.save(v);
        vendorRepository.save(v);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Vendor> getAllVendors() {
        // This returns a JSON or XML with the users
        return vendorRepository.findAll();
    }

}
