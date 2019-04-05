package jdbc;

import java.sql.Timestamp;

public class Category {

    private Integer category_id;
    private String name;
    private Timestamp last_update;

    public Category() {
    }


    public Category(Integer category_id, String name, Timestamp last_update) {
        this.category_id = category_id;
        this.name = name;
        this.last_update = last_update;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer id) {
        this.category_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id= " + category_id +
                ", name= '" + name + '\'' +
                ", last_update= " + last_update +
                '}';
    }
}
