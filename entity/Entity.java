package entity;

public class Entity {
    private int id;

    /// GETTERS
    public int getId() {
        return this.id;
    }

    ///

    /// SETTERS
    public void setId(int id) {
        this.id = id;
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