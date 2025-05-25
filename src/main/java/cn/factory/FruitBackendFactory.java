package cn.factory;

import cn.pojo.sku.Apple;
import cn.pojo.sku.Mango;
import cn.pojo.sku.Strawberry;

import java.math.BigDecimal;

/**
 * 进货和定价
 */
public class FruitBackendFactory {

    public static Apple apple;
    public static Strawberry strawberry;
    public static Mango mango;


    static {
        synchronized (FruitBackendFactory.class) {
            if (strawberry == null) {
                strawberry = new Strawberry();
                strawberry.setPrice(BigDecimal.valueOf(13.0));
            }

            if (apple == null) {
                apple = new Apple();
                apple.setPrice(BigDecimal.valueOf(8.0));
            }

            if (mango == null) {
                mango = new Mango();
                mango.setPrice(BigDecimal.valueOf(20.0));
            }
        }
    }

    public static Apple getApple() {
        return (Apple) apple.clone();
    }

    public static Strawberry getStrawberry() {
        return (Strawberry) strawberry.clone();
    }

    public static Mango getMango() {
        return (Mango) mango.clone();
    }
}
