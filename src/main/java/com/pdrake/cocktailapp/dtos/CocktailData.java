package com.pdrake.cocktailapp.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CocktailData {
    private String name;
    private List<String> ingredients;
    private List<String> instructions;

    public CocktailData(String name, List<String> ingredients, List<String> instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
}
