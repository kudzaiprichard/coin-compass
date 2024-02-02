package com.intela.favoriteservice.services;

import com.intela.favoriteservice.models.Favorite;
import com.intela.favoriteservice.repository.FavoriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public Favorite save(Favorite favorite){
        if(this.favoriteRepository.findFavoriteByName(favorite.getName()).isEmpty()){
            return this.favoriteRepository.save(favorite);
        }
        throw new RuntimeException("Crypto already exists");
    }

    public Favorite findByName(String name){
        return this.favoriteRepository.findFavoriteByName(name)
                .orElseThrow(() ->  new RuntimeException("Crypto currency not available"));
    }

    public List<Favorite> findAllByUserId(String userId){
        return this.favoriteRepository.findAllByUserId(userId);
    }

    public String deleteById(Integer id){
        this.favoriteRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Crypto currency not available"));

        this.favoriteRepository.deleteById(id);
        return "Crypto currency removed successfully";
    }

}
