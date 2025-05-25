package cn.billing;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 折扣枚举
 */
@Getter
@NoArgsConstructor
public enum DisCountEnum {
    NONE(0, "无折扣", BigDecimal.ONE),
    // 满减 10块价格
    DISCOUNT_100(100, "满减(100-10)", BigDecimal.valueOf(10)),
    // 八折
    DISCOUNT_8(8, "八折", BigDecimal.valueOf(0.8));

    private Integer discountId;
    private String desc;
    private BigDecimal discount;

    DisCountEnum(Integer discountId, String desc, BigDecimal discount) {
        this.discountId = discountId;
        this.desc = desc;
        this.discount = discount;
    }

}
