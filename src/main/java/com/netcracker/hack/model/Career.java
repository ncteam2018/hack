package com.netcracker.hack.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "career")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "placeOfWork")
    private String placeOfWork;

    @Column(name = "position")
    private String position;

    public Career() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Career career = (Career) o;
        return Objects.equals(id, career.id) &&
                Objects.equals(placeOfWork, career.placeOfWork) &&
                Objects.equals(position, career.position);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, placeOfWork, position);
    }
}
