package guru.springframework.dao.custom;

import guru.springframework.domain.entity.Stock;
import org.apache.ibatis.annotations.Param;

public interface StockCustomMapper {

    /**
     * 改库存
     *
     * @param id          库存记录id
     * @param stockChange 库存变动 正数是+ 负数是-
     * @return
     */
    int updateStock(@Param("id") Long id, @Param("stockChange") Integer stockChange);

    /**
     * 改库存 带版本号
     *
     * @param id          库存记录id
     * @param stockChange 库存变动 正数是+ 负数是-
     * @param version     数据版本号
     * @return
     */
    int updateStockWithVersion(@Param("id") Long id, @Param("stockChange") Integer stockChange,
                               @Param("version") Long version);

    /**
     *
     * @param productId
     * @return
     */
    Stock findByGoodsId(@Param("productId") Long  productId);

    Stock findByGoodsId1(@Param("productId") Long  productId);

}