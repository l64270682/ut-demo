package com.xhtec.utdemo.interfaces.http;

import com.xhtec.utdemo.application.PayService;
import com.xhtec.utdemo.application.dto.AlipayPayResponse;
import com.xhtec.utdemo.application.dto.PayIssueRequest;
import com.xhtec.utdemo.application.dto.PayIssueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 支付接口
 *
 * 1、支付
 *
 * @author hhy@100fen.cn
 */
@RestController
@RequestMapping("/api/")
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/v1/transactions/pay")
    public ResponseEntity<?> issuePay(@RequestBody PayIssueRequest request) {
        PayIssueResponse rsp = PayIssueResponse.builder()
                .transactionKey(UUID.randomUUID().toString())
                .build();

        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/v1/transactions/alipay_callbck")
    public ResponseEntity<?> alipayCallback(@RequestBody AlipayPayResponse request) {

        payService.receiveAlipayResponse(request);

        return ResponseEntity.ok().build();
    }

}
