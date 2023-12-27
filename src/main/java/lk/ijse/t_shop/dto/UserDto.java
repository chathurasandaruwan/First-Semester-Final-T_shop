package lk.ijse.t_shop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String username;
    private String password;
    private String contactNo;
}
