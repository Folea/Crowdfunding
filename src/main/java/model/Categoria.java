package model;

import javax.persistence.*;

/**
 * Created by foleac on 7/5/2015.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Categoria.getCategorias", query = "select c from Categoria as c" ),
        @NamedQuery(name = "Categoria.getCategoria", query = "select c from Categoria as c where c.name = :nombre")
})

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(unique = true)
    private String name;

    public Categoria() {

    }

    public Categoria(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
