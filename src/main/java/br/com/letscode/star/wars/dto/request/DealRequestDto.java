package br.com.letscode.star.wars.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class DealRequestDto {

    Long rebelSellerId;
    List<ItemRequestDto> sellerItems;
    Long rebelBuyerId;
    List<ItemRequestDto> buyerItems;

}
