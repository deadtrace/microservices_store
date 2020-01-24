package com.github.boyvita.services.catalog.controller;

import com.github.boyvita.services.catalog.exception.NoEntityException;
import com.github.boyvita.services.catalog.model.Item;
import com.github.boyvita.services.catalog.model.Product;
import com.github.boyvita.services.catalog.repo.ItemRepository;
import com.github.boyvita.services.catalog.repo.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@ComponentScan(basePackages = "com.github.boyvita.services.catalog")
@EnableJpaRepositories(basePackages = "com.github.boyvita.services.catalog.repo")
@EntityScan(basePackages = "com.github.boyvita.services.catalog.model")
public class CatalogController {

    private ProductRepository productRepository;
    private ItemRepository itemRepository;

    @Autowired
    private RabbitMQReceiver rabbitMQReceiver;

    @Autowired
    public CatalogController(ProductRepository productRepository, ItemRepository itemRepository) {
        this.productRepository = productRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/product")
    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Product product) {
        return product;
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product) throws NoEntityException {
        Long id = product.getProductId();
        Product productFromDb = productRepository.findById(id).orElseThrow(() -> new NoEntityException(id));
        BeanUtils.copyProperties(product, productFromDb, "id");
        return productRepository.save(productFromDb);

    }

    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable("id") Product product) {
        productRepository.delete(product);
        return product;
    }

    @GetMapping("/item")
    public List<Item> listItem() {
        return itemRepository.findAll();
    }

    @GetMapping("/item/{id}")
    public Item getItem(@PathVariable("id") Item item) {
        return item;
    }

    @PostMapping("/item")
    public Item addItem(@RequestBody Item item) throws NoEntityException {
        Long productId = item.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoEntityException(productId));
        if (item.getQuantity() <= product.getQuantity()) {
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);
            return itemRepository.save(item);
        }
        return null;
    }

    @PutMapping("/item")
    public Item updateItem(@RequestBody Item item) throws NoEntityException {
        Long id = item.getItemId();
        Item itemFromDb = itemRepository.findById(id).orElseThrow(() -> new NoEntityException(id));
        Long diff = item.getQuantity() - itemFromDb.getQuantity();
        if (!item.getProductId().equals(itemFromDb.getProductId())) {
            return null;
        }
        Long productId = item.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoEntityException(productId));
        if (diff <= product.getQuantity()) {
            product.setQuantity(product.getQuantity() - diff);
            productRepository.save(product);
            BeanUtils.copyProperties(item, itemFromDb, "id");
            return itemRepository.save(itemFromDb);
        }
        return null;

    }

    @DeleteMapping("/item/{id}")
    public Item deleteItem(@PathVariable("id") Item item) {
        itemRepository.delete(item);
        return item;
    }

}
