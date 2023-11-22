package lk.ijse.t_shop.dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class machineDto {
    private String machineCode;
    private String name;
    private String machineType;
    private String tailerId;

    public String getMachineCode() {
        return machineCode;
    }

    public String getName() {
        return name;
    }

    public String getMachineType() {
        return machineType;
    }

    public String getTailerId() {
        return tailerId;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public void setTailerId(String tailerId) {
        this.tailerId = tailerId;
    }
}
