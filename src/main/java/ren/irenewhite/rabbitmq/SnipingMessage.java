package ren.irenewhite.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import ren.irenewhite.domain.SnipingGood;
import ren.irenewhite.domain.User;

/**
 * @author DearAhri520
 * @date 2022/7/21
 */
@Data
@AllArgsConstructor
public class SnipingMessage {
    private User user;
    private long snipingGoodId;
}
