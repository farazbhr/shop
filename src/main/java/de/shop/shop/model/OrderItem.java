package de.shop.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrderItem  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Positive(message="Price must be > 0")
//    private double price;

    //@Pattern(regexp = "^[0-9]*$", message = "Position can only digits")
//    private String position;
//
//   // @NotNull
   private Long beverageId;

  @ManyToOne
  private ShopOrder shopOrder;
}
