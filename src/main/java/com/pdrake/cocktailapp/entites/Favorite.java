package com.pdrake.cocktailapp.entites;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "favorites")
@Getter
@Setter
public class Favorite {
    @MongoId
    private String id;
    private String name;
    private List<String> ingredients;
    private List<String> instructions;
    private String imageUrl;

    public Favorite(String name, List<String> ingredients, List<String> instructions, String imageUrl) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.imageUrl = imageUrl;
    }
}
