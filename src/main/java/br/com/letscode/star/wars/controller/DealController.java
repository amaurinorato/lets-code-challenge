package br.com.letscode.star.wars.controller;

import br.com.letscode.star.wars.domain.Rebel;
import br.com.letscode.star.wars.dto.request.DealRequestDto;
import br.com.letscode.star.wars.dto.request.RebelRequestDto;
import br.com.letscode.star.wars.service.DealService;
import br.com.letscode.star.wars.service.RebelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/deals")
public class DealController {

    @Autowired
    DealService dealService;

    @ApiOperation(value = "Operation to make deals between two rebels", nickname = "MakeDeal")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "When the service can exchange items between rebels", response = Rebel.class, responseContainer = "Object"),
            @ApiResponse(code = 400, message = "When there is some invalid data in the request ", response = ResponseStatusException.class)})
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ResponseEntity<List<Rebel>> postDeal(@RequestBody DealRequestDto dealDto) {
        return ResponseEntity.ok(dealService.makeDeal(dealDto));
    }

}
