package VenenciaDario.MiProyectoJava.services;

import VenenciaDario.MiProyectoJava.entities.Product;
import VenenciaDario.MiProyectoJava.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> updateProduct(Long id, Product product) {
        Optional<Product> productDB = productRepository.findById(id);

        if (productDB.isEmpty()) {
            return Optional.empty();
        }

        productDB.get().setDescription(product.getDescription());
        productDB.get().setCode(product.getCode());
        productDB.get().setStock(product.getStock());
        productDB.get().setPrice(product.getPrice());
        productDB.get().setInvoiceDetails(product.getInvoiceDetails());

        productRepository.save(productDB.get());

        return productDB;
    }

    public Optional<Product> deleteProduct(Long id) {
        Optional<Product> productDB = productRepository.findById(id);

        if (productDB.isEmpty()) {
            return Optional.empty();
        }

        productRepository.deleteById(id);

        return productDB;
    }
}