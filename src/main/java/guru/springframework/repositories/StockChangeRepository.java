package guru.springframework.repositories;

import guru.springframework.dao.StockChangeMapper;
import guru.springframework.dao.custom.StockChangeCustomMapper;
import guru.springframework.domain.entity.StockChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author huangshilu
 * @date 2019/3/6 16:20
 * @description
 */
@Repository
public class StockChangeRepository {

    @Autowired
    private StockChangeMapper stockChangeMapper;

    @Autowired
    private StockChangeCustomMapper stockChangeCustomMapper;

    public int insert(StockChange stockChange) {
        stockChange.setCreateTime(new Date());
        return stockChangeMapper.insertSelective(stockChange);
    }

    public int delete(Long  id){
        return stockChangeMapper.deleteByPrimaryKey(id);
    }



}
