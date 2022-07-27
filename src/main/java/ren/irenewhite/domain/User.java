package ren.irenewhite.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * user
 *
 * @author DearAhri520
 */
@Data
public class User implements Serializable {
    /**
     * 用户id,手机号
     */
    private Long id;

    private String nickname;

    /**
     * MD5(MD5(pass明文+固定salt)+固定salt)
     */
    private String password;

    private String salt;

    /**
     * 头像,云存储的ID
     */
    private String head;

    /**
     * 注册时间
     */
    private Date registerDate;

    /**
     * 上次登录时间
     */
    private Date lastLoginDate;

    /**
     * 登录次数
     */
    private Integer loginCount;

    private static final long serialVersionUID = 7194793218021438329L;
}