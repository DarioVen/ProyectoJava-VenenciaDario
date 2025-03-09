package VenenciaDario.MiProyectoJava.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "total", nullable = false)
    private double total;

    @JsonManagedReference
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<InvoiceDetails> invoiceDetails = new ArrayList<>();

    public Invoice() {
    }

    public Invoice(Client client, double total, List<InvoiceDetails> invoiceDetails) {
        this.client = client;
        this.total = total;
        this.invoiceDetails = invoiceDetails;
        if (client != null) {
            client.addInvoice(this);
        }
        calculateTotal();
    }

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
        if (client != null && !client.getInvoices().contains(this)) {
            client.getInvoices().add(this);
        }
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
        calculateTotal();
    }


    public void addInvoiceDetail(InvoiceDetails invoiceDetail) {
        if (invoiceDetails == null) {
            invoiceDetails = new ArrayList<>();
        }
        if (!invoiceDetails.contains(invoiceDetail)) {
            invoiceDetails.add(invoiceDetail);
            invoiceDetail.setInvoice(this);
            calculateTotal();
        }
    }

    public void calculateTotal() {
        this.total = 0.0;
        if (invoiceDetails != null) {
            for (InvoiceDetails detail : invoiceDetails) {
                this.total += detail.getSubtotal();
            }
        }
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", client=" + (client != null ? client.getId() : null) +
                ", createdAt=" + createdAt +
                ", total=" + total +
                ", invoiceDetails=" + invoiceDetails +
                '}';
    }
}