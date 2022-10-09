package com.example.ordercakemanagment.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;



@Getter
public class Cake implements ValueObject {

    private final CakeId id;
    private final String name;
    private final String description;
    private final Money price;
    private final int sales;

    private Cake(){
        this.id=CakeId.randomId(CakeId.class);
        this.name="";
        this.price=Money.valueOf(Currency.MKD,0);
        this.description="";
        this.sales=0;
    }
    @JsonCreator
    public Cake(@JsonProperty("id") CakeId id,
                @JsonProperty("productName") String name,
                @JsonProperty("details") String description,
                @JsonProperty("price") Money price,
                @JsonProperty("sales") int sales) {
        this.id = id;
        this.name = name;
        this.description  = description;
        this.price = price;
        this.sales=sales;
    }
}
