package ren.irenewhite.configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author DearAhri520
 * @date 2022/7/26
 */
@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha kaptchaProducer() {
        Properties properties = new Properties();
        /*（选择性的设置，不是每个都需要设置）*/
        /*是否需要外边框（yes,no）*/
        properties.put("kaptcha.border", "yes");
        /*边框厚度，合法值：>0*/
        properties.put("kaptcha.border.thickness", "2");
        /*框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue*/
        properties.put("kaptcha.border.color", "blue");
        /*字体颜色*/
        properties.put("kaptcha.textproducer.font.color", "black");
        /*渲染效果：水纹：WaterRipple；鱼眼：FishEyeGimpy；阴影：ShadowGimpy
         *properties.put("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.FishEyeGimpy");
         *配置干扰线–噪点(只改变最后一个单词：NoNoise,DefaultNoise)*/
        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");
        /*干扰 颜色，合法值： r,g,b 或者 white,black,blue.*/
        properties.put("kaptcha.noise.color", "yellow");
        /*设置图片及字体*/
        properties.put("kaptcha.image.width", "90");
        properties.put("kaptcha.image.height", "33");
        properties.put("kaptcha.textproducer.font.size", "25");
        /*生成验证码的长度（也就是要几个字）*/
        properties.put("kaptcha.textproducer.char.length", "4");
        /*文字间隔*/
        properties.put("kaptcha.textproducer.char.space", "5");
        /*和登录框背景颜色一致*/
        /*背景颜色渐变，开始颜色*/
        properties.put("kaptcha.background.clear.from", "247,247,247");
        /*背景颜色渐变， 结束颜色*/
        properties.put("kaptcha.background.clear.to", "247,247,247");
        properties.put("kaptcha.word.impl", "com.google.code.kaptcha.text.impl.DefaultWordRenderer");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
