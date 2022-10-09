package com.example.cakecatalog.config;

import com.example.cakecatalog.domain.model.Cake;
import com.example.cakecatalog.domain.repository.CakeRepository;
import com.example.cakecatalog.services.CakeService;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitilizer {

    private final CakeRepository cakeRepository;
    @PostConstruct
    public void initData(){
        Cake cake1 = Cake.build("Trilece",
                "Torta so tri sloevi na mleko - Turski specijalitet",
                "https://i0.wp.com/tiffinandteaofficial.com/wp-content/uploads/2020/10/IMG_0871-1.jpg?resize=800%2C877&ssl=1",
                20,
                Money.valueOf(Currency.MKD,600));
        Cake cake2 = Cake.build("Toplo Ladno",
                "Muffin so sladoled",
                "https://i0.wp.com/www.magazin.mk/wp-content/uploads/2019/03/%D1%82%D0%BE%D0%BF%D0%BB%D0%BE-%D0%BB%D0%B0%D0%B4%D0%BD%D0%BE%D0%BE%D0%BE.jpg?fit=1024%2C768&ssl=1",
                25,
                Money.valueOf(Currency.MKD,800));
        if(cakeRepository.findAll().isEmpty())
        {
            this.cakeRepository.saveAll(Arrays.asList(cake1,cake2));
        }

    }

}
