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
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(unique=true)
    private String name;
    private String email;
    private String city;
    private String address;
    private int irrigated;
    private int noirrigated;
    private int total;
    private int homogenous;
    private int heterogenous;
    private int clay;
    private int silt;
    private int sand;
    private int limestone;
    private int gypsum;
    private String maps;
}
