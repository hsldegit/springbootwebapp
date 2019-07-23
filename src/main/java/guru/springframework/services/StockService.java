package guru.springframework.services;

/**
 * @author huangshilu
 * @date 2019/3/7 11:24
 * @description
 */
public interface StockService {

    boolean updateStock1(Long goodsId, Integer stockChange);

    boolean updateStock2(Long goodsId, Integer stockChange);

    int updateStockWithVersion1(Long goodsId, Integer stockChange);

    int updateStockWithVersion2(Long goodsId, Integer stockChange);

    boolean test(Long  goodsId) throws InterruptedException;

    void method1();
}
