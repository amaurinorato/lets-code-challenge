package br.com.letscode.star.wars.controller;

import br.com.letscode.star.wars.domain.Rebel;
import br.com.letscode.star.wars.dto.response.ReportResponseDto;
import br.com.letscode.star.wars.service.ReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/v1/reports")
public class ReportsController {

    @Autowired
    ReportService reportService;

    @ApiOperation(value = "Operation to get a report about rebels data", nickname = "Reports")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "When the service can recover data to create report", response = ReportResponseDto.class, responseContainer = "Object"),
            @ApiResponse(code = 400, message = "When there is some invalid data in the request ", response = ResponseStatusException.class)})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<ReportResponseDto> getReports() {
        return ResponseEntity.ok(reportService.reports());
    }

}
