package com.example.scanner_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "[key]", unique = true)
    private String key;
    @Column(name = "Keylicense")
    private String keyLicense;
    @ManyToOne
    @JoinColumn(name = "Storeid")
    private Store store;
    @Column(name = "Datecreated")
    private LocalDateTime dateCreated;

    @PrePersist
    public void onCreate(){
        dateCreated=LocalDateTime.now();
    }

}
