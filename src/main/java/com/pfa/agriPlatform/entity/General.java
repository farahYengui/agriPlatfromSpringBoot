package com.pfa.agriPlatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class General {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "idclient", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Long idClient ;
    private String kind;
    private String variety;
    private int eFeet;
    private int eLine ;
    private int year;
    private int size;
}
