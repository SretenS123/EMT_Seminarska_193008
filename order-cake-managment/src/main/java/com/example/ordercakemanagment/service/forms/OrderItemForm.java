package com.example.ordercakemanagment.service.forms;

import com.example.ordercakemanagment.domain.valueobjects.Cake;
import com.sun.istack.NotNull;
import lombok.Data;


import javax.validation.constraints.Min;


@Data

public class OrderItemForm {

    @NotNull
    private Cake cake;

    @Min(1)
    private int quantity = 1;

}
