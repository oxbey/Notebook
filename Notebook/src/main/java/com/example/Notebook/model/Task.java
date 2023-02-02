package com.example.Notebook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 5, message = "Name is too short")
    private final String name;

    private final String description;

    @ManyToOne(cascade=CascadeType.ALL)
    @JsonIgnoreProperties(value = {"authorities", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"})
    private final User user;

//    @Override
//    public String toString() {
//        return "Task{" +
//                "name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", user=" + user +
//                '}';
//    }
}
