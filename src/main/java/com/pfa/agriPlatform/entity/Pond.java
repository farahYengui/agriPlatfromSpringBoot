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
public class Pond {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "idclient", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Long idClient;
    private float capacity;
    private String betan;
    private String equipment;

}
