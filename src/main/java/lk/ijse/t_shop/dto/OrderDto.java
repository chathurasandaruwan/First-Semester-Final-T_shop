package lk.ijse.t_shop.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
    private String orderId;
    private String customerId;
    private LocalDate date;
}
