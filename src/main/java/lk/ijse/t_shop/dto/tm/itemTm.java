package lk.ijse.t_shop.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class itemTm {
    private String itemCode;
    private String type;
    private double price;
    private int quntity;
    private double discountPercentage;
    private String size;
    private String color;

}
