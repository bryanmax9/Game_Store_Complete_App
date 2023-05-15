package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

// Controller for our front end
@Controller
public class WebController {
    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/")
    public String returnMain(){
        return "index";
    }

    @GetMapping("/checkout")
    public String returnCheckout(){
        return "checkout";
    }

    @GetMapping("/search")
    public String returnSearch(){
        return "search";
    }

    @GetMapping("/createGame")
    public String returnCreateGame(){
        return "createGame";
    }

    //Getting infrormation of the user through form
    @RequestMapping(value = "/saveGame", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute Game game){
        System.out.println("Game from UI =" + game);

        serviceLayer.saveGame(game);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("game_information");
        modelAndView.addObject("game",game);
        return modelAndView;
    }
}
