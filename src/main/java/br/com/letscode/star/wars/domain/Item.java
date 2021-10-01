package br.com.letscode.star.wars.domain;

import br.com.letscode.star.wars.constants.ItemType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    ItemType itemType;

    @ManyToOne
    @JoinColumn(name = "rebel_id")
    @JsonIgnore
    Rebel rebel;

    Integer quantity;
}
