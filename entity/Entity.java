package entity;

public class Entity {
    private int id;
    private int price;
    private String name;

    /// GETTERS
    public int getId() {
        return this.id;
    }

    public int getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }
    ///

    /// SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    ///

    @Override
    public boolean equals(Object o) {
        if (this.getId() == ((Entity) o).getId()) {
            return true;
        } else {
            return false;
        }
    }
}