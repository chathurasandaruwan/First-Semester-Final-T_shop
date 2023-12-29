package lk.ijse.t_shop.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Record {
    private String RecordId;
    private String Type;
    private double crotchDep;
    private double rice;
    private double legOpe;
    private double kneeCirum;
    private double thighCirum;
    private double outSeamL;
    private double inseamL;
    private double hipsCircum;
    private double waistCircum;
    private double cuffCirum;
    private double neckCirum;
    private double chestCirum;
    private double shirtL;
    private double shoulderWid;
    private double sleeveL;
    private double bicepCircum;
    private double sleeveOp;
    private double coller;
    private String orderId;
    private String price;
    private  String custId;

    public Record(double crotchDep, double rice, double legOpe, double kneeCirum, double thighCirum, double outSeamL, double inseamL, double hipsCircum, double waistCircum, double cuffCirum, double neckCirum, double chestCirum, double shirtL, double shoulderWid, double sleeveL, double bicepCircum, double sleeveOp, double coller, String price, String recordId) {
        RecordId = recordId;
        this.crotchDep = crotchDep;
        this.rice = rice;
        this.legOpe = legOpe;
        this.kneeCirum = kneeCirum;
        this.thighCirum = thighCirum;
        this.outSeamL = outSeamL;
        this.inseamL = inseamL;
        this.hipsCircum = hipsCircum;
        this.waistCircum = waistCircum;
        this.cuffCirum = cuffCirum;
        this.neckCirum = neckCirum;
        this.chestCirum = chestCirum;
        this.shirtL = shirtL;
        this.shoulderWid = shoulderWid;
        this.sleeveL = sleeveL;
        this.bicepCircum = bicepCircum;
        this.sleeveOp = sleeveOp;
        this.coller = coller;
        this.price = price;
    }
}
