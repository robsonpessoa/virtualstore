package br.org.rpessoa.vstore.controller;

import br.org.rpessoa.vstore.dao.GenericDAO;
import br.org.rpessoa.vstore.dao.ProductDAO;
import br.org.rpessoa.vstore.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ProductController {

    @PostMapping("/products")
    public ResponseEntity<?> add(@RequestBody Product product) {
        GenericDAO<Product> productGenericDAO = new GenericDAO<>();
        productGenericDAO.saveOrUpdate(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/products")
    public ResponseEntity<Collection<Product>> list() {
        ProductDAO productDAO = new ProductDAO();
        return ResponseEntity.ok(productDAO.listAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> get(@PathVariable(value = "id") Integer id) {
        ProductDAO productDAO = new ProductDAO();
        return ResponseEntity.ok(productDAO.findById(Product.class, id));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                          @RequestBody Product product) {
        ProductDAO productDAO = new ProductDAO();
        product.setId(id);
        productDAO.saveOrUpdate(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.remove(Product.class, id);
        return ResponseEntity.noContent().build();
    }
}
