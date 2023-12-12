package com.paulin.musicas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="music")
@Table(name="music")
public class MusicEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @NotBlank
    @Size(min=2, message="Digite pelo menos 2 caracteres")
    @Column(name="title")
    private String title;
    
    @NotBlank
    @Size(min=2, message="Digite pelo menos 2 caracteres")
    @Column(name="gender")
    private String gender;
    
    @NotBlank
    @Pattern(regexp="\\d{4}")
    @Column(name="year")
    private String year;
    
    
}
