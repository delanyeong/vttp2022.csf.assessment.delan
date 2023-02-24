package vttp2022.csf.assessment.server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2022.csf.assessment.server.models.Restaurant;
import vttp2022.csf.assessment.server.services.RestaurantService;

@Controller
@RequestMapping (path = "/api")
public class RestaurantController {
    
    @Autowired
    private RestaurantService resSvc;

    @GetMapping(path="/cuisines")
    public String getCuisines(@PathVariable String cuisine, Model model) {

        List<String> resByCui = resSvc.getRestaurantsByCuisine(cuisine);

        model.addAttribute("resByCui", resByCui);

        return "cuisine";
    }

    @GetMapping(path="/{cuisine}/restaurants")
    public String getRestaurantsByCuisine (@PathVariable String cuisine, Model model) {

        List<String> resByCui = resSvc.getRestaurantsByCuisine(cuisine);

        model.addAttribute("resByCui", resByCui);

        return "resByCui";
    }

    @GetMapping (path="/{restaurant}")
    public String getRestaurant (@PathVariable String res, Model model) {

        Optional<Restaurant> rest = resSvc.getRestaurant(res);

        Restaurant restt = rest.get();

        model.addAttribute("restt", restt);

        return "restaurant";
    }

}
