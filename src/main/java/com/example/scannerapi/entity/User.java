package com.example.scannerapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "Role")
    private Integer role;

    @ManyToOne
    @JoinColumn(name = "Storeid")
    private Store store;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_store",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id")
    )
    private List<Store> stores;

    @Column(name = "Datecreated")
    private LocalDateTime dateCreated;

    @PrePersist
    public void onCreate() {
        dateCreated = LocalDateTime.now();
    }
}
