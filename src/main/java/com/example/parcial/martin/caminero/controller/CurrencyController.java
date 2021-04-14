package com.example.parcial.martin.caminero.controller;

import com.example.parcial.martin.caminero.model.Currency;
import com.example.parcial.martin.caminero.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping
    public void addCurrency(@RequestBody Currency currency){
        currencyService.add(currency);
    }

    @GetMapping
    public List<Currency> getAllCurrencies(){
        return currencyService.getAll();
    }

    @GetMapping("/{id}")
    public Currency getCurrencyById(@PathVariable int id) {
        return currencyService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCurrencyById(@PathVariable int id){
        currencyService.deleteById(id);
    }

}
