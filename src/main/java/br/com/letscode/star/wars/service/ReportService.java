package br.com.letscode.star.wars.service;

import br.com.letscode.star.wars.constant.ItemType;
import br.com.letscode.star.wars.domain.Rebel;
import br.com.letscode.star.wars.dto.response.AverageResourceDto;
import br.com.letscode.star.wars.dto.response.ReportResponseDto;
import br.com.letscode.star.wars.repository.RebelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    RebelRepository rebelRepository;

    public ReportResponseDto reports() {
        ReportResponseDto responseDto = ReportResponseDto.builder().build();

        List<Rebel> allData = rebelRepository.findAll();
        List<Rebel> traitors = allData.stream().filter(Rebel::getTraitor).collect(Collectors.toList());
        List<Rebel> rebels = allData.stream().filter(x -> !x.getTraitor()).collect(Collectors.toList());

        calculateTraitorsAndRebelsPercentage(traitors, rebels, responseDto, allData);
        calculateAverageResourcesPerRebels(rebels, responseDto);
        calculateLostPointsForTraitors(traitors, responseDto);

        return responseDto;
    }

    private void calculateTraitorsAndRebelsPercentage(List<Rebel> traitors, List<Rebel> rebels, ReportResponseDto responseDto, List<Rebel> allData) {
        if (traitors.size() == 0 && rebels.size() == 0) {
            responseDto.setTraitorsPercentage(BigDecimal.ZERO);
            responseDto.setRebelsPercentage(BigDecimal.ZERO);
        } else if (traitors.size() == 0) {
            responseDto.setRebelsPercentage(BigDecimal.valueOf(100));
            responseDto.setTraitorsPercentage(BigDecimal.ZERO);
        } else if (rebels.size() == 0) {
            responseDto.setTraitorsPercentage(BigDecimal.valueOf(100));
            responseDto.setRebelsPercentage(BigDecimal.ZERO);
        } else {
            responseDto.setTraitorsPercentage(BigDecimal.valueOf(traitors.size()).divide(BigDecimal.valueOf(allData.size()), 4, RoundingMode.HALF_EVEN).multiply(BigDecimal.valueOf(100)));
            responseDto.setRebelsPercentage(BigDecimal.valueOf(rebels.size()).divide(BigDecimal.valueOf(allData.size()), 4, RoundingMode.HALF_EVEN).multiply(BigDecimal.valueOf(100)));
        }
    }

    private void calculateAverageResourcesPerRebels(List<Rebel> rebels, ReportResponseDto responseDto) {
        Map<ItemType, Integer> averagePerItem = new HashMap<>();
        rebels.forEach(x -> x.getInventory().forEach(y -> averagePerItem.merge(y.getItemType(), y.getQuantity(), Integer::sum)));
        List<AverageResourceDto> averageResourceDtoResult = new ArrayList<>();
        averagePerItem.forEach((k, v) -> {
            AverageResourceDto averageResourceDto = AverageResourceDto.builder()
                    .averagePerRebel(BigDecimal.valueOf(v).divide(BigDecimal.valueOf(rebels.size()), 4, RoundingMode.HALF_EVEN))
                    .itemType(k)
                    .build();
            averageResourceDtoResult.add(averageResourceDto);
        });
        responseDto.setAverageResources(averageResourceDtoResult);
    }

    private void calculateLostPointsForTraitors(List<Rebel> traitors, ReportResponseDto responseDto) {
        traitors.forEach(x -> x.getInventory().forEach(y -> {
            if (responseDto.getLostPointsDueTraitors() == null) {
                responseDto.setLostPointsDueTraitors(y.getItemType().getPoint());
            } else {
                responseDto.setLostPointsDueTraitors(responseDto.getLostPointsDueTraitors() + y.getItemType().getPoint());
            }
        }));
    }
}
