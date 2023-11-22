package lk.ijse.t_shop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class itemDto {
    private String itemCode;
    private String type;
    private double price;
    private int quntity;
    private double discountPercentage;
    private String size;
    private String color;

}
