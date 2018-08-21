package com.xhtec.utdemo.application;

import com.google.common.base.Preconditions;
import com.xhtec.utdemo.application.assembler.OrderAssembler;
import com.xhtec.utdemo.application.dto.OrderCreateResponse;
import com.xhtec.utdemo.application.dto.OrderDTO;
import com.xhtec.utdemo.application.dto.OrderGoodsDTO;
import com.xhtec.utdemo.domain.model.order.Goods;
import com.xhtec.utdemo.domain.model.order.Order;
import com.xhtec.utdemo.domain.model.order.OrderRepository;
import com.xhtec.utdemo.domain.model.order.OrderStatus;
import com.xhtec.utdemo.domain.model.sku.SKU;
import com.xhtec.utdemo.domain.service.SkuManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 订单管理
 *
 * @author hhy@100fen.cn
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SkuManager skuManager;

    public OrderCreateResponse createOrder(long userId, String token, Set<OrderGoodsDTO> goods) {
        Order order = orderRepository.findByUserIdAndToken(userId, token);
        Preconditions.checkArgument(order == null, "请不用重复下单");

        String orderNo = UUID.randomUUID().toString();

        // lock amount
        final List<Goods> orderGoods = goods.stream()
                .map(g -> {
                    SKU sku = skuManager.findSkuById(g.getSkuId());
                    Preconditions.checkArgument(sku != null && !sku.isOff(), "你下单的商品已下架");

                    boolean succ = skuManager.prepareOrderSku(sku, g.getQuantity(), userId, token);
                    Preconditions.checkArgument(succ, "扣库存失败");

                    return new Goods(sku.getId(), sku.getName(), sku.getPrice(), g.getQuantity());
                })
                .collect(Collectors.toList());



        // amount
        BigDecimal amount = orderGoods.stream().reduce(BigDecimal.valueOf(0, 2),
                (r, g) -> r.add(g.getSubTotal()),
                (l, r) -> l.add(r));

        // 创建订单
        order = new Order(userId, orderNo, orderGoods, amount, BigDecimal.valueOf(0), token, OrderStatus.UNPAID);
        order = orderRepository.save(order);

        return OrderCreateResponse.builder().orderNo(order.getNo()).build();
    }

    public List<OrderDTO> getAllOrder(long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);

        return OrderAssembler.toDTOs(orders);
    }
}
