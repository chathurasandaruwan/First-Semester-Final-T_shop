package lk.ijse.t_shop.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;
    private String customerId;
    private LocalDate date;
}
