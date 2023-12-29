package lk.ijse.t_shop.entity;

public class Item {
    private String itemCode;
    private String type;
    private double price;
    private int quntity;
    private double discountPercentage;
    private String size;
    private String color;

    public Item() {
    }

    public Item(String itemCode, String type, double price, int quntity, double discountPercentage, String size, String color) {
        this.itemCode = itemCode;
        this.type = type;
        this.price = price;
        this.quntity = quntity;
        this.discountPercentage = discountPercentage;
        this.size = size;
        this.color = color;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuntity() {
        return quntity;
    }

    public void setQuntity(int quntity) {
        this.quntity = quntity;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "itemCode='" + itemCode + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", quntity=" + quntity +
                ", discountPercentage=" + discountPercentage +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
