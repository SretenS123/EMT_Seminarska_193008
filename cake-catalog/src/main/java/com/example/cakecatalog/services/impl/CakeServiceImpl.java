package com.example.cakecatalog.services.impl;

import com.example.cakecatalog.domain.exceptions.CakeNotFoundException;
import com.example.cakecatalog.domain.model.Cake;
import com.example.cakecatalog.domain.model.CakeId;
import com.example.cakecatalog.domain.repository.CakeRepository;
import com.example.cakecatalog.services.CakeService;
import com.example.cakecatalog.services.form.CakeForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CakeServiceImpl implements CakeService {
    private final CakeRepository cakeRepository;


    @Override
    public Cake findById(CakeId cakeId) {
        return this.cakeRepository.findById(cakeId).orElseThrow(()-> new CakeNotFoundException(cakeId));
    }

    @Override
    public Cake createCake(CakeForm form) {
        Cake cake = Cake.build(form.getName(),form.getDescription(), form.getImageUrl(), form.getSales(),form.getPrice());
        this.cakeRepository.save(cake);
        return cake;

    }

    @Override
    public List<Cake> getAllCakes() {
        return this.cakeRepository.findAll();
    }

    @Override
    public void deleteCake(CakeId cakeId) {
        Cake cake = this.cakeRepository.findById(cakeId).orElseThrow(()-> new CakeNotFoundException(cakeId));
        this.cakeRepository.delete(cake);
    }
}
