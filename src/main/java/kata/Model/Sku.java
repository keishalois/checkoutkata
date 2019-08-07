package kata.Model;

public class Sku {

    private String nameOfProduct;

    private int price;

    public Sku(String nameOfProduct, int price) {
        this.nameOfProduct = nameOfProduct;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
    public String getNameOfProduct() { return nameOfProduct;}

}
