package guru.springframework.repositories;

import guru.springframework.dao.StockMapper;
import guru.springframework.dao.custom.StockCustomMapper;
import guru.springframework.domain.entity.Stock;
import guru.springframework.domain.example.StockExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huangshilu
 * @date 2019/3/6 16:11
 * @description
 */
@Repository
public class StockRepository {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockCustomMapper stockCustomMapper;


    public Stock findStockByGoodsId(Long productId) {
        return stockCustomMapper.findByGoodsId(productId);
    }

    public Stock findStockByGoodsId1(Long productId) {
        return stockCustomMapper.findByGoodsId1(productId);
    }

//    public int update(Stock stock, StockExample stockExample) {
//
//        StockExample.Criteria criteria = stockExample.createCriteria();
//        criteria.ands
//
//        return stockMapper.updateByExample(stock, stockExample);
//    }

    public int updateStock(Long id, Integer stockChange) {
        return stockCustomMapper.updateStock(id, stockChange);
    }

    public int updateStockWithVersion(Long id, Integer stockChange, Long version) {
        return stockCustomMapper.updateStockWithVersion(id, stockChange, version);
    }

}
