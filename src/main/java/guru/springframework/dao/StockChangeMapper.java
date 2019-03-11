package guru.springframework.dao;

import guru.springframework.domain.entity.StockChange;
import guru.springframework.domain.example.StockChangeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockChangeMapper {

    int countByExample(StockChangeExample example);

    int deleteByExample(StockChangeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StockChange record);

    int insertSelective(StockChange record);

    List<StockChange> selectByExample(StockChangeExample example);

    StockChange selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StockChange record, @Param("example") StockChangeExample example);

    int updateByExample(@Param("record") StockChange record, @Param("example") StockChangeExample example);

    int updateByPrimaryKeySelective(StockChange record);

    int updateByPrimaryKey(StockChange record);

}