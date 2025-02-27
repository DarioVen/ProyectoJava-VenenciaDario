package VenenciaDario.MiProyectoJava.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class InvoiceDetailId implements Serializable {

    @Column(name = "invoice_id")
    private int invoiceId;

    @Column(name = "invoice_detail_id")
    private int invoiceDetailId;

    // Constructor vacío (necesario para JPA)
    public InvoiceDetailId() {
    }

    // Constructor con parámetros
    public InvoiceDetailId(int invoiceId, int invoiceDetailId) {
        this.invoiceId = invoiceId;
        this.invoiceDetailId = invoiceDetailId;
    }

    // Getters y Setters
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(int invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    // Metodo toString para representación en cadena de la clave compuesta
    @Override
    public String toString() {
        return "InvoiceDetailId{" +
                "invoiceId=" + invoiceId +
                ", invoiceDetailId=" + invoiceDetailId +
                '}';
    }
}