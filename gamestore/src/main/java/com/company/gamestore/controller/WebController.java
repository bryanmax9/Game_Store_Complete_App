package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Imports needed for GET requests that need to check JWT authentication
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;


// Controller for our front end
@Controller
public class WebController {
    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/")
    public String returnMain(){
        return "./MainPage/index";
    }

    @GetMapping("/About")
    public String returnAbout(){
        return "./AboutPage/about";
    }

    @GetMapping("/checkout/{type}/{id}")
    public String returnCheckout(@PathVariable("type") String type, @PathVariable("id") int id, Model model){
        // Based on the type, call the correct service to get the item
        Object item;
        String itemDetail = ""; // This will hold the detail (title, model, or size)
        String itemType = ""; // Item category of Console, game or T-Shirt
        String imageUrl = ""; // This will hold the imageUrl
        String description = ""; //Hold description
        BigDecimal price = BigDecimal.ZERO;
        String urlTrailerID = "";
        switch (type) {
            case "game":
                Game game = serviceLayer.findGame(id);
                item = game;
                itemDetail = game.getTitle();
                imageUrl = game.getImageUrl();
                itemType = "Game";
                description = game.getDescription();
                price = game.getPrice();
                urlTrailerID = game.getYoutubeId();
                model.addAttribute("quantity", game.getQuantity()); // add this line
                
                break;
            case "console":
                Console console = serviceLayer.findConsole(id);
                item = console;
                itemDetail = console.getModel();
                imageUrl = console.getImageUrl();
                itemType = "Console";
                description = console.getProcessor();
                price = console.getPrice();
                urlTrailerID = console.getYoutubeId();
                model.addAttribute("quantity", console.getQuantity()); // add this line
                break;
            case "tshirt":
                Tshirt tshirt = serviceLayer.findTshirt(id);
                item = tshirt;
                itemDetail = tshirt.getColor();
                imageUrl = "https://i.imgur.com/IMKWUEB.png";
                itemType = "T-Shirt";
                description = tshirt.getDescription();
                price = tshirt.getPrice();
                urlTrailerID = tshirt.getYoutubeId();
                model.addAttribute("quantity", tshirt.getQuantity()); // add this line
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }

        model.addAttribute("item", item);
        model.addAttribute("itemId", id);
        model.addAttribute("itemType", itemType);
        model.addAttribute("itemDetail", itemDetail);
        model.addAttribute("imageUrl", imageUrl);
        model.addAttribute("description", description);
        model.addAttribute("price", price);
        model.addAttribute("urlTrailerID", urlTrailerID);

        System.out.println(model);

        return "./CheckoutPage/checkout";
    }



    @GetMapping("/admin")
    public String returnAdmin(){
        return "./AdminLogin/ADMIN";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/dashboard")
    public ModelAndView returnAdminDashboard(HttpServletRequest request, ModelAndView modelAndView) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) request.getSession().getAttribute("token"); // Retrieve the token from the session

        // Use the token as needed
        modelAndView.setViewName("./AdminLogin/AdminDashboard"); // Your view name
        modelAndView.addObject("token", token); // Add the token to the model and view

        return modelAndView;
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

    @GetMapping("/ConsoleCatalog")
    public String returnSearchConsole(Model model){
        List<Console> consoles = serviceLayer.getAllConsoles(); //This is an example. Implement a method in your service layer that fetches all the games from your database.
        model.addAttribute("consoles", consoles);
        return "./Console/searchConsole";
    }
    @GetMapping("/TshirtCatalog")
    public String returnSearchTShirt(Model model){
        List<Tshirt> tshirts = serviceLayer.getAllTshirts(); //This is an example. Implement a method in your service layer that fetches all the games from your database.
        model.addAttribute("tshirts", tshirts);
        return "./TShirt/searchTshirt";
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



    @RequestMapping(value = "/saveConsole", method = RequestMethod.POST)
    public ModelAndView saveConsole(@ModelAttribute Console console){
        System.out.println("Game from UI =" + console);

        serviceLayer.saveConsole(console);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("./Console/console_information");
        modelAndView.addObject("console",console);
        return modelAndView;
    }


    @RequestMapping(value = "/saveTshirt", method = RequestMethod.POST)
    public ModelAndView saveTshirt(@ModelAttribute Tshirt tshirt){
        System.out.println("Game from UI =" + tshirt);

        serviceLayer.saveTshirt(tshirt);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("./TShirt/tshirt_information");
        modelAndView.addObject("tshirt",tshirt);
        return modelAndView;
    }
    @RequestMapping(value = "/saveOurInvoice", method = RequestMethod.POST)
    public ModelAndView saveInvoice(@ModelAttribute("invoice") Invoice invoice) {
        System.out.println("invoice from UI =" + invoice);

        // Convert invoice to InvoiceViewModel
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setImageUrl(invoice.getImageUrl());
        invoiceViewModel.setId(invoiceViewModel.getId());
        invoiceViewModel.setName(invoice.getName());
        invoiceViewModel.setStreet(invoice.getStreet());
        invoiceViewModel.setCity(invoice.getCity());
        invoiceViewModel.setState(invoice.getState());
        invoiceViewModel.setZipcode(invoice.getZipcode());
        invoiceViewModel.setItemType(invoice.getItemType());
        invoiceViewModel.setItemId(invoice.getItemId());
        invoiceViewModel.setQuantity(invoice.getQuantity());
        invoiceViewModel.setUnitPrice(invoice.getUnitPrice());
        invoiceViewModel.setSubtotal(invoice.getSubtotal());
        invoiceViewModel.setTax(invoice.getTax());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setTotal(invoice.getTotal());

        serviceLayer.saveInvoice(invoiceViewModel);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("./Invoice/invoice_information");
        modelAndView.addObject("invoice",invoiceViewModel);
        return modelAndView;
    }
    // Function, will help us for searching at all costs! :)
    private static boolean subSequence(String query, String name){
        int j = 0;

        for(int i = 0; i < name.length() && j < query.length(); i++){

            if(query.charAt(j) == name.charAt(i)){
                j++;
            }
        }
        // Will return true/false depending on if we found all characters of query inside name (a.k.a item)
        boolean answer = j == query.length();
        return answer;
    }

    //Searching for specific items
    @GetMapping("/search")
    public String returnSearch(@RequestParam(name="q", required=false, defaultValue="") String query, Model model) {
    // Check if the query is empty
    if (query == null || query.isEmpty()) {
        //If the query is empty, then we will show to the user all items that we have
        return "redirect:/searchAll";
    }

    // Using what the user wrote, we are going to retrieve if what user wrote has
    // if matches a game title
    // if matches console manufacturer
    // if matches a color or a size of a t-shirt
    List<Game> gamesTitleExact = serviceLayer.getAllGamesByTitle(query);
    List<Game> gamesStudioExact = serviceLayer.getAllGamesByStudio(query);
    List<Console> consolesExact = serviceLayer.getAllConsolesByManufacturer(query);
    List<Tshirt> tshirtsByColorExact = serviceLayer.getAllTshirtsByColor(query);
    List<Tshirt> tshirtsBySizeExact = serviceLayer.getAllTshirtBySize(query);

    // Combine the two lists into one and removing repeating elements using "distinct", removing duplicates. Then we collect all into a list using "collect"
    List<Game> gamesExact = Stream.concat(gamesTitleExact.stream(), gamesStudioExact.stream())
            .distinct()
            .collect(Collectors.toList());

    // Combine the two lists into one and removing repeating elements using "distinct", removing duplicates. Then we collect all into a list using "collect"
    List<Tshirt> tshirtsExact = Stream.concat(tshirtsByColorExact.stream(), tshirtsBySizeExact.stream())
            .distinct()
            .collect(Collectors.toList());





    //If for some reason what the user wrote doesn't match exactly
    //Then we are going to retrieve to what might be searching for
    //Example:
    // User searched "Mario"
    // There is an item called "Super Mario Odyssey" but we want to still retrieve that

    //First we will retrieve everything we have for each of the items
    List<Game> allGames = serviceLayer.getAllGames();
    List<Console> allConsoles = serviceLayer.getAllConsoles();
    List<Tshirt> allTshirts = serviceLayer.getAllTshirts();

    //Now, we will filter depending on the search of the user
    //For games if the game title contains the word written by the user in the search bar
    List<Game> gamesTitleBroad = allGames.stream()
            .filter(game -> game.getTitle().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
    // WE also want to allow the user to search for Game Brands if the search contains a brand word
    List<Game> gamesStudioBroad = allGames.stream()
            .filter(game -> game.getStudio().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());

    List<Game> gamesBroad = Stream.concat(gamesTitleBroad.stream(), gamesStudioBroad.stream())
            .distinct()
            .collect(Collectors.toList());


    //For the consoles, if a console model contains a word wrote by the user in the search bar
    List<Console> consolesBroad = allConsoles.stream()
            .filter(console -> console.getModel().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());

    //For the T-shirts, if the color is the same as the color written by the user
    List<Tshirt> tshirtsBroad = allTshirts.stream()
            .filter(tshirt -> tshirt.getColor().toLowerCase().equals(query.toLowerCase()))
            .collect(Collectors.toList());





    // Merge exact and broad matches, removing duplicates
    List<Game> games = Stream.concat(gamesExact.stream(), gamesBroad.stream())
            .distinct()
            .collect(Collectors.toList());

    List<Console> consoles = Stream.concat(consolesExact.stream(), consolesBroad.stream())
            .distinct()
            .collect(Collectors.toList());

    List<Tshirt> tshirts = Stream.concat(tshirtsExact.stream(), tshirtsBroad.stream())
            .distinct()
            .collect(Collectors.toList());

    // If no item was found, return some potential items

    if(games.isEmpty() && consoles.isEmpty() && tshirts.isEmpty()){
        List<Game> gamesBroader = allGames.stream().filter(game -> subSequence(query.toLowerCase(), game.getTitle().toLowerCase())).collect(Collectors.toList());
        List<Console> consolesBroader = allConsoles.stream().filter(console -> subSequence(query.toLowerCase(), console.getModel().toLowerCase())).collect(Collectors.toList());
        List<Tshirt> tshirtsBroader = allTshirts.stream().filter(tshirt -> subSequence(query.toLowerCase(), tshirt.getColor().toLowerCase())).collect(Collectors.toList());

        games = gamesBroader;
        consoles = consolesBroader;
        tshirts = tshirtsBroader;
    }
    // If any item was found, we will return them
    if(!games.isEmpty() || !consoles.isEmpty() || !tshirts.isEmpty()){

        // Add the items to the model
        model.addAttribute("games", games);
        model.addAttribute("consoles", consoles);
        model.addAttribute("tshirts", tshirts);

        return "/SearchAllProducts/search";
    }

    // Return the "Not found" page if no item is going to be returned
    return "/ItemsNotFound/notfound";
}

    //    Search for all items (consoles, games, t-shirts)
    @GetMapping("/searchAll")
    public String returnSearchAll(Model model){
        List<Console> consoles = serviceLayer.getAllConsoles();
        List<Game> games = serviceLayer.getAllGames();
        List<Tshirt> tShirts = serviceLayer.getAllTshirts();


        model.addAttribute("consoles", consoles);
        model.addAttribute("games", games);
        model.addAttribute("tshirts", tShirts);

        return "/SearchAllProducts/search";
    }

}
