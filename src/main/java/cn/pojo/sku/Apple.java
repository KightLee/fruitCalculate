package cn.pojo.sku;

import java.math.BigDecimal;
import java.util.Optional;

public class Apple extends FruitParent {

    private final static String name = "苹果";
    private final static Integer ID = 1;

    public Apple() {
        super(ID, name);

    }

    public BigDecimal calculateDiscount(BigDecimal totalPrice) {
        return Optional.ofNullable(totalPrice)
                .filter(info -> info.compareTo(BigDecimal.ZERO)  > 0)
                .map(info -> info.multiply(disCountEnum.getDiscount()))
                .orElse(BigDecimal.ZERO);
    }

}
