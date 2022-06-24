package ren.irenewhite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.irenewhite.dao.GoodInfoDao;
import ren.irenewhite.pojo.GoodInfo;

import java.util.List;

/**
 * @author DearAhri520
 * @date 2022/6/24
 */
@Service
public class GoodService {

    @Autowired
    GoodInfoDao goodDao;

    public List<GoodInfo> getGoodsInfo(){
        return goodDao.getGoodsInfo();
    }

    public GoodInfo getGoodInfoById(long goodsId) {
        return goodDao.getGoodInfoById(goodsId);
    }
}
