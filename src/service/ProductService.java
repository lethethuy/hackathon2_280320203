package service;

import model.Product;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService{

    private List<Product> productList = new ArrayList<>();


    public List<Product> getAllProducts() {
        return productList;
    }

    public void save(Product product) {
        if (findById(product.getProductId()) == null) {
            // khong co sang pham trong list
            // tien hanh them moi san pham
            productList.add(product);
        } else {
            // update
            productList.set(productList.indexOf(findById(product.getProductId())), product);
        }
    }

    public Product findById(String id
    ) {
        for (Product product : productList
        ) {
            if (product.getProductId().equals(id)) {
                // tim thay
                return product;
            }
        }
        // khong tim thay
        return null;
    }

    public void deleteProduct(String idDel) {
        if (findById(idDel) == null) {
            // không ton tại
            System.err.println("ID không tồn tại");
            return;
        }
        // cho phép xóa
        productList.remove(findById(idDel));

    }









    @Override
    public List<Product> getAll() {
       return productList;
    }

    @Override
    public void save(Object o) {


    }

    @Override
    public Object findById(Object o) {
        return null;
    }

    @Override
    public void delete(Object o) {

    }
}
