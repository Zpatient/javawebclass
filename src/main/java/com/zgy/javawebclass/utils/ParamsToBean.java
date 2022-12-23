package com.zgy.javawebclass.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author zgy
 * @create 2022-12-18 19:42
 */
public class ParamsToBean {
    public static <T> T transform(Map params,Class<T> clazz){
        T bean = null;
        try {
            bean = clazz.newInstance();
            BeanUtils.populate(bean, params);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
