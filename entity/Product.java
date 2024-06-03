package entity;

public class Product extends Entity {
    private String category;
    private double price;
    private String name;

    /// GETTERS
    public String getCategory() {
        return this.category;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    /// SETTERS
    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    /// CONSTRUCTOR
    public Product(int id, double price, String name, String category) {
        this.setId(id);
        this.setPrice(price);
        this.setName(name);
        this.setCategory(category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", price=" + getPrice() +
                ", name='" + getName() + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}