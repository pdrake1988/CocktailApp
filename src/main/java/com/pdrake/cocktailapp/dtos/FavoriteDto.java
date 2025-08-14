package com.pdrake.cocktailapp.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FavoriteDto {
    private String id;
    private CocktailData cocktailData;
    private String imageUrl;

    public FavoriteDto(String id, CocktailData cocktailData, String imageUrl) {
        this.id = id;
        this.cocktailData = cocktailData;
        this.imageUrl = imageUrl;
    }
    @JsonCreator
    public FavoriteDto(
            @JsonProperty("cocktailData")
            CocktailData cocktailData,
            @JsonProperty("imageUrl")
            String imageUrl) {
        this.cocktailData = cocktailData;
        this.imageUrl = imageUrl;
    }
}
