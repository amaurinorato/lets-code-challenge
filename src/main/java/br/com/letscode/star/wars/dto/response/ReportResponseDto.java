package br.com.letscode.star.wars.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class ReportResponseDto {

    public BigDecimal traitorsPercentage;
    public BigDecimal rebelsPercentage;
    public List<AverageResourceDto> averageResources;
    public Integer lostPointsDueTraitors;

}
