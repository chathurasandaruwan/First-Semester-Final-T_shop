package lk.ijse.t_shop.entity;

import lk.ijse.t_shop.view.tdm.CartTm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrder {
    private String orderId;
    private LocalDate date;
    private String customerId;
    private List<CartTm> cartTmList = new ArrayList<>();

    public PlaceOrder() {
    }

    public PlaceOrder(String orderId, LocalDate date, String customerId, List<CartTm> cartTmList) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.cartTmList = cartTmList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<CartTm> getCartTmList() {
        return cartTmList;
    }

    public void setCartTmList(List<CartTm> cartTmList) {
        this.cartTmList = cartTmList;
    }

    @Override
    public String toString() {
        return "PlaceOrderDto{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", customerId='" + customerId + '\'' +
                ", cartTmList=" + cartTmList +
                '}';
    }
}