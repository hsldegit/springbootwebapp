package guru.springframework.services;

import guru.springframework.domain.entity.Product;
import guru.springframework.repositories.ProductRepository;
import guru.springframework.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    //下面俩共性 父事务捕获本方法异常不抛出不会影响父事务提交
    @Transactional(propagation = Propagation.NESTED) //这个是单独事务 与父事务依赖 但是要和父事务一起提交 父事务回滚 这个也会回滚
    //@Transactional(propagation=Propagation.REQUIRES_NEW) //这个与父事务完全独立 父事务调用这个方法成功 以后 父事务回滚这个不会回滚
    public void method2() {
        int result = stockRepository.updateStock(1L, 10);
        System.out.println("result=" + result);
        //throw new RuntimeException();
    }
}
