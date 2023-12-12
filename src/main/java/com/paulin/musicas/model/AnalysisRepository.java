package com.paulin.musicas.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<AnalysisEntity, Integer> {
    public List<AnalysisEntity> findByMusic(MusicEntity music);
    public AnalysisEntity findById(int id);
}
