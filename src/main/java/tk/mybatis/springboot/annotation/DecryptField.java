package tk.mybatis.springboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据库实体字段属性解密注解
 *
 * @author caojx
 * @version $Id: DecryptField.java,v 1.0 2019-05-20 16:48 caojx
 * @date 2019-05-20 16:48
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DecryptField {

    String value() default "";
}