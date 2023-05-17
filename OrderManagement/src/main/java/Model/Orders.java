package Model;

public class Orders {
    private int idOrder;
    private int idClient;
    private int idProduct;
    private int price;
    private int quantity;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", idClient=" + idClient +
                ", idProduct=" + idProduct +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public Orders(int idOrder, int idClient, int idProduct, int price, int quantity) {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.price = price;
        this.quantity = quantity;
    }

    public Orders() {
    }
}
