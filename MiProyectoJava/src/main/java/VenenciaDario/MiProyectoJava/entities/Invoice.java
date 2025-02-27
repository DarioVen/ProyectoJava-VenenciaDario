package VenenciaDario.MiProyectoJava.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "total", nullable = false)
    private double total;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InvoiceDetails> invoiceDetails;

    // Constructor vacío (necesario para JPA)
    public Invoice() {
    }

    // Constructor con todos los campos excepto el id (autoincremental)
    public Invoice(Client client, LocalDateTime createdAt, double total, List<InvoiceDetails> invoiceDetails) {
        this.client = client;
        this.createdAt = createdAt;
        this.total = total;
        this.invoiceDetails = invoiceDetails;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
        return "Invoice{" +
                "id=" + id +
                ", client=" + client +
                ", createdAt=" + createdAt +
                ", total=" + total +
                ", invoiceDetails=" + invoiceDetails +
                '}';
    }
}