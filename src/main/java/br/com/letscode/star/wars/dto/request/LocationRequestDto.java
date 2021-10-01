package br.com.letscode.star.wars.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class LocationRequestDto {

    private Long latitude;
    private Long longitude;
    private String name;

}
