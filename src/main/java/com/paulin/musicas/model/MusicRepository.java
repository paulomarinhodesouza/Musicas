package com.paulin.musicas.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<MusicEntity, Integer> {
    MusicEntity findById(int id);
    MusicEntity findByTitle(String title);
    List<MusicEntity> findByGender(String gender);
    List<MusicEntity> findByYear(String year);
}
