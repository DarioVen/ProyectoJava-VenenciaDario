package VenenciaDario.MiProyectoJava.services;

import VenenciaDario.MiProyectoJava.entities.*;
import VenenciaDario.MiProyectoJava.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;

    public Optional<Invoice> saveInvoice(Long clientId, Invoice invoice) {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con el ID: " + clientId);
        }

        for (InvoiceDetails detail : invoice.getInvoiceDetails()) {
            Optional<Product> product = productRepository.findById(detail.getProduct().getId());
            if (product.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado con el ID: " + detail.getProduct().getId());
            }

            if (detail.getQuantity() > product.get().getStock()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock insuficiente para el producto: " + product.get().getDescription());
            }

        }

        invoice.setClient(client.get());
        invoice.setCreatedAt(LocalDateTime.now());

        Invoice savedInvoice = invoiceRepository.save(invoice);

        for (InvoiceDetails detail : savedInvoice.getInvoiceDetails()) {
            Product product = productRepository.findById(detail.getProduct().getId()).get();

            product.setStock(product.getStock() - detail.getQuantity());
            productRepository.save(product);

            detail.setInvoice(savedInvoice);
            invoiceDetailsRepository.save(detail);
        }

        return Optional.of(savedInvoice);
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comprobante no encontrado con el ID: " + id);
        }

        invoiceDB.get().setTotal(invoice.getTotal());
        invoiceDB.get().setInvoiceDetails(invoice.getInvoiceDetails());

        invoiceRepository.save(invoiceDB.get());

        return invoiceDB;
    }

    public Optional<Invoice> deleteInvoice(Long id) {
        Optional<Invoice> invoiceDB = invoiceRepository.findById(id);

        if (invoiceDB.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comprobante no encontrado con el ID: " + id);
        }

        List<InvoiceDetails> details = invoiceDB.get().getInvoiceDetails();

        if (details != null && !details.isEmpty()) {
            invoiceDetailsRepository.deleteAll(details);
        }

        invoiceRepository.delete(invoiceDB.get());

        return invoiceDB;
    }

    public List<Invoice> getInvoicesByClient(Long clientId) {
        return invoiceRepository.findByClientId(clientId);
    }
}