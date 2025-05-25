package cn.pojo.sku;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class Strawberry extends FruitParent {

    private final static String name = "草莓";
    private final static Integer ID = 2;

    public Strawberry() {
        super(ID, name);
    }

    /**
     * 打八折的草莓
     * @param totalPrice
     * @return
     */
    @Override
    protected BigDecimal calculateDiscount(BigDecimal totalPrice) {
        return Optional.ofNullable(totalPrice)
                .filter(info -> info.compareTo(BigDecimal.ZERO)  > 0)
                .map(info -> info.multiply(disCountEnum.getDiscount()))
                .map(info -> info.setScale(2, RoundingMode.HALF_UP))
                .orElse(BigDecimal.ZERO);
    }
}
