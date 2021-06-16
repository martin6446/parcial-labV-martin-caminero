package UtilsTest;

import com.example.parcial.martin.caminero.model.Country;
import com.example.parcial.martin.caminero.model.Currency;
import com.example.parcial.martin.caminero.model.CurrencyType;
import com.example.parcial.martin.caminero.model.Player;
import com.example.parcial.martin.caminero.model.dto.PlayerDto;
import com.example.parcial.martin.caminero.model.dto.StarPlayer;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

public class UtilsTest {


    public static Player aPlayer(){

        Player player = new Player(42.5f,12.5f,12,35,LocalDate.now(),Currency.builder()
                .id(1)
                .currency(CurrencyType.DOLLAR)
                .total(123)
                .build());

        player.setName("martin");
        return player;
    }

    public static PlayerDto aPlayerDto(){
        return new ModelMapper().map(aPlayer(),PlayerDto.class);
    }

    public static StarPlayer aStarPlayer(){
        return StarPlayer
                .builder()
                .id(1)
                .firstName("martin")
                .lastName("palermo")
                .age(43)
                .height(1.95f)
                .country(new Country())
                .build();
    }
}
