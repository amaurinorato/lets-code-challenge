package br.com.letscode.star.wars.service;

import br.com.letscode.star.wars.domain.Rebel;
import br.com.letscode.star.wars.dto.request.DealRequestDto;
import br.com.letscode.star.wars.dto.request.ItemRequestDto;
import br.com.letscode.star.wars.repository.ItemRepository;
import br.com.letscode.star.wars.repository.RebelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class DealService {

    @Autowired
    RebelRepository rebelRepository;

    @Autowired
    ItemRepository itemRepository;

    public List<Rebel> makeDeal(DealRequestDto dealRequestDto) {
        //TODO create logic to exchange items
        //TODO validate if rebels are not traitor
        Integer buyerItemsTotalPoints = sumPointsOfItems(dealRequestDto.getBuyerItems());
        Integer sellerItemsTotalPoints = sumPointsOfItems(dealRequestDto.getSellerItems());
        if (!buyerItemsTotalPoints.equals(sellerItemsTotalPoints)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seller items total points: " + sellerItemsTotalPoints + " is different from buyer items total points: " + buyerItemsTotalPoints);
        }
        Rebel buyer = rebelRepository.findById(dealRequestDto.getRebelBuyerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rebel buyer was not found"));
        Rebel seller = rebelRepository.findById(dealRequestDto.getRebelSellerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rebel seller was not found"));

        buyer.getInventory().forEach(x -> {
            x.setRebel(seller);
            itemRepository.save(x);
        });
        seller.getInventory().forEach(x -> {
            x.setRebel(buyer);
            itemRepository.save(x);
        });

        return Arrays.asList(buyer, seller);
    }

    public Integer sumPointsOfItems(List<ItemRequestDto> items) {
        return items.stream().map(x -> x.getItemType().getPoint() * x.getQuantity()).reduce(Integer::sum).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is an empty item List"));
    }

}
