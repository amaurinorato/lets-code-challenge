package br.com.letscode.star.wars.domain;

import br.com.letscode.star.wars.dto.request.ItemRequestDto;
import br.com.letscode.star.wars.dto.request.RebelRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Rebel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String genre;
    private Long latitude;
    private Long longitude;
    private String locationName;
    private Integer traitorReports;
    private Boolean traitor;

    @OneToMany(mappedBy = "rebel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> inventory;

    public Rebel(RebelRequestDto dto) {
        this.name = dto.getName();
        this.age = dto.getAge();
        this.genre = dto.getGenre();
        this.latitude = dto.getLocation().getLatitude();
        this.longitude = dto.getLocation().getLongitude();;
        this.locationName = dto.getLocation().getName();
        this.inventory = new ArrayList<>();
        this.traitor = false;

        for (int i = 0; i < dto.getInventory().size(); i++) {
            ItemRequestDto itemRequestDto = dto.getInventory().get(i);
            inventory.add(Item.builder().itemType(itemRequestDto.getItemType()).quantity(itemRequestDto.getQuantity()).rebel(this).build());
        }
    }

    public void reportTraitor() {
        if (this.traitorReports == null) {
            this.traitorReports = 1;
        } else {
            this.traitorReports++;
        }
        if (this.traitorReports.equals(3)) {
            this.traitor = true;
        }
    }
}
