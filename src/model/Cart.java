package model;

import java.util.List;

public class Cart {

    private int cartItemId;
    private Product product;
    private double price;
    private int quantity = 0;
    private String nameProduct;

    public Cart(int cartItemId, Product product, double price, int quantity, String nameProduct) {
        this.cartItemId = cartItemId;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.nameProduct = nameProduct;
    }

    public Cart() {
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItemId=" + cartItemId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", nameProduct='" + nameProduct + '\'' +
                '}';
    }
}
