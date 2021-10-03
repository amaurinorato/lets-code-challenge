package br.com.letscode.star.wars.domain;

import br.com.letscode.star.wars.constant.ItemType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "rebel")
@EqualsAndHashCode(of = "id")
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
