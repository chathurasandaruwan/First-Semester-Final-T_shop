package lk.ijse.t_shop.dto.tm;


import javafx.scene.control.Button;

    public class CartTm {
        private String code;
        private String description;
        private double Price;
        private int qty;
        private double discountPercentage;
        private double tot;
        private Button btn;

        public CartTm() {
        }

        public CartTm(String code, String description,double Price,int qty, double tot,double discount, Button btn) {
            this.code = code;
            this.description = description;
            this.qty = qty;
            this.Price = Price;
            this.tot = tot;
            this.btn = btn;
            this.discountPercentage=discount;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getQty() {
            return qty;
        }

        public double getDiscountPercentage() {
            return discountPercentage;
        }

        public void setDiscountPercentage(double discountPercentage) {
            this.discountPercentage = discountPercentage;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double price) {
            Price = price;
        }

        public double getTot() {
            return tot;
        }

        public void setTot(double tot) {
            this.tot = tot;
        }

        public Button getBtn() {
            return btn;
        }

        public void setBtn(Button btn) {
            this.btn = btn;
        }
}
