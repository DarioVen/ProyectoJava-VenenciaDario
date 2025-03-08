package VenenciaDario.MiProyectoJava.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Transient
    private double subtotal;

    public InvoiceDetails() {
        calculateSubtotal();
    }

    public InvoiceDetails(Long id, Invoice invoice, Product product, int quantity) {
        this.id = id;
        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
        calculateSubtotal();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
        if (invoice != null && !invoice.getInvoiceDetails().contains(this)) {
            invoice.getInvoiceDetails().add(this);
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        calculateSubtotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateSubtotal();
    }

    public double getSubtotal() {
        calculateSubtotal();
        return subtotal;
    }

    public void calculateSubtotal() {
        if (this.product != null) {
            this.subtotal = this.quantity * this.product.getPrice();
        } else {
            this.subtotal = 0.0;
        }
    }

    @Override
    public String toString() {
        return "InvoiceDetails{" +
                "id=" + id +
                ", invoice=" + (invoice != null ? invoice.getId() : null) +
                ", product=" + (product != null ? product.getId() : null) +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}