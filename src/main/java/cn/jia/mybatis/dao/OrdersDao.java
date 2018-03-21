package cn.jia.mybatis.dao;

import cn.jia.mybatis.entity.Orders;

import java.util.List;

/**
 * 订单接口
 * Created by jia on 2018/3/19.
 */
public interface OrdersDao {
    //一对一查询
    List<Orders> findOrdersWithUser();
    //一对多查询
    List<Orders> findOrdersAndOrderDetailResultMap();
    //延迟加载
    List<Orders> findOrdersWithUserByLazy();
}
