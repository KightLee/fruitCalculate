package cn.lixing;


import cn.billing.BillingOrderList;
import cn.billing.DisCountEnum;
import cn.factory.FruitBackendFactory;
import cn.pojo.sku.Apple;
import cn.pojo.sku.FruitParent;
import cn.pojo.sku.Strawberry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FruitTest {

    private final static Logger logger  = Logger.getLogger(FruitTest.class.getName());

    /**
     * 单价计算
     */
    @Test
    public void testStage1() {
        Apple apple = FruitBackendFactory.apple;
        BigDecimal finalPrice = apple.caculatePrice(10L);
        logger.info("计算价格, ### " + finalPrice.doubleValue());
        Assertions.assertEquals(finalPrice, BigDecimal.valueOf(80.0));
    }

    // 折扣计算
    @Test
    public void testStage2() {
        Strawberry strawberry = FruitBackendFactory.strawberry;
        strawberry.setDisCountEnum(DisCountEnum.DISCOUNT_8);
        BigDecimal finalPrice = strawberry.caculatePrice(10L);
        logger.info("计算价格, ### " + finalPrice.doubleValue());
        Assertions.assertEquals(finalPrice.doubleValue(), BigDecimal.valueOf(104.00).doubleValue());
    }

    // 满减
    @Test
    public void testStage3() {
        ArrayList<FruitParent> list = new ArrayList<>(32);
        Stream.generate(FruitBackendFactory::getApple).limit(10).forEach(list::add);
        Stream.generate(() -> {
            Strawberry strawberry = FruitBackendFactory.getStrawberry();
            strawberry.setDisCountEnum(DisCountEnum.DISCOUNT_8);
            return strawberry;
        }).limit(10).forEach(list::add);

        BillingOrderList billingOrderList = new BillingOrderList();
        billingOrderList.setFruitParents(list);
//        BigDecimal pay = billingOrderList.pay();
        BigDecimal pay = billingOrderList.pay(DisCountEnum.DISCOUNT_100);
        logger.info("计算价格, ### " + pay.doubleValue());
        Assertions.assertEquals(pay.doubleValue(), BigDecimal.valueOf(174.00).doubleValue());
    }

}
