package br.com.letscode.star.wars.controller;

import br.com.letscode.star.wars.domain.Rebel;
import br.com.letscode.star.wars.dto.request.DealRequestDto;
import br.com.letscode.star.wars.dto.request.RebelRequestDto;
import br.com.letscode.star.wars.service.DealService;
import br.com.letscode.star.wars.service.RebelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/deals")
public class DealController {

    @Autowired
    DealService dealService;

    @PostMapping
    public ResponseEntity<List<Rebel>> postDeal(@RequestBody DealRequestDto dealDto) {
        System.out.println("dealDto: " + dealDto);
        return ResponseEntity.ok(dealService.makeDeal(dealDto));
    }

}
