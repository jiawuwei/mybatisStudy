package cn.jia.mybatis.entity;

/**
 * 订单明细表
 * 订单明细表和订单表是一对一
 * 订单表和明细表是一对多
 * 所以 订单表：明细表 1：n
 *
 * 订单明细表和物品表是一对一 因为一个明细对应一个物品
 * 物品表和订单表明细一对多  一个物品可在多个明细里出现
 * 所以订单明细表：物品表 1:n
 *
 * 一个用户可买多个不同商品 1:n
 * 一个商品可被多个用户购买 1:n
 * 所以用户:商品 n:m
 *
 * Created by jia on 2018/3/19.
 */
public class OrderDetail {
    private Integer id;
    private Integer ordersId;
    private Integer itemsId;
    private Integer itemsNum;
    private Items items; //一个明细对应一个商品

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getItemsId() {
        return itemsId;
    }

    public void setItemsId(Integer itemsId) {
        this.itemsId = itemsId;
    }

    public Integer getItemsNum() {
        return itemsNum;
    }

    public void setItemsNum(Integer itemsNum) {
        this.itemsNum = itemsNum;
    }
}
