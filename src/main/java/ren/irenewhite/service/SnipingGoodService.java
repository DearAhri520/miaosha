package ren.irenewhite.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ren.irenewhite.dao.SnipingGoodDao;
import ren.irenewhite.domain.SnipingGood;
import ren.irenewhite.redis.RedisManager;
import ren.irenewhite.redis.key.SnipingGoodKey;

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

    @Autowired
    RedisManager redisManager;

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
        SnipingGood snipingGood;
        /*redis获取*/
        snipingGood = redisManager.get(SnipingGoodKey.SnipingGood, id + "", SnipingGood.class);
        if (ObjectUtils.isNotEmpty(snipingGood)) {
            return snipingGood;
        }
        /*redis获取为空,从数据库中获取并添加入缓存*/
        snipingGood = snipingGoodDao.getSnipingGoodById(id);
        if (ObjectUtils.isNotEmpty(snipingGood)) {
            redisManager.set(SnipingGoodKey.SnipingGood, id + "", snipingGood);
        }
        return snipingGood;
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
