package VenenciaDario.MiProyectoJava.controllers;

import VenenciaDario.MiProyectoJava.entities.InvoiceDetails;
import VenenciaDario.MiProyectoJava.services.InvoiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice-details")
public class InvoiceDetailsController {
    @Autowired
    private final InvoiceDetailsService invoiceDetailsService;

    public InvoiceDetailsController(InvoiceDetailsService invoiceDetailsService) {
        this.invoiceDetailsService = invoiceDetailsService;
    }

    @GetMapping()
    public ResponseEntity<List<InvoiceDetails>> getAllInvoiceDetails() {
        return ResponseEntity.ok(invoiceDetailsService.getAllInvoiceDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<InvoiceDetails>> getInvoiceDetail(@PathVariable Long id) {
        Optional<InvoiceDetails> invoiceDetail = invoiceDetailsService.getInvoiceDetail(id);

        if (invoiceDetail.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(invoiceDetail);
    }

    @PostMapping("/invoice/{invoiceId}/product/{productId}")
    public ResponseEntity<InvoiceDetails> saveInvoiceDetail(
            @PathVariable Long invoiceId,
            @PathVariable Long productId,
            @RequestBody InvoiceDetails invoiceDetail) {
        Optional<InvoiceDetails> savedInvoiceDetail = invoiceDetailsService.saveInvoiceDetail(invoiceId, productId, invoiceDetail);

        if (savedInvoiceDetail.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Factura o producto no encontrado
        }

        return ResponseEntity.ok(savedInvoiceDetail.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<InvoiceDetails>> updateInvoiceDetail(
            @PathVariable Long id,
            @RequestBody InvoiceDetails invoiceDetail) {
        Optional<InvoiceDetails> updatedInvoiceDetail = invoiceDetailsService.updateInvoiceDetail(id, invoiceDetail);

        if (updatedInvoiceDetail.isEmpty()) {
            return ResponseEntity.notFound().build(); // Detalle no encontrado
        }

        return ResponseEntity.ok(updatedInvoiceDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<InvoiceDetails>> deleteInvoiceDetail(@PathVariable Long id) {
        Optional<InvoiceDetails> deletedInvoiceDetail = invoiceDetailsService.deleteInvoiceDetail(id);

        if (deletedInvoiceDetail.isEmpty()) {
            return ResponseEntity.notFound().build(); // Detalle no encontrado
        }

        return ResponseEntity.ok(deletedInvoiceDetail);
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<List<InvoiceDetails>> getInvoiceDetailsByInvoice(@PathVariable Long invoiceId) {
        List<InvoiceDetails> invoiceDetails = invoiceDetailsService.getInvoiceDetailsByInvoice(invoiceId);
        return ResponseEntity.ok(invoiceDetails);
    }
}