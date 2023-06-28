package service;

import model.Cart;
import model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    private List<Cart> cartList = new ArrayList<>();

    public List<Cart> getAllCart() {
        return cartList;
    }

    public void save(Cart cart) {

        if (findById(cart.getCartItemId()) == null) {
            // Ko co san pham trong cart
            // tien hanh them mois vao cart
            cartList.add(cart);
        }else {
            // update
            cartList.set(cartList.indexOf(findById(cart.getCartItemId())),cart);
        }

    }

    public Cart findById(int id) {
        for (Cart cart : cartList
        ) {
            if (cart.getCartItemId() == id) {
                // tim thay
                return cart;
            }
        }
        // khong tim thay
        return null;
    }

    public int getNewId(){
        // id tu tang
        int max = 0;
        for (Cart cart : cartList
             ) {
            if (cart.getCartItemId()>max){
                max = cart.getCartItemId();
            }
        }
        return  max +1;
    }

    public void deleteCart(int id){
        if(findById(id)==null){
            // khong ton tai
            System.out.println("ID khong ton tai");
        }
        // cho phep xoa
        cartList.remove(findById(id));
    }


}
