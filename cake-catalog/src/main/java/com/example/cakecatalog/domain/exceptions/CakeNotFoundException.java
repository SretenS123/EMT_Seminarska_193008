package com.example.cakecatalog.domain.exceptions;

import com.example.cakecatalog.domain.model.CakeId;

public class CakeNotFoundException extends RuntimeException {
    public CakeNotFoundException(CakeId cakeId) {
        super(String.format("The cake with this id %d was not found",cakeId));
    }
}
