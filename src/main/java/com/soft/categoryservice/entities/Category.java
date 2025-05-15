package com.soft.categoryservice.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PICTURE", nullable = false)
    private String picture;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;


    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    List<SubCategory> subCategories;
}
