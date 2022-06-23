package ren.irenewhite.exception;

import lombok.Getter;
import ren.irenewhite.result.CodeMsg;

/**
 * @author DearAhri520
 * @date 2022/6/23
 */
public class GlobalException extends RuntimeException {
    @Getter
    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }
}
