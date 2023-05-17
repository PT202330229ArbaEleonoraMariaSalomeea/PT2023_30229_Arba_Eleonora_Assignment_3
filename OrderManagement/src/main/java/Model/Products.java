package Model;

public class Products {
    private int idProducts;
    private String nameProduct;
    private int price;
    private int stock;

    public Products() {
    }

    public Products(int idProduct, String name, int price, int stock) {
        this.idProducts = idProduct;
        this.nameProduct = name;
        this.price = price;
        this.stock = stock;
    }

    public int getIdProducts() {
        return idProducts;
    }

    public void setIdProducts(int idProducts) {
        this.idProducts = idProducts;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Products{" +
                "idProducts=" + idProducts +
                ", nameProduct='" + nameProduct + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
