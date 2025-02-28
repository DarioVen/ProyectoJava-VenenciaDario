package VenenciaDario.MiProyectoJava.services;

import VenenciaDario.MiProyectoJava.entities.Invoice;
import VenenciaDario.MiProyectoJava.entities.InvoiceDetails;
import VenenciaDario.MiProyectoJava.entities.Product;
import VenenciaDario.MiProyectoJava.repositories.InvoiceDetailsRepository;
import VenenciaDario.MiProyectoJava.repositories.InvoiceRepository;
import VenenciaDario.MiProyectoJava.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetailsService {
    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    public Optional<InvoiceDetails> saveInvoiceDetail(Long invoiceId, Long productId, InvoiceDetails invoiceDetail) {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        Optional<Product> product = productRepository.findById(productId);

        if (invoice.isEmpty() || product.isEmpty()) {
            return Optional.empty();
        }

        invoiceDetail.setInvoice(invoice.get());
        invoiceDetail.setProduct(product.get());
        invoiceDetail.setPrice(product.get().getPrice());

        double subtotal = invoiceDetail.getQuantity() * invoiceDetail.getPrice();
        Invoice currentInvoice = invoice.get();
        currentInvoice.setTotal(currentInvoice.getTotal() + subtotal);

        invoiceDetailsRepository.save(invoiceDetail);
        invoiceRepository.save(currentInvoice);

        return Optional.of(invoiceDetail);
    }

    public Optional<InvoiceDetails> getInvoiceDetail(Long id) {
        return invoiceDetailsRepository.findById(id);
    }

    public List<InvoiceDetails> getAllInvoiceDetails() {
        return invoiceDetailsRepository.findAll();
    }

    public Optional<InvoiceDetails> updateInvoiceDetail(Long id, InvoiceDetails invoiceDetail) {
        Optional<InvoiceDetails> invoiceDetailDB = invoiceDetailsRepository.findById(id);

        if (invoiceDetailDB.isEmpty()) {
            return Optional.empty();
        }

        Invoice invoice = invoiceDetailDB.get().getInvoice();

        double oldSubtotal = invoiceDetailDB.get().getQuantity() * invoiceDetailDB.get().getPrice();
        invoice.setTotal(invoice.getTotal() - oldSubtotal);

        invoiceDetailDB.get().setQuantity(invoiceDetail.getQuantity());
        invoiceDetailDB.get().setPrice(invoiceDetail.getPrice());

        double newSubtotal = invoiceDetailDB.get().getQuantity() * invoiceDetailDB.get().getPrice();
        invoice.setTotal(invoice.getTotal() + newSubtotal);

        invoiceDetailsRepository.save(invoiceDetailDB.get());
        invoiceRepository.save(invoice);

        return invoiceDetailDB;
    }

    public Optional<InvoiceDetails> deleteInvoiceDetail(Long id) {
        Optional<InvoiceDetails> invoiceDetailDB = invoiceDetailsRepository.findById(id);

        if (invoiceDetailDB.isEmpty()) {
            return Optional.empty();
        }

        Invoice invoice = invoiceDetailDB.get().getInvoice();

        double subtotal = invoiceDetailDB.get().getQuantity() * invoiceDetailDB.get().getPrice();
        invoice.setTotal(invoice.getTotal() - subtotal);

        invoiceDetailsRepository.deleteById(id);
        invoiceRepository.save(invoice);

        return invoiceDetailDB;
    }

    public List<InvoiceDetails> getInvoiceDetailsByInvoice(Long invoiceId) {
        return invoiceDetailsRepository.findByInvoiceId(invoiceId);
    }
}