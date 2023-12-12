package com.paulin.musicas.controller;

import com.paulin.musicas.exception.ResourceNotFoundException;
import com.paulin.musicas.model.AnalysisEntity;
import com.paulin.musicas.service.AnalysisService;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping("/analysis")
public class AnalysisRestController {
    
    @Autowired
    private AnalysisService analysisService;
    
    @GetMapping
    public ResponseEntity findAllAnalysis(){
        List analysis = analysisService.findAll();
        if(analysis == null){
            throw new ResourceNotFoundException("Nenhuma análise encontrada");
        }
        return new ResponseEntity<>(analysis, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable int id){
        var analysis = analysisService.findById(id);
        if(analysis == null){
            throw new ResourceNotFoundException("Nenhuma análise encontrada pelo id: " + id);
        }
        return new ResponseEntity<>(analysis, HttpStatus.OK);
    }
    
    @GetMapping("/music/{idMusic}")
    public ResponseEntity findByIdMovie(@PathVariable int idMusic){
        List analysis = analysisService.findByIdMusic(idMusic);
        if(analysis == null){
            throw new ResourceNotFoundException("Nenhuma análise encontrada pela música: " + idMusic);
        }
        return new ResponseEntity<>(analysis, HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity saveAnalysis(@Valid @RequestBody AnalysisEntity analysis){
        var analysisSave = analysisService.save(analysis);
        if(analysisSave == null){
            return new ResponseEntity<>(analysisSave, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(analysisSave, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity updateAnalysis(@PathVariable int id, @Valid @RequestBody AnalysisEntity analysis){
        var analysisUpdate = analysisService.update(id, analysis);
        if(!analysisUpdate){
            throw new ResourceNotFoundException("Análise não encontrada pelo ID: " + id);
        }
        return new ResponseEntity<>(analysis, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAnalysis(@PathVariable int id){
        var analysisDelete = analysisService.delete(id);
        if(!analysisDelete){
            throw new ResourceNotFoundException("Análise não encontrada pelo ID: " + id);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
