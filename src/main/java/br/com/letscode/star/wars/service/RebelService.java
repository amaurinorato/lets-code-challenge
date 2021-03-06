package br.com.letscode.star.wars.service;

import br.com.letscode.star.wars.domain.Rebel;
import br.com.letscode.star.wars.dto.request.LocationRequestDto;
import br.com.letscode.star.wars.dto.request.RebelRequestDto;
import br.com.letscode.star.wars.repository.RebelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
public class RebelService {

    @Autowired
    RebelRepository rebelRepository;

    public Rebel save(RebelRequestDto requestDto) {
        validatePayload(requestDto);
        Rebel rebel = new Rebel(requestDto);
        rebel = rebelRepository.save(rebel);

        return rebel;
    }

    public Rebel updateLocation(LocationRequestDto dto, Long rebelId) {
        Rebel rebel = rebelRepository.findById(rebelId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "the informed id was not found"));
        rebel.setLocationName(dto.getName());
        rebel.setLatitude(dto.getLatitude());
        rebel.setLongitude(dto.getLongitude());
        return rebelRepository.save(rebel);
    }

    public Rebel informTraitor(Long rebelId) {
        Rebel rebel = rebelRepository.findById(rebelId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "the informed id was not found"));
        rebel.reportTraitor();
        return rebelRepository.save(rebel);
    }

    private void validatePayload(RebelRequestDto requestDto) {
        if (requestDto.getInventory() == null || requestDto.getInventory().size() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventory must be informed");
        }
        if (requestDto.getLocation() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location must be informed");
        }
    }

}
