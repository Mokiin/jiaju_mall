package com.kiin.furns.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cart {

    private Map<Integer,CartItem> items = new HashMap<>();

    private Integer totalCount = 0;
    private BigDecimal cartTotalPrice = new BigDecimal(0);

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public BigDecimal getCartTotalPrice(){
        cartTotalPrice = new BigDecimal(0);
        items.forEach((k,v) -> {
            cartTotalPrice = cartTotalPrice.add(v.getTotalPrice());
        });
        return cartTotalPrice;
    }


    public void addItems(CartItem item){
        CartItem cartItem = items.get(item.getId());
        // 判断购物车里是否有这个家具
        if (null != cartItem){
            // 不等于空说明购物车里有，增加 count
            cartItem.setCount(cartItem.getCount() + 1);
            // 获取当前购物车单项的价格
            BigDecimal price = cartItem.getPrice();
            // 价格乘以当前单项的数量
            BigDecimal totalPrice = price.multiply(new BigDecimal(cartItem.getCount()));
            // 赋值给购物车单项总价格
            cartItem.setTotalPrice(totalPrice);
            return;
        }
        items.put(item.getId(), item);
    }

    public void deleteItem(Integer id){
        items.remove(id);
    }

    public void clear(){
        items.clear();
    }

    public void updateCount(int id,int count){
        CartItem item = items.get(id);
        if (null != item){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public int getTotalCount(){
       totalCount = 0;
       items.forEach((key, v) ->{
           totalCount += v.getCount();
       });
       return totalCount;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }
}
