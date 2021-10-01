package br.com.letscode.star.wars.controller;

import br.com.letscode.star.wars.domain.Rebel;
import br.com.letscode.star.wars.dto.request.LocationRequestDto;
import br.com.letscode.star.wars.dto.request.RebelRequestDto;
import br.com.letscode.star.wars.service.RebelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1/rebels")
public class RebelsController {

    @Autowired
    RebelService rebelService;

    @PostMapping
    public ResponseEntity<Rebel> saveRebel(@RequestBody RebelRequestDto rebelDto) {
        System.out.println("rebelDto: " + rebelDto);
        System.out.println(rebelDto.getInventory().get(0).getItemType().getPoint());
        return ResponseEntity.ok(rebelService.save(rebelDto));
    }

    @PatchMapping("/{rebelId}/locations")
    public ResponseEntity<Rebel> updateRebelLocation(@RequestBody LocationRequestDto locationDto, @PathVariable(value = "rebelId") Long rebelId) {
        System.out.println("LocationDto: " + locationDto);
        System.out.println("rebelId: " + rebelId);
        return ResponseEntity.ok(rebelService.updateLocation(locationDto, rebelId));
    }

    @PatchMapping("/{rebelId}/betrayals")
    public ResponseEntity<Rebel> informTraitor(@PathVariable(value = "rebelId") Long rebelId) {
        System.out.println("rebelId: " + rebelId);
        return ResponseEntity.ok(rebelService.informTraitor(rebelId));
    }
}
