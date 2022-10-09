package com.example.cakecatalog.services.form;

import com.example.sharedkernel.domain.financial.Money;
import lombok.Data;

@Data
public class CakeForm {
    private String name;
    private String description;
    private String imageUrl;
    private int sales;
    private Money price;
}
