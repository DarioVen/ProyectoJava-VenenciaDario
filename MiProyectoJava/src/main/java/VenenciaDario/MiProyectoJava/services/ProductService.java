
package VenenciaDario.MiProyectoJava.services;

import VenenciaDario.MiProyectoJava.entities.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final JdbcTemplate jdbc;

    public ProductService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Product> getAllProducts() {
        return jdbc.query(
                "SELECT * FROM products",
                (rs, rowNum) -> new Product(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getString("code"),
                        rs.getInt("stock"),
                        rs.getDouble("price")
                )
        );
    }

    public void createProduct(Product product) {
        jdbc.update(
                "INSERT INTO products (description, code, stock, price) VALUES (?, ?, ?, ?)",
                product.getDescription(),
                product.getCode(),
                product.getStock(),
                product.getPrice()
        );
    }
}