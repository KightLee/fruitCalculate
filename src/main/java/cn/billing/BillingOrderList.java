package cn.billing;

import cn.pojo.sku.FruitParent;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 总价计算类
 */
public class BillingOrderList {

    @Setter
    private List<FruitParent> fruitParents;

    public BigDecimal pay() {
        Map<FruitParent, Long> fruitParentLongMap = splitOrderList();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Map.Entry<FruitParent, Long> entry : fruitParentLongMap.entrySet()) {
            totalPrice = totalPrice.add(entry.getKey().caculatePrice(entry.getValue()));
        }
        return totalPrice;
    }

    /**
     * 总满减计费
     * @param disCountEnum
     * @return
     */
    public BigDecimal pay(DisCountEnum disCountEnum) {
        BigDecimal totalPrice = pay();
        if (Objects.requireNonNull(disCountEnum) == DisCountEnum.DISCOUNT_100) {
            return totalPrice.subtract(DisCountEnum.DISCOUNT_100.getDiscount());
        }
        // todo 基于总价的优惠可以继续if扩展
        return totalPrice;
    }

    public Map<FruitParent, Long> splitOrderList() {
        if (fruitParents != null && !fruitParents.isEmpty()) {
            return fruitParents.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }
        return Map.of();
    }

}
