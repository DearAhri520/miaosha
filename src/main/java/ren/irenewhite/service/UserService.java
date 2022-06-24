package ren.irenewhite.service;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import ren.irenewhite.dao.UserDao;
import ren.irenewhite.domain.User;
import ren.irenewhite.exception.GlobalException;
import ren.irenewhite.pojo.LoginUser;
import ren.irenewhite.redis.RedisManager;
import ren.irenewhite.redis.key.UserKey;
import ren.irenewhite.result.CodeMsg;
import ren.irenewhite.utils.MD5Util;
import ren.irenewhite.utils.UUIDUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

/**
 * @author DearAhri520
 * @date 2022/5/31
 */
@Service
public class UserService {
    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    UserDao userDao;

    @Autowired
    RedisManager redisService;

    /**
     * 根据用户id从数据库获取一个用户
     *
     * @param id 用户id
     * @return 获取的用户
     */
    public User getById(long id) {
        return userDao.selectByPrimaryKey(id);
    }

    public User getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        User user = redisService.get(UserKey.token, token, User.class);
        if (user != null) {
            addCookie(user, token, response);
        }
        return user;
    }

    /**
     * 插入一个用户
     *
     * @return 影响的行数
     */
    public int insert(User user) {
        return userDao.insert(user);
    }

    public boolean login(HttpServletResponse response, LoginUser loginUser) {
        if (loginUser == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginUser.getMobile();
        User user;
        if ((user = getById(Long.parseLong(mobile))) == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbPass = user.getPassword();
        String salt = user.getSalt();
        if (!MD5Util.formPassToDBPass(loginUser.getPassword(), salt).equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        addCookie(user, UUIDUtil.uuid(), response);
        return true;
    }

    public boolean register(LoginUser user) {
        String salt = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        int cnt = userDao.insert(new User() {{
            setId(Long.parseLong(user.getMobile()));
            setPassword(MD5Util.formPassToDBPass(user.getPassword(), salt));
            setSalt(salt);
        }});
        return cnt == 1;
    }

    /**
     * 生成token
     *
     * @param user     用户
     * @param response 返回的响应
     */
    private void addCookie(User user, String token, HttpServletResponse response) {
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
