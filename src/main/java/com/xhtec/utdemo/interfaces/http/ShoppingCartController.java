package com.xhtec.utdemo.interfaces.http;

import com.xhtec.utdemo.application.CartService;
import com.xhtec.utdemo.application.dto.CartAddRequest;
import com.xhtec.utdemo.application.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车和订单接口
 *
 * 1. 下单
 *
 * @author hhy@100fen.cn
 */
@RestController
@RequestMapping("/api/")
public class ShoppingCartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/v1/users/{userId}/carts/")
    public ResponseEntity<?> getCartGoods(@PathVariable long userId) {
        CartDTO cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/v1/users/{userId}/carts/")
    public ResponseEntity<?> addGoodsToCart(@PathVariable long userId,
                                            @RequestBody CartAddRequest request) {
        cartService.addToCart(userId, request.getSkuId(), request.getQuantity());
        return ResponseEntity.noContent().build();
    }

}
