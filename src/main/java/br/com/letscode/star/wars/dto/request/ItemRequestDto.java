package br.com.letscode.star.wars.dto.request;

import br.com.letscode.star.wars.constant.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ItemRequestDto {

    ItemType itemType;
    Integer quantity;

}
