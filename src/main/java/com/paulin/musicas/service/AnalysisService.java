package com.paulin.musicas.service;

import com.paulin.musicas.model.AnalysisEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paulin.musicas.model.AnalysisRepository;
import com.paulin.musicas.model.MusicRepository;

@Service
public class AnalysisService {
    
    @Autowired 
    private AnalysisRepository analysisRepository;
    
    @Autowired
    private MusicRepository musicRepository;
    
    public List<AnalysisEntity> findAll(){
        var listAnalysis = analysisRepository.findAll();
        if(listAnalysis == null || listAnalysis.isEmpty()){
            return null;
        } else {
            return listAnalysis;
        }
    }
    public List<AnalysisEntity> findByIdMusic(int idMusic){
        var music = musicRepository.findById(idMusic);
        var listAnalysis = analysisRepository.findByMusic(music);
        if(listAnalysis == null || listAnalysis.isEmpty()){
            return null;
        } else {
            return listAnalysis;
        }
        
    }
    
    public AnalysisEntity findById(int id){
        var analysis = analysisRepository.findById(id);
        if(analysis == null){
            return null;
        } else {
            return analysis;
        }
        
    }
    
    public AnalysisEntity save(AnalysisEntity analysis){
        try {
            var idMusic = analysis.getIdMusic();
            var music = musicRepository.findById(Integer.parseInt(idMusic));
            analysis.setMusic(music);
            analysisRepository.save(analysis);
            return analysis;
        } catch (Exception e) {
            return null;
        }
        
    }
    public boolean delete(int id){
        try {
            analysisRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    public boolean update(int id, AnalysisEntity analysis){
        try {
            delete(id);
            save(analysis);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
}
