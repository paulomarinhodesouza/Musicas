package com.paulin.musicas.service;

import com.paulin.musicas.model.MusicEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paulin.musicas.model.MusicRepository;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;
    
    @Autowired
    private AnalysisService analysisService;
     
    public List<MusicEntity> getMusicList(){
        var musicList = musicRepository.findAll();
        if(musicList == null || musicList.isEmpty()){
            return null;
        }
        return musicList;
    }
    
    public MusicEntity getMusicById(int id){
        try {
            var music = musicRepository.findById(id);
            return music;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    public MusicEntity save(MusicEntity music){
        try {
            musicRepository.save(music);
            return music;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    public boolean update(int id, MusicEntity music){
        var movieId = getMusicById(id);
        var listAnalysis = analysisService.findByIdMusic(id);
        try {
            if(movieId != null){
                if(listAnalysis != null){
                    for(int i = 0; i < listAnalysis.size(); i++){
                        int idAnalysis = listAnalysis.get(i).getId();
                        analysisService.delete(idAnalysis);
                    }
                }
                delete(id);
                save(music);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
        
    }
    
    public boolean delete(int id){
        try {
            var musicId = musicRepository.findById(id);
            var listAnalysis = analysisService.findByIdMusic(id);
            if(musicId != null){
                if(listAnalysis != null){
                    for(int i = 0; i < listAnalysis.size(); i++){
                        int idAnalysis = listAnalysis.get(i).getId();
                        analysisService.delete(idAnalysis);
                    }
                }
                musicRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
        
    }
}
