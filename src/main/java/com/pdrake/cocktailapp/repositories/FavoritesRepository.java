package com.pdrake.cocktailapp.repositories;

import com.pdrake.cocktailapp.entites.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritesRepository extends MongoRepository<Favorite, String> {
    Optional<List<Favorite>> findByName(String name);

    Optional<Favorite> getFavoriteByName(String name);
}
