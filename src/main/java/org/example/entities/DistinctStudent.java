package org.example.entities;

import jakarta.persistence.*;

import java.util.List;



@Entity
@Table(name= "distinct_student")
public class DistinctStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DistinctStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
