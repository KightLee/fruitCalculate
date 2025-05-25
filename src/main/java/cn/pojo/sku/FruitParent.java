package cn.pojo.sku;

import cn.billing.DisCountEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public abstract class FruitParent implements Cloneable{

    private final String fruitName;

    @Getter
    private final Integer fruitId;

    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    public DisCountEnum disCountEnum = DisCountEnum.NONE;

    public FruitParent(Integer fruitId, String fruitName) {
        this.fruitId = fruitId;
        this.fruitName = fruitName;
    }


    // 水果SKU计算模版
    public BigDecimal caculatePrice(Long count) {
        return Optional.ofNullable(count)
                .filter(info -> info > 0)
                .map(BigDecimal::new)
                .map(info -> info.multiply(this.price))
                .map(info -> disCountEnum != DisCountEnum.NONE ? calculateDiscount(info) : info)
                .orElse(BigDecimal.ZERO);
    }

    /**
     * 100 减 10
     * @param count
     * @return
     */
    public BigDecimal caculateReducePrice(Integer count) {
        return Optional.ofNullable(count)
                .filter(info -> info > 0)
                .map(BigDecimal::new)
                .map(info -> info.multiply(this.price))
                .map(info -> disCountEnum != DisCountEnum.NONE ? calculateDiscount(info) : info)
                .map(info -> info.subtract(DisCountEnum.DISCOUNT_100.getDiscount()).setScale(2,  RoundingMode.HALF_UP))
                .orElse(BigDecimal.ZERO);
    }

    // 子类扩展折扣和满减
    protected abstract BigDecimal calculateDiscount(BigDecimal totalPrice);

    @Override
    public FruitParent clone() {
        try {
            FruitParent clone = (FruitParent) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
