package com.xhtec.utdemo.interfaces.http;

import com.google.common.base.Preconditions;
import com.xhtec.utdemo.application.OrderService;
import com.xhtec.utdemo.application.dto.OrderCreateRequest;
import com.xhtec.utdemo.application.dto.OrderCreateResponse;
import com.xhtec.utdemo.application.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车和订单接口
 *
 * 1. 下单
 *
 * @author hhy@100fen.cn
 */
@RestController
@RequestMapping("/api/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/v1/users/{userId}/orders/")
    public ResponseEntity<?> createOrder(@PathVariable long userId,
                                         @RequestBody OrderCreateRequest request) {
        Preconditions.checkArgument(request.getToken() != null && !request.getToken().isEmpty(), "token不能为空");

        OrderCreateResponse rsp = orderService.createOrder(userId, request.getToken(), request.getGoods());
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/v1/users/{userId}/orders/")
    public ResponseEntity<?> getOrders(@PathVariable long userId) {
        List<OrderDTO> rsp = orderService.getAllOrder(userId);
        return ResponseEntity.ok(rsp);
    }

}
