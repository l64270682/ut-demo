package com.xhtec.utdemo.interfaces.http;

import com.xhtec.utdemo.application.ShelvesService;
import com.xhtec.utdemo.application.dto.SkuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品货架接口
 *
 * 1、查看货架的商品列表和库存状态
 *
 * @author hhy@100fen.cn
 */
@RestController
@RequestMapping("/api/v1/")
public class ShelvesController {

    @Autowired
    private ShelvesService shelvesService;

    @GetMapping("/shelves/{shelveId}/")
    public ResponseEntity<?> getShelveSkus(@PathVariable long shelveId) {
        List<SkuDTO> skuDTOS = shelvesService.fetchShelveSkus(shelveId);
        return ResponseEntity.ok(skuDTOS);
    }

}
