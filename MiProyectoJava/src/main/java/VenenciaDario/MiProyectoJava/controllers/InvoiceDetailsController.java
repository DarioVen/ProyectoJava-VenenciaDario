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
        List<InvoiceDetails> invoiceDetails = invoiceDetailsService.getAllInvoiceDetails();
        invoiceDetails.forEach(InvoiceDetails::calculateSubtotal);
        return ResponseEntity.ok(invoiceDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDetails> getInvoiceDetail(@PathVariable Long id) {
        Optional<InvoiceDetails> invoiceDetail = invoiceDetailsService.getInvoiceDetail(id);

        if (invoiceDetail.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        invoiceDetail.get().calculateSubtotal();
        return ResponseEntity.ok(invoiceDetail.get());
    }

    @PostMapping("/invoice/{invoiceId}/product/{productId}")
    public ResponseEntity<?> saveInvoiceDetail(
            @PathVariable Long invoiceId,
            @PathVariable Long productId,
            @RequestBody InvoiceDetails invoiceDetail) {
        try {
            Optional<InvoiceDetails> savedInvoiceDetail = invoiceDetailsService.saveInvoiceDetail(invoiceId, productId, invoiceDetail);

            if (savedInvoiceDetail.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            savedInvoiceDetail.get().calculateSubtotal();
            return ResponseEntity.ok(savedInvoiceDetail.get());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDetails> updateInvoiceDetail(
            @PathVariable Long id,
            @RequestBody InvoiceDetails invoiceDetail) {
        Optional<InvoiceDetails> updatedInvoiceDetail = invoiceDetailsService.updateInvoiceDetail(id, invoiceDetail);

        if (updatedInvoiceDetail.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        updatedInvoiceDetail.get().calculateSubtotal();
        return ResponseEntity.ok(updatedInvoiceDetail.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoiceDetail(@PathVariable Long id) {
        Optional<InvoiceDetails> deletedInvoiceDetail = invoiceDetailsService.deleteInvoiceDetail(id);

        if (deletedInvoiceDetail.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<List<InvoiceDetails>> getInvoiceDetailsByInvoice(@PathVariable Long invoiceId) {
        List<InvoiceDetails> invoiceDetails = invoiceDetailsService.getInvoiceDetailsByInvoice(invoiceId);
        // Recalcula el subtotal para cada detalle
        invoiceDetails.forEach(InvoiceDetails::calculateSubtotal);
        return ResponseEntity.ok(invoiceDetails);
    }
}