package VenenciaDario.MiProyectoJava.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INVOICE_DETAILS")
public class InvoiceDetails {
    @EmbeddedId
    private InvoiceDetailId id;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "price")
    private double price;
}