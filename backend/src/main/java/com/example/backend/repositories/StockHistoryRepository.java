package com.example.backend.repositories;

import com.example.backend.models.StockHistory;
import com.example.backend.models.vo.StockHistoryVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {
    @Query(nativeQuery = true, value = """
            select SUBSTRING_INDEX(GROUP_CONCAT(price ORDER BY created_at), ',', 1)  open,
               max(price)                                                            high,
               min(price)                                                            low,
               SUBSTRING_INDEX(GROUP_CONCAT(price ORDER BY created_at), ',', -1)     close,
               date(created_at)                                                      time
            from stock_history sh
            where stock_id = ?1
            group by month(created_at), day(created_at)
            order by created_at""")
    List<StockHistoryVo> findByStockId(Long id);
}
