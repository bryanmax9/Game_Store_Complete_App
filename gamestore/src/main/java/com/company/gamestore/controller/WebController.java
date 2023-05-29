package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @GetMapping("/admin")
    public String returnAdmin(){
        return "ADMIN";
    }

    @GetMapping("/createGame")
    public String returnCreateGame(){
        return "createGame";
    }

    @GetMapping("/createConsole")
    public String returnCreateConsole(){
        return "createConsole";
    }

    @GetMapping("/createTshirt")
    public String returnCreateTshirt(){
        return "createT-shirt";
    }


    @GetMapping("/searchGames")
    public String returnSearch(Model model){
        List<Game> games = serviceLayer.getAllGames(); //This is an example. Implement a method in your service layer that fetches all the games from your database.
        model.addAttribute("games", games);
        return "searchGame";
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
