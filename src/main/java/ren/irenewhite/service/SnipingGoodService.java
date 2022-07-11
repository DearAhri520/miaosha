package ren.irenewhite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.irenewhite.dao.SnipingGoodDao;
import ren.irenewhite.domain.Good;
import ren.irenewhite.domain.SnipingGood;

import java.util.List;

/**
 * @author DearAhri520
 * @date 2022/6/26
 * <p>
 * 秒杀商品service
 */
@Service
public class SnipingGoodService {
    @Autowired
    SnipingGoodDao snipingGoodDao;

    /**
     * 获取所有秒杀商品信息
     *
     * @return 所有秒杀商品信息
     */
    public List<SnipingGood> getSnipingGoods() {
        return snipingGoodDao.getSnipingGoods();
    }

    /**
     * 根据秒杀商品id获取秒杀商品信息
     *
     * @param id 秒杀商品id
     * @return 秒杀商品信息
     */
    public SnipingGood getSnipingGoodById(long id) {
        return snipingGoodDao.getSnipingGoodById(id);
    }

    /**
     * 减少秒杀商品库存
     *
     * @param id 商品id
     */
    public int reduceStockById(Long id) {
        return snipingGoodDao.updateSnipingStockCountById(id);
    }
}
