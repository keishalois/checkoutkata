package sku;

public class Sku {
    private String nameOfProduct;

    private int price;

    public Sku(String nameOfProduct, int price) {
        this.nameOfProduct = nameOfProduct;
        this.price = price;
    }
    public String getNameOfProduct() { return nameOfProduct;}
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean equals(Sku sku) {
        if(sku != null && sku instanceof Sku ) {
            Sku tmpSku = sku;
            if(tmpSku.nameOfProduct!=null && tmpSku.nameOfProduct.equals(this.nameOfProduct)) {
                tmpSku.nameOfProduct = this.nameOfProduct;
                return true;
            }
        }
        return false;
    }


}
