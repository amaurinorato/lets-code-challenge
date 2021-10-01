package br.com.letscode.star.wars.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class RebelRequestDto {

    private String name;
    private Integer age;
    private String genre;
    private LocationRequestDto location;
    private List<ItemRequestDto> inventory;

}
