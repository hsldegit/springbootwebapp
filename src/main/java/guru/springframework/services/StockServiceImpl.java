package guru.springframework.services;

import guru.springframework.domain.entity.Stock;
import guru.springframework.domain.entity.StockChange;
import guru.springframework.repositories.StockChangeRepository;
import guru.springframework.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huangshilu
 * @date 2019/3/7 11:24
 * @description
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockChangeRepository stockChangeRepository;

    @Override
    @Transactional(value = "myTransactionManager")
    public boolean updateStock1(Long goodsId, Integer stockChange) {
        Stock stock = stockRepository.findStockByGoodsId(goodsId);
        StockChange stockChangeObj = new StockChange();
        stockChangeObj.setBeforeNum(stock.getInStockNum());
        stockChangeObj.setAfterNum(stock.getInStockNum() + stockChange);
        stockChangeObj.setChangeNum(stockChange);
        stockChangeObj.setProductId(goodsId);
        stockChangeRepository.insert(stockChangeObj);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = stockRepository.updateStock(stock.getId(), stockChange);
        if (result < 1) {
            throw new RuntimeException("没库存了");
        }
        return true;
    }

    @Override
    @Transactional(value = "myTransactionManager")
    public boolean updateStock2(Long goodsId, Integer stockChange) {
        Stock stock = stockRepository.findStockByGoodsId(goodsId);
        StockChange stockChangeObj = new StockChange();
        stockChangeObj.setBeforeNum(stock.getInStockNum());
        stockChangeObj.setAfterNum(stock.getInStockNum() + stockChange);
        stockChangeObj.setChangeNum(stockChange);
        stockChangeObj.setProductId(goodsId);
        stockChangeRepository.insert(stockChangeObj);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = stockRepository.updateStock(stock.getId(), stockChange);
        if (result < 1) {
            throw new RuntimeException("没库存了");
        }
        return true;
    }

    @Override
    @Transactional(value = "myTransactionManager")
    public int updateStockWithVersion1(Long goodsId, Integer stockChange) {
        Stock stock = stockRepository.findStockByGoodsId(goodsId);
        if (stock.getInStockNum() + stockChange < 0) {
            throw new RuntimeException("没库存了");
        }
        StockChange stockChangeObj = new StockChange();
        stockChangeObj.setBeforeNum(stock.getInStockNum());
        stockChangeObj.setAfterNum(stock.getInStockNum() + stockChange);
        stockChangeObj.setProductId(goodsId);
        stockChangeObj.setChangeNum(stockChange);
        stockChangeRepository.insert(stockChangeObj);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = stockRepository.updateStockWithVersion(stock.getId(), stockChange, stock.getVersion());
        if (result < 1) {
            System.out.println("版本号不对 再次尝试");
            stockChangeRepository.delete(stockChangeObj.getId());
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional(value = "myTransactionManager")
    public int updateStockWithVersion2(Long goodsId, Integer stockChange) {
        Stock stock = stockRepository.findStockByGoodsId(goodsId);
        if (stock.getInStockNum() + stockChange < 0) {
            throw new RuntimeException("没库存了");
        }
        StockChange stockChangeObj = new StockChange();
        stockChangeObj.setBeforeNum(stock.getInStockNum());
        stockChangeObj.setAfterNum(stock.getInStockNum() + stockChange);
        stockChangeObj.setProductId(goodsId);
        stockChangeObj.setChangeNum(stockChange);
        stockChangeRepository.insert(stockChangeObj);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = stockRepository.updateStockWithVersion(stock.getId(), stockChange, stock.getVersion());
        if (result < 1) {
            System.out.println("版本号不对 再次尝试");
            stockChangeRepository.delete(stockChangeObj.getId());
            return 1;
        }
        return 0;
    }


    @Override
    @Transactional(value = "myTransactionManager")
    public boolean test(Long goodsId) throws InterruptedException {
        Stock stock1 = stockRepository.findStockByGoodsId(goodsId);
        System.out.println(stock1);
        // int result = stockRepository.updateStock(stock1.getId(), -1);
        Thread.sleep(10000);
        Stock stock2 = stockRepository.findStockByGoodsId(goodsId);
        System.out.println(stock2);
        System.out.println(stock1 == stock2);
        return false;
    }


}

