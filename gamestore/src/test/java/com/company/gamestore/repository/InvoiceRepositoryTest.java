package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    TaxRepository taxRepository;

    @Autowired
    FeeRepository feeRepository;



    @Autowired
    GameRepository gameRepository;


    @Before
    public void setUp() throws Exception{
        invoiceRepository.deleteAll();
    }

    @Test
    public void addGetDeleteInvoice(){
        //Add Game - We are going to use it to pretend buying a game
        Game game = new Game();
        game.setTitle("Super Mario Odyssey");
        game.setEsrbRating("E");
        game.setDescription("Join Mario on a massive, globe-trotting 3D adventure!");
        game.setPrice(new BigDecimal( "59.99"));
        game.setStudio("Nintendo");
        game.setQuantity(50);

        game = gameRepository.save(game);

        //Add
        Invoice invoice = new Invoice();
        invoice.setName("Customer 1");
        invoice.setStreet("100 Main Street");
        invoice.setCity("Clovis");
        invoice.setState("CA");
        invoice.setZipcode("93612");
        invoice.setItemType("Game");
        invoice.setItemId(game.getGameId()); // getting the id from the game we created
        invoice.setQuantity(12);

        //Calculations
        BigDecimal unitPrice = game.getPrice();
        BigDecimal quantity = new BigDecimal(invoice.getQuantity());
        BigDecimal subtotal = unitPrice.multiply(quantity); // unitPrice.multiply(new BigDecimal(invoice.getQuantity()))

        BigDecimal taxRate =  taxRepository.findByState(invoice.getState()).get().getRate();
        BigDecimal baseProcessingFee = new BigDecimal("1.49");
        BigDecimal additionalProcessingFee = new BigDecimal("15.49");

        BigDecimal amountGettingTaxed = taxRate.multiply(subtotal);
        BigDecimal total = amountGettingTaxed.add(subtotal).add(baseProcessingFee).add(additionalProcessingFee);

        // Setting final Invoice variables
        invoice.setUnitPrice(unitPrice);
        invoice.setSubtotal(subtotal);
        invoice.setTax( amountGettingTaxed.setScale(2, RoundingMode.CEILING));
        invoice.setProcessingFee(additionalProcessingFee.add(baseProcessingFee));
        invoice.setTotal(total.setScale(2, RoundingMode.CEILING));

        invoice = invoiceRepository.save(invoice);

        //Get
        Optional<Invoice> invoice1 = invoiceRepository.findById(invoice.getId());
        assertEquals(invoice1.get(),invoice);

        invoiceRepository.deleteById(invoice.getId());

        invoice1 = invoiceRepository.findById(invoice.getId());

        assertFalse(invoice1.isPresent());

    }

    @Test
    public void getAllInvoices(){
        //Add Game - We are going to use it to pretend buying a game
        Game game = new Game();
        game.setTitle("Super Mario Odyssey");
        game.setEsrbRating("E");
        game.setDescription("Join Mario on a massive, globe-trotting 3D adventure!");
        game.setPrice(new BigDecimal( "59.99"));
        game.setStudio("Nintendo");
        game.setQuantity(50);

        game = gameRepository.save(game);

        //Add
        Invoice invoice = new Invoice();
        invoice.setName("Customer 1");
        invoice.setStreet("100 Main Street");
        invoice.setCity("Clovis");
        invoice.setState("CA");
        invoice.setZipcode("93612");
        invoice.setItemType("Game");
        invoice.setItemId(game.getGameId()); // getting the id from the game we created
        invoice.setQuantity(12);

        //Calculations
        BigDecimal unitPrice = game.getPrice();
        BigDecimal quantity = new BigDecimal(invoice.getQuantity());
        BigDecimal subtotal = unitPrice.multiply(quantity); // unitPrice.multiply(new BigDecimal(invoice.getQuantity()))

        BigDecimal taxRate =  taxRepository.findByState(invoice.getState()).get().getRate();
        BigDecimal baseProcessingFee = new BigDecimal("1.49");
        BigDecimal additionalProcessingFee = new BigDecimal("15.49");

        BigDecimal amountGettingTaxed = taxRate.multiply(subtotal);
        BigDecimal total = amountGettingTaxed.add(subtotal).add(baseProcessingFee).add(additionalProcessingFee);

        // Setting final Invoice variables
        invoice.setUnitPrice(unitPrice);
        invoice.setSubtotal(subtotal);
        invoice.setTax( amountGettingTaxed.setScale(2, RoundingMode.CEILING));
        invoice.setProcessingFee(additionalProcessingFee.add(baseProcessingFee));
        invoice.setTotal(total.setScale(2, RoundingMode.CEILING));

        invoice = invoiceRepository.save(invoice);


        //Adding Second Invoice

        Game game2 = new Game();
        game2.setTitle("The Last of Us Part II");
        game2.setEsrbRating("M");
        game2.setDescription("Survive a post-apocalyptic world filled with danger and difficult choices!");
        game2.setPrice(new BigDecimal("69.99"));
        game2.setStudio("Naughty Dog");
        game2.setQuantity(75);

        game2 = gameRepository.save(game2);

        Invoice invoice2 = new Invoice();
        invoice2.setName("Customer 2");
        invoice2.setStreet("200 Broadway");
        invoice2.setCity("New York");
        invoice2.setState("NY");
        invoice2.setZipcode("10007");
        invoice2.setItemType("Game");
        invoice2.setItemId(game2.getGameId());
        invoice2.setQuantity(25);



        //Calculations
        BigDecimal unitPrice2 = game2.getPrice();
        BigDecimal quantity2 = new BigDecimal(invoice2.getQuantity());
        BigDecimal subtotal2 = unitPrice.multiply(quantity2); // unitPrice.multiply(new BigDecimal(invoice.getQuantity()))

        BigDecimal taxRate2 =  taxRepository.findByState(invoice2.getState()).get().getRate();
        BigDecimal baseProcessingFee2 = new BigDecimal("1.49");
        BigDecimal additionalProcessingFee2 = new BigDecimal("15.49");

        BigDecimal amountGettingTaxed2 = taxRate2.multiply(subtotal2);
        BigDecimal total2 = amountGettingTaxed2.add(subtotal2).add(baseProcessingFee2).add(additionalProcessingFee2);

        // Setting final Invoice variables
        invoice2.setUnitPrice(unitPrice2);
        invoice2.setSubtotal(subtotal2);
        invoice2.setTax( amountGettingTaxed2.setScale(2, RoundingMode.CEILING));
        invoice2.setProcessingFee(additionalProcessingFee2.add(baseProcessingFee2));
        invoice2.setTotal(total2.setScale(2, RoundingMode.CEILING));








        invoice2 = invoiceRepository.save(invoice2);

        List<Invoice> invoiceList = invoiceRepository.findAll();

        assertEquals(invoiceList.size(), 2);


    }

    @Test
    public void updateInvoice(){
        //Add Game - We are going to use it to pretend buying a game
        Game game = new Game();
        game.setTitle("Super Mario Odyssey");
        game.setEsrbRating("E");
        game.setDescription("Join Mario on a massive, globe-trotting 3D adventure!");
        game.setPrice(new BigDecimal( "59.99"));
        game.setStudio("Nintendo");
        game.setQuantity(50);

        game = gameRepository.save(game);

        //Add
        Invoice invoice = new Invoice();
        invoice.setName("Customer 1");
        invoice.setStreet("100 Main Street");
        invoice.setCity("Clovis");
        invoice.setState("CA");
        invoice.setZipcode("93612");
        invoice.setItemType("Game");
        invoice.setItemId(game.getGameId()); // getting the id from the game we created
        invoice.setQuantity(12);

        //Calculations
        BigDecimal unitPrice = game.getPrice();
        BigDecimal quantity = new BigDecimal(invoice.getQuantity());
        BigDecimal subtotal = unitPrice.multiply(quantity); // unitPrice.multiply(new BigDecimal(invoice.getQuantity()))

        BigDecimal taxRate =  taxRepository.findByState(invoice.getState()).get().getRate();
        BigDecimal baseProcessingFee = new BigDecimal("1.49");
        BigDecimal additionalProcessingFee = new BigDecimal("15.49");

        BigDecimal amountGettingTaxed = taxRate.multiply(subtotal);
        BigDecimal total = amountGettingTaxed.add(subtotal).add(baseProcessingFee).add(additionalProcessingFee);

        // Setting final Invoice variables
        invoice.setUnitPrice(unitPrice);
        invoice.setSubtotal(subtotal);
        invoice.setTax( amountGettingTaxed.setScale(2, RoundingMode.CEILING));
        invoice.setProcessingFee(additionalProcessingFee.add(baseProcessingFee));
        invoice.setTotal(total.setScale(2, RoundingMode.CEILING));

        invoice = invoiceRepository.save(invoice);

        //Update
        invoice.setStreet("200 Broadway");
        invoice.setCity("New York");

        invoiceRepository.save(invoice);

        Optional<Invoice> invoice1 = invoiceRepository.findById(invoice.getId());
        assertEquals(invoice1.get(),invoice);
    }
}