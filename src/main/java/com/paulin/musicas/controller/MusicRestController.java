package com.paulin.musicas.controller;

import com.paulin.musicas.exception.ResourceNotFoundException;
import com.paulin.musicas.model.MusicEntity;
import com.paulin.musicas.service.MusicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music")
public class MusicRestController {
    @Autowired
    private MusicService musicService;
    
    @GetMapping
    public ResponseEntity findAllMovies(){
        var listMusic = musicService.getMusicList();
        if(listMusic == null){
            throw new ResourceNotFoundException("Nenhuma musica encontrada");
        }
        return new ResponseEntity<>(listMusic, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findMovieById(@PathVariable int id){
        var movie = musicService.getMusicById(id);
        if(movie == null){
            throw new ResourceNotFoundException("Musica não encontrada");
        }
        return new ResponseEntity(movie, HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity save(@Valid @RequestBody MusicEntity music){
        var save = musicService.save(music);
        if(save == null){
            return new ResponseEntity(music, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(music, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody MusicEntity music){
        var update = musicService.update(id, music);
        if(!update){
            throw new ResourceNotFoundException("Erro ao atualizar a musica pelo id: " + id);
        }
        return new ResponseEntity(music, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        var delete = musicService.delete(id);
        if(!delete){
            throw new ResourceNotFoundException("Erro ao deletar a musica pelo id: " + id);
        }
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
