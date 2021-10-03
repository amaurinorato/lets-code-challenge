package br.com.letscode.star.wars.controller;

import br.com.letscode.star.wars.domain.Rebel;
import br.com.letscode.star.wars.dto.request.LocationRequestDto;
import br.com.letscode.star.wars.dto.request.RebelRequestDto;
import br.com.letscode.star.wars.service.RebelService;
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
@RequestMapping("/v1/rebels")
public class RebelsController {

    @Autowired
    RebelService rebelService;

    @ApiOperation(value = "Operation to save a rebel", nickname = "SaveRebel")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "When the service can save the informed rebel ", response = Rebel.class, responseContainer = "Object"),
            @ApiResponse(code = 400, message = "When there is some invalid data in the request ", response = ResponseStatusException.class)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Rebel> saveRebel(@RequestBody RebelRequestDto rebelDto) {
        return new ResponseEntity<>(rebelService.save(rebelDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Operation to update rebel location", nickname = "UpdateLocation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "When the service can update location", response = Rebel.class, responseContainer = "Object"),
            @ApiResponse(code = 400, message = "When there is some invalid data in the request ", response = ResponseStatusException.class)})
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{rebelId}/locations")
    public ResponseEntity<Rebel> updateRebelLocation(@RequestBody LocationRequestDto locationDto, @PathVariable(value = "rebelId") Long rebelId) {
        return ResponseEntity.ok(rebelService.updateLocation(locationDto, rebelId));
    }

    @ApiOperation(value = "Operation to inform a traitor rebel", nickname = "UpdateTraitor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "When the service can update traitor information", response = Rebel.class, responseContainer = "Object"),
            @ApiResponse(code = 400, message = "When there is some invalid data in the request", response = ResponseStatusException.class)})
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{rebelId}/betrayals")
    public ResponseEntity<Rebel> informTraitor(@PathVariable(value = "rebelId") Long rebelId) {
        return ResponseEntity.ok(rebelService.informTraitor(rebelId));
    }
}
