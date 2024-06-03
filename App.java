import database.Database;
import entity.Product;
import exception.DatabaseException;

public class App {
    public static void main(String[] args) throws DatabaseException {
        Database database = new Database();

        Product produto = new Product(123, 4.00, "leite", "comida");
        database.save(Product.class, produto);
        System.out.print(database.findById(Product.class, 123));
        // database.delete(Product.class, 123);
    }
}
