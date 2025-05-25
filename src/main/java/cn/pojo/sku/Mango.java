package cn.pojo.sku;

import java.math.BigDecimal;

public class Mango extends FruitParent {

    private final static String name = "芒果";
    private final static Integer ID = 3;

    public Mango() {
        super(ID, name);
    }

    @Override
    public BigDecimal calculateDiscount(BigDecimal totalPrice) {
        return totalPrice;
    }
}
