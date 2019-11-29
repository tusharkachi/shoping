package com.allianz.shopping.dao;

import java.util.List;

import com.allianz.shopping.model.AddToCart;

public interface AddToCartInterface {
public boolean addAddToCart(AddToCart addToCart);
public List<AddToCart> getAllAddToCartByOrderId(int order_id);
}
