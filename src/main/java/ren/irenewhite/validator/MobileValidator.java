package ren.irenewhite.validator;

import org.apache.commons.lang3.StringUtils;
import ren.irenewhite.utils.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author DearAhri520
 * @date 2022/6/22
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {
    boolean required = false;

    @Override
    public void initialize(Mobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return ValidatorUtil.isMobileNumber(value);
        } else{
            if(StringUtils.isEmpty(value)){
                return true;
            }
            return ValidatorUtil.isMobileNumber(value);
        }
    }
}
