package sg.edu.nus.iss.day28airbnb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.day28airbnb.model.Airbnb;
import sg.edu.nus.iss.day28airbnb.service.AirbnbService;

@Controller
@RequestMapping
public class AirbnbController {
    @Autowired
    private AirbnbService airSvc;

    @GetMapping("/")
    public String searchForm(Model model){
        model.addAttribute("title", "Search for something");
        return "search";
    }

    @GetMapping("/search")
    public String searchListingsByText(@RequestParam("q") String text, Model model) {
      List<Airbnb> results = airSvc.findByText(text);
      model.addAttribute("title", "Search Results");
      model.addAttribute("results", results);
      return "results";
    }
}
