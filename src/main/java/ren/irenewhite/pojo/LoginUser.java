package ren.irenewhite.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ren.irenewhite.validator.Mobile;

import javax.validation.constraints.NotNull;

/**
 * @author DearAhri520
 * @date 2022/6/19
 */
@Data
public class LoginUser {
    @NotNull
    @Mobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
