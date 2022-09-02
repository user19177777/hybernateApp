package org.example.models;

import javax.persistence.*;
//сущность
@Entity
//название таблицы в бд
@Table(name = "Item")
public class Item {
    @Id
    //название колонки в бд
    @Column(name = "id")
    //ключ генерируется на стороне бд
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

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
}
