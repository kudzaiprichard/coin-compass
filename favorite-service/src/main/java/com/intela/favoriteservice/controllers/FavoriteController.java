package com.intela.favoriteservice.controllers;

import com.intela.favoriteservice.client.AuthClient;
import com.intela.favoriteservice.models.Favorite;
import com.intela.favoriteservice.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorite")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final AuthClient authClient;

    @PostMapping("/save")
    public ResponseEntity<Favorite> save(
            @RequestBody Favorite favorite
    ){
        return ResponseEntity
                .accepted()
                .body(this.favoriteService.save(favorite));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Favorite>> fetchAll(){
        String userId = authClient.getLoggedInUser().getBody();
        return ResponseEntity.accepted()
                .body(
                        this.favoriteService.findAllByUserId(userId)
                ); //Todo: get loggedin user
    }

    @GetMapping("/{name}")
    public ResponseEntity<Favorite> fetchByName(
            @PathVariable String name
    ){
        return ResponseEntity.accepted()
                .body(this.favoriteService.findByName(name));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id
    ){
        return ResponseEntity.accepted()
                .body(this.favoriteService.deleteById(id));
    }
}
