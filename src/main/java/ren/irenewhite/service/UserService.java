package ren.irenewhite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ren.irenewhite.dao.UserDAO;
import ren.irenewhite.domain.User;

/**
 * @author DearAhri520
 * @date 2022/5/31
 */
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User getById(int id) {
        return userDAO.getById(id);
    }

    /**
     * 插入一个用户
     *
     * @return 返回是否成功
     */
    //@Transactional(rollbackFor = Exception.class)
    public boolean insert() {
        User u1 = new User();
        u1.setId(2);
        u1.setName("Bobby");
        userDAO.insert(u1);
        User u2 = new User();
        u2.setId(1);
        u2.setName("Lux");
        userDAO.insert(u2);
        return true;
    }
}
