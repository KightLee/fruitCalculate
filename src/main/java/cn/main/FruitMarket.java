package cn.main;

import cn.billing.BillingOrderList;
import cn.billing.DisCountEnum;
import cn.factory.FruitBackendFactory;
import cn.pojo.sku.FruitParent;
import cn.pojo.sku.Strawberry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FruitMarket {

    private final static Logger logger = Logger.getLogger(FruitMarket.class.getName());

    List<FruitParent> fruitList = new ArrayList<>(32);

    // 苹果斤数
    private final int appleNum = 10;

    // 草莓斤数
    private final int strawberryNum = 10;

    // 芒果斤数
    private final  int mangoNum = 10;

    public static void main(String[] args) {
        FruitMarket fruitMarket = new FruitMarket();
        fruitMarket.stage4();
    }

    /**
     * 第一题 --- 苹果和草莓
     */
    private void stage1() {
        Stream.generate(FruitBackendFactory::getApple).limit(appleNum).forEach(fruitList::add);
        Stream.generate(FruitBackendFactory::getStrawberry).limit(strawberryNum).forEach(fruitList::add);

        // 计费
        BillingOrderList billingOrderList = new BillingOrderList();
        billingOrderList.setFruitParents(fruitList);
        BigDecimal pay = billingOrderList.pay();
        logger.info("stage1: " + pay);
    }

    /**
     * 第二题 --- 苹果和草莓、芒果
     */
    private void stage2() {
        Stream.generate(FruitBackendFactory::getApple).limit(appleNum).forEach(fruitList::add);
        Stream.generate(FruitBackendFactory::getStrawberry).limit(strawberryNum).forEach(fruitList::add);
        Stream.generate(FruitBackendFactory::getMango).limit(mangoNum).forEach(fruitList::add);
        // 计费
        BillingOrderList billingOrderList = new BillingOrderList();
        billingOrderList.setFruitParents(fruitList);
        BigDecimal pay = billingOrderList.pay();
        logger.info("stage1: " + pay);
    }

    /**
     * 第三题 --- 苹果和草莓、芒果（草莓打八折）
     */
    private void stage3() {
        Stream.generate(FruitBackendFactory::getApple).limit(appleNum).forEach(fruitList::add);
        Stream.generate(() -> {
            Strawberry strawberry = FruitBackendFactory.getStrawberry();
            strawberry.setDisCountEnum(DisCountEnum.DISCOUNT_8);
            return strawberry;
        }).limit(strawberryNum).forEach(fruitList::add);
        Stream.generate(FruitBackendFactory::getMango).limit(mangoNum).forEach(fruitList::add);
        // 计费
        BillingOrderList billingOrderList = new BillingOrderList();
        billingOrderList.setFruitParents(fruitList);
        BigDecimal pay = billingOrderList.pay();
        logger.info("stage3: " + pay);
    }

    /**
     * 第四题。苹果和草莓、芒果（草莓打八折）// 总价满100 - 10
     */
    private void stage4() {
        Stream.generate(FruitBackendFactory::getApple).limit(appleNum).forEach(fruitList::add);
        Stream.generate(() -> {
            Strawberry strawberry = FruitBackendFactory.getStrawberry();
            strawberry.setDisCountEnum(DisCountEnum.DISCOUNT_8);
            return strawberry;
        }).limit(strawberryNum).forEach(fruitList::add);
        Stream.generate(FruitBackendFactory::getMango).limit(mangoNum).forEach(fruitList::add);
        // 计费
        BillingOrderList billingOrderList = new BillingOrderList();
        billingOrderList.setFruitParents(fruitList);
        BigDecimal pay = billingOrderList.pay(DisCountEnum.DISCOUNT_100);
        logger.info("stage4: " + pay);
    }
}
