package cn.jia.mybatis.entity;

import java.util.Date;
import java.util.List;

/**
 * 订单表
 * 订单表和用户表是一对一的关系
 * 用户表和订单表是多对一的关系
 * Created by jia on 2018/3/19.
 */
public class Orders {
    private Integer id;
    private String number;
    private Date createTime;
    private String note;
    private Integer userId;
    private User user;      //在使用resultMap，一对一映射时使用
    public Integer getId() {
        return id;
    }
    private List<OrderDetail> details;

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void print(){
        System.out.println("----test-print-----"+user+"   user==null: "+(user==null));
    }
}
