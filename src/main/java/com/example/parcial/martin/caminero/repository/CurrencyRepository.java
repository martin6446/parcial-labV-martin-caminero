package com.example.parcial.martin.caminero.repository;

import com.example.parcial.martin.caminero.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

}
