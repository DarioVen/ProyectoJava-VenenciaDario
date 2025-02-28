package VenenciaDario.MiProyectoJava.controllers;

import VenenciaDario.MiProyectoJava.entities.Invoice;
import VenenciaDario.MiProyectoJava.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping()
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Invoice>> getInvoice(@PathVariable Long id) {
        Optional<Invoice> invoice = invoiceService.getInvoice(id);

        if (invoice.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(invoice);
    }

    @PostMapping("/client/{clientId}")
    public ResponseEntity<Invoice> saveInvoice(@PathVariable Long clientId, @RequestBody Invoice invoice) {
        Optional<Invoice> savedInvoice = invoiceService.saveInvoice(clientId, invoice);

        if (savedInvoice.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Cliente no encontrado
        }

        return ResponseEntity.ok(savedInvoice.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Invoice>> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        Optional<Invoice> updatedInvoice = invoiceService.updateInvoice(id, invoice);

        if (updatedInvoice.isEmpty()) {
            return ResponseEntity.notFound().build(); // Factura no encontrada
        }

        return ResponseEntity.ok(updatedInvoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Invoice>> deleteInvoice(@PathVariable Long id) {
        Optional<Invoice> deletedInvoice = invoiceService.deleteInvoice(id);

        if (deletedInvoice.isEmpty()) {
            return ResponseEntity.notFound().build(); // Factura no encontrada
        }

        return ResponseEntity.ok(deletedInvoice);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Invoice>> getInvoicesByClient(@PathVariable Long clientId) {
        List<Invoice> invoices = invoiceService.getInvoicesByClient(clientId);
        return ResponseEntity.ok(invoices);
    }
}