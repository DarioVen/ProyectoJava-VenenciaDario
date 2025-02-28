package VenenciaDario.MiProyectoJava.services;

import VenenciaDario.MiProyectoJava.entities.Client;
import VenenciaDario.MiProyectoJava.entities.Invoice;
import VenenciaDario.MiProyectoJava.repositories.ClientRepository;
import VenenciaDario.MiProyectoJava.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Optional<Invoice> saveInvoice(Long clientId, Invoice invoice) {
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isEmpty()) {
            return Optional.empty();
        }

        invoice.setClient(client.get());
        invoice.setCreatedAt(LocalDateTime.now());
        invoiceRepository.save(invoice);

        return Optional.of(invoice);
    }

    public Optional<Invoice> getInvoice(Long id) {
        return invoiceRepository.findById(id);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> updateInvoice(Long id, Invoice invoice) {
        Optional<Invoice> invoiceDB = invoiceRepository.findById(id);

        if (invoiceDB.isEmpty()) {
            return Optional.empty();
        }

        invoiceDB.get().setTotal(invoice.getTotal());
        invoiceDB.get().setInvoiceDetails(invoice.getInvoiceDetails());

        invoiceRepository.save(invoiceDB.get());

        return invoiceDB;
    }

    public Optional<Invoice> deleteInvoice(Long id) {
        Optional<Invoice> invoiceDB = invoiceRepository.findById(id);

        if (invoiceDB.isEmpty()) {
            return Optional.empty();
        }

        invoiceRepository.deleteById(id);

        return invoiceDB;
    }

    public List<Invoice> getInvoicesByClient(Long clientId) {
        return invoiceRepository.findByClientId(clientId);
    }
}