package com.pdrake.cocktailapp.controllers;

import com.pdrake.cocktailapp.dtos.CocktailData;
import com.pdrake.cocktailapp.dtos.FavoriteDto;
import com.pdrake.cocktailapp.entites.Favorite;
import com.pdrake.cocktailapp.repositories.FavoritesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/favorites")
@CrossOrigin(origins = "http://localhost:3000")
public class FavoritesController {
    private final FavoritesRepository favoritesRepository;

    public FavoritesController(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    @GetMapping("/get-all")
    public List<FavoriteDto> getFavorites() {
        List<Favorite> favorites = favoritesRepository.findAll();
        return favorites.stream().map(item -> {
            CocktailData cocktailData = new CocktailData(item.getName(), item.getIngredients(), item.getInstructions());
            return new FavoriteDto(item.getId(), cocktailData, item.getImageUrl());
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDto> getFavoriteById(@PathVariable String id) {
        Optional<Favorite> potentialFavorite = favoritesRepository.findById(id);
        if (potentialFavorite.isPresent()) {
            Favorite favorite = potentialFavorite.get();
            CocktailData cocktailData = new CocktailData(favorite.getName(), favorite.getIngredients(), favorite.getInstructions());
            FavoriteDto favoriteDto = new FavoriteDto(favorite.getId(), cocktailData, favorite.getImageUrl());
            return ResponseEntity.ok(favoriteDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add-favorite")
    public ResponseEntity<String> addFavorite(@RequestBody FavoriteDto favoriteDto) {
        String name = favoriteDto.getCocktailData().getName();
        List<String> ingredients = favoriteDto.getCocktailData().getIngredients();
        List<String> instructions = favoriteDto.getCocktailData().getInstructions();
        String imageUrl = favoriteDto.getImageUrl();
        favoritesRepository.save(new Favorite(name, ingredients, instructions, imageUrl));
        return ResponseEntity.ok("Favorite added");
    }

    @PutMapping("/update-image/{name}")
    public ResponseEntity<String> updateFavorite(@PathVariable String name, @RequestBody String imageUrl) {
        Optional<List<Favorite>> favorite = favoritesRepository.findByName(name);
        if (favorite.isPresent()) {
            List<Favorite> favorites = favorite.get();
            favorites.forEach(item -> item.setImageUrl(imageUrl));
            favoritesRepository.saveAll(favorites);
            return ResponseEntity.ok("Image updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
