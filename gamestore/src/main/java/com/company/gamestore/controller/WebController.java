package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        return "./MainPage/index";
    }

    @GetMapping("/checkout")
    public String returnCheckout(){
        return "./CheckoutPage/checkout";
    }

    @GetMapping("/admin")
    public String returnAdmin(){
        return "./AdminLogin/ADMIN";
    }

    @GetMapping("/createGame")
    public String returnCreateGame(){
        return "./Game/createGame";
    }

    @GetMapping("/createConsole")
    public String returnCreateConsole(){
        return "./Console/createConsole";
    }

    @GetMapping("/createTshirt")
    public String returnCreateTshirt(){
        return "./TShirt/createT-shirt";
    }


    @GetMapping("/GameCatalog")
    public String returnSearchGame(Model model){
        List<Game> games = serviceLayer.getAllGames(); //This is an example. Implement a method in your service layer that fetches all the games from your database.
        model.addAttribute("games", games);
        return "./Game/searchGame";
    }



    //Getting infrormation of the user through form
    @RequestMapping(value = "/saveGame", method = RequestMethod.POST)
    public ModelAndView saveGame(@ModelAttribute Game game){
        System.out.println("Game from UI =" + game);

        serviceLayer.saveGame(game);



        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("./Game/game_information");
        modelAndView.addObject("game",game);
        return modelAndView;
    }

    @GetMapping("/ConsoleCatalog")
    public String returnSearchConsole(Model model){
        List<Console> consoles = serviceLayer.getAllConsoles(); //This is an example. Implement a method in your service layer that fetches all the games from your database.
        model.addAttribute("consoles", consoles);
        return "./Console/searchConsole";
    }
    // *******8
    @RequestMapping(value = "/saveConsole", method = RequestMethod.POST)
    public ModelAndView saveConsole(@ModelAttribute Console console){
        System.out.println("Game from UI =" + console);

        serviceLayer.saveConsole(console);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("./Console/console_information");
        modelAndView.addObject("console",console);
        return modelAndView;
    }

    @GetMapping("/TshirtCatalog")
    public String returnSearchTShirt(Model model){
        List<Tshirt> tshirts = serviceLayer.getAllTshirts(); //This is an example. Implement a method in your service layer that fetches all the games from your database.
        model.addAttribute("tshirts", tshirts);
        return "./TShirt/searchTshirt";
    }
    // *******8
    @RequestMapping(value = "/saveTshirt", method = RequestMethod.POST)
    public ModelAndView saveTshirt(@ModelAttribute Tshirt tshirt){
        System.out.println("Game from UI =" + tshirt);

        serviceLayer.saveTshirt(tshirt);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("./TShirt/tshirt_information");
        modelAndView.addObject("tshirt",tshirt);
        return modelAndView;
    }


    //    Search for all itams (consoles, games, t-shirts)
    @GetMapping("/search")
    public String returnSearch(Model model){
        List<Console> consoles = serviceLayer.getAllConsoles();
        List<Game> games = serviceLayer.getAllGames();
        List<Tshirt> tShirts = serviceLayer.getAllTshirts();
        model.addAttribute("consoles", consoles);
        model.addAttribute("games", games);
        model.addAttribute("tshirts", tShirts);

        return "/SearchAllProducts/search";
    }

}
