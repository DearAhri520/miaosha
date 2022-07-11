package ren.irenewhite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.irenewhite.dao.GoodDao;
import ren.irenewhite.domain.Good;

import java.util.List;

/**
 * @author DearAhri520
 * @date 2022/6/24
 */
@Service
public class GoodService{

    @Autowired
    GoodDao goodDao;

    /**
     * 减少商品库存
     *
     * @param id 商品id
     */
    public int reduceStockById(Long id) {
        return goodDao.updateStockCountById(id);
    }

    public List<Good> getGoods(){
        return goodDao.getGoods();
    }
}
