package com.example.parcial.martin.caminero.service;

import com.example.parcial.martin.caminero.exception.CurrencyNotFoundException;
import com.example.parcial.martin.caminero.model.Currency;
import com.example.parcial.martin.caminero.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;


    public void add(Currency currency) {
        currencyRepository.save(currency);
    }

    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

    public Currency getById(int id) {
        return currencyRepository.findById(id).orElseThrow(CurrencyNotFoundException::new);
    }

    public void deleteById(int id) {
        currencyRepository.deleteById(id);
    }
}
