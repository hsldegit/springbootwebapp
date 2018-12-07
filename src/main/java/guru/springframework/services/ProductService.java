package guru.springframework.services;


import guru.springframework.domain.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);
}
