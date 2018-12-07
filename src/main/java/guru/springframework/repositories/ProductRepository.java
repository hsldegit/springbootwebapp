package guru.springframework.repositories;

import guru.springframework.dao.ProductMapper;
import guru.springframework.domain.entity.Product;
import guru.springframework.domain.example.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> findAll() {
        ProductExample example = new ProductExample();
        return productMapper.selectByExample(example);
    }

    public Product findById(int id) {
        return productMapper.selectByPrimaryKey(id);
    }

    public Product save(Product product) {
        productMapper.insert(product);
        return productMapper.selectByPrimaryKey(product.getId());
    }

}
