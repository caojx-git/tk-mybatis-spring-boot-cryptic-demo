package tk.mybatis.springboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据实体电话号码处理注解
 *
 * @author caojx
 * @version $Id: HidePhone.java,v 1.0 2019-05-21 11:41 caojx
 * @date 2019-05-21 11:41
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HidePhone {

    String value() default "";
}