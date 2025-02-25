package VenenciaDario.MiProyectoJava.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Embeddable
public class InvoiceDetailId implements Serializable {
    @Column(name = "invoice_id")
    private int invoiceId;

    @Column(name = "invoice_detail_id")
    private int invoiceDetailId;
}

