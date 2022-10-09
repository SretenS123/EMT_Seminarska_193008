package com.example.cakecatalog.xport.rest;

import com.example.cakecatalog.domain.model.Cake;
import com.example.cakecatalog.services.CakeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cakes")
@CrossOrigin(origins = "https://localhost:3000")
@AllArgsConstructor
public class CakesController {

        private final CakeService cakeService;

        @GetMapping
        public List<Cake> getAllCakes(){
            return this.cakeService.getAllCakes();
        }
}
