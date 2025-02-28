package VenenciaDario.MiProyectoJava.repositories;


import VenenciaDario.MiProyectoJava.entities.InvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Long> {
    List<InvoiceDetails> findByInvoiceId(Long invoiceId);
}