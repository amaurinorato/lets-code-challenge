package br.com.letscode.star.wars.dto.response;

import br.com.letscode.star.wars.dto.request.ItemRequestDto;
import br.com.letscode.star.wars.dto.request.LocationRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class RebelResponseDto {

    private Long id;
    private String name;
    private Integer age;
    private String genre;
    private LocationRequestDto location;
    private List<ItemRequestDto> inventory;

}
