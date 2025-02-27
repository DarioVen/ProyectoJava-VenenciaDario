package VenenciaDario.MiProyectoJava.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private String code;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price")
    private double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InvoiceDetails> invoiceDetails;

    // Constructor vacío (necesario para JPA)
    public Product() {
    }

    // Constructor con todos los campos excepto el id (autoincremental)
    public Product(String description, String code, int stock, double price, List<InvoiceDetails> invoiceDetails) {
        this.description = description;
        this.code = code;
        this.stock = stock;
        this.price = price;
        this.invoiceDetails = invoiceDetails;
    }

    // Constructor con ID (para objetos existentes)
    public Product(int id, String description, String code, int stock, double price) {
        this.id = id;
        this.description = description;
        this.code = code;
        this.stock = stock;
        this.price = price;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<InvoiceDetails> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetails> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    // Metodo toString para representación en cadena de la entidad
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", invoiceDetails=" + invoiceDetails +
                '}';
    }
}
