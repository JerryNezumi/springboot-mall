package com.Jerry.springbootmall.controller;

import com.Jerry.springbootmall.dto.CartItemRequest;
import com.Jerry.springbootmall.model.CartItem;
import com.Jerry.springbootmall.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    //添加商品到購物車
    @PostMapping("/user/{userId}/cart")
    public ResponseEntity<String> addCart(@PathVariable Integer userId,
                                  @RequestBody @Valid CartItemRequest cartItemRequest) {

        int cartId = cartService.addCart(userId);
        Integer cartItemId = cartService.createCartItem(cartId, cartItemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("商品已成功加入購物車，ID:" +cartItemId );

    }
    // 獲取用戶購物車內容
    @GetMapping("/user/{userId}/cart")
    public ResponseEntity<List<CartItem>> getCart(@PathVariable Integer userId) {
        List<CartItem> cartItems = cartService.getCartItems(userId);
        return ResponseEntity.status(HttpStatus.OK).body(cartItems);
    }

    //更新購物車商品數量
    @PutMapping("/user/cart/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Integer cartItemId,
                                                   @RequestBody @Valid CartItemRequest cartItemRequest) {
        CartItem updatedCartItem = cartService.updateCartItem(cartItemId, cartItemRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCartItem);
    }


    //刪除購物車中的商品
    @DeleteMapping("/user/cart/{cartItemId}")
    public ResponseEntity<String> deleteCart(@PathVariable Integer cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}