package br.com.letscode.star.wars.dto.response;

import br.com.letscode.star.wars.constant.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Data
public class AverageResourceDto {

    private ItemType itemType;
    private BigDecimal averagePerRebel;

}
