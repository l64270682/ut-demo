package com.xhtec.utdemo.application.assembler;

import com.xhtec.utdemo.application.dto.OrderDTO;
import com.xhtec.utdemo.domain.model.order.Order;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hhy@100fen.cn
 */
public class OrderAssembler {

    public static List<OrderDTO> toDTOs(List<Order> orders) {
        return orders.stream()
                .map(o -> {
                    OrderDTO orderDTO = new OrderDTO();
                    BeanUtils.copyProperties(o, orderDTO);
                    return orderDTO;
                })
                .collect(Collectors.toList());
    }

}
