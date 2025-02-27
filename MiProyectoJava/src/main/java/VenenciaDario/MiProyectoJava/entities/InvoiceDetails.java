package VenenciaDario.MiProyectoJava.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetails {

    @EmbeddedId
    private InvoiceDetailId id;

    @ManyToOne
    @MapsId("invoiceId")
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    // Constructor vacío
    public InvoiceDetails() {
    }

    // Constructor con parámetros
    public InvoiceDetails(InvoiceDetailId id, Invoice invoice, Product product, int quantity, double price) {
        this.id = id;
        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters y Setters
    public InvoiceDetailId getId() {
        return id;
    }

    public void setId(InvoiceDetailId id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Método toString
    @Override
    public String toString() {
        return "InvoiceDetails{" +
                "id=" + id +
                ", invoice=" + invoice +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}