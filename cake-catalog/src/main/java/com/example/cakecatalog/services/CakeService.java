package com.example.cakecatalog.services;

import com.example.cakecatalog.domain.model.Cake;
import com.example.cakecatalog.domain.model.CakeId;
import com.example.cakecatalog.services.form.CakeForm;

import java.util.List;

public interface CakeService {
    Cake findById(CakeId cakeId);
    Cake createCake(CakeForm form);
    List<Cake> getAllCakes();
    void deleteCake(CakeId cakeId);
}
