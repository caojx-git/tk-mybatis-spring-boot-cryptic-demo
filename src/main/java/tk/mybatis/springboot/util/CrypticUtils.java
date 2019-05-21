package tk.mybatis.springboot.util;

import org.apache.commons.lang3.StringUtils;
import tk.mybatis.springboot.annotation.DecryptField;
import tk.mybatis.springboot.annotation.EncryptField;
import tk.mybatis.springboot.annotation.HidePhone;

import java.lang.reflect.Field;

/**
 * 对象加解密工具
 * 其子类可以通过调用decryptField(T t)方法实现自加密，返回参数类型；
 * 调用encryptField(T t)实现自解密，返回参数类型；
 * encrypt对注解{@link EncryptField}字段有效；
 * decrypt对注解{@link DecryptField}字段有效
 *
 * @author caojx
 * @version $Id: CrypticUtils.java,v 1.0 2019-05-21 11:47 caojx
 * @date 2019-05-21 11:47
 */
public class CrypticUtils {

    /**
     * 对含注解字段解密
     *
     * @param t
     * @param <T>
     */
    public static <T> void decryptField(T t, String password) {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        try {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(DecryptField.class) && field.getType().toString().endsWith("String")) {
                        field.setAccessible(true);
                        String fieldValue = (String) field.get(t);
                        if (StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, AESUtils.decrypt(fieldValue, password));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对含注解字段加密
     *
     * @param t
     * @param <T>
     */
    public static <T> void encryptField(T t, String password) {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        try {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(EncryptField.class) && field.getType().toString().endsWith("String")) {
                        field.setAccessible(true);
                        String fieldValue = (String) field.get(t);
                        if (StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, AESUtils.encrypt(fieldValue, password));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 隐藏号码中间4位
     *
     * @param t
     * @param <T>
     */
    public static <T> void hidePhone(T t) {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        try {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(HidePhone.class) && field.getType().toString().endsWith("String")) {
                        field.setAccessible(true);
                        String fieldValue = (String) field.get(t);
                        if (StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, StringUtils.overlay(fieldValue, "****", 3, 7));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}