package com.example.cakecatalog.domain.model;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Money;
import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cake")
@Getter
public class Cake extends AbstractEntity<CakeId> {

    private String name;

    private String description;

    private String imageUrl;

    private int sales = 0;

    @Embedded
    private Money price;

    public Cake() {
        super(CakeId.randomId(CakeId.class));
    }

    public static Cake build(String name,String description,String imageUrl,int sales,Money price){
        Cake c = new Cake();
        c.name = name;
        c.description = description;
        c.imageUrl = imageUrl;
        c.sales = sales;
        c.price = price;
        return c;
    }
    public void addSales(int quantity)
    {
        this.sales -= quantity;
    }
    public void removeSales(int quantity)
    {
        this.sales +=quantity;
    }
}
