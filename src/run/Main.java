package run;

import config.InputMethods;
import model.Cart;
import model.Catalog;
import model.Product;
import service.CartService;
import service.CatalogService;
import service.ProductService;

import java.util.Collections;
import java.util.Comparator;

public class Main {
    private final static CatalogService catalogService = new CatalogService();
    private final static ProductService productService = new ProductService();
    private final static CartService cartService = new CartService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("**************************BASIC-MENU**************************\n" +
                    "1. Quản lý danh mục [5 điểm]\n" +
                    "2. Quản lý sản phẩm [5 điểm]\n" +
                    "3. Dành cho người dùng [5 điểm]\n" +
                    "4. Thoát [5 điểm]");

            System.out.println("Nhap vao lua chon cua ban: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    // menu catalog
                    menuCatalogManager();


                    break;
                case 2:
                    // menu product
                    menuProductManager();

                    break;

                case 3:
                    // menu product
                    menuUserManager();

                    break;

                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Khong hop le, vui long nhap lai");

            }
        }

    }

    ////
    public static void menuCatalogManager() {
        System.out.println("********************CATALOG-MANAGEMENT********************\n" +
                "1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục [5 điểm]\n" +
                "2. Hiển thị thông tin tất cả các danh mục [5 điểm]\n" +
                "3. Sửa tên danh mục theo mã danh mục [5 điểm]\n" +
                "4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm) [5 điểm]\n" +
                "5. Quay lại");
        System.out.println("Nhap vao lua chon cua ban: ");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                // menu catalog
                addCatalog();


                break;
            case 2:
                // display catalog
                displayCatalog();


                break;
            case 3:
                // edit catalog
                editCatalogInfo();

                break;
            case 4:
                // delete catalog
                deleteCatalog();

                break;

            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Khong hop le, vui long nhap lai");

        }

    }

    public static void addCatalog() {
        // Nhap vao so luong catalog can them moi
        System.out.println("Nhap so luong catalog can them moi: ");
        int n = InputMethods.getInteger();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap thong tin cho catalog thu " + (i + 1));
            Catalog newCatalog = new Catalog();
            // id tu tang
            int newId = catalogService.getNewId();
            newCatalog.setCatalogId(newId);
            System.out.println("New ID: " + newId);
            // Nhap cac thong tin con lai
            System.out.println("Hay nhap vap ten cua Catalog");
            String newNameCatalog = InputMethods.getString();
            newCatalog.setCatalogName(newNameCatalog);
            // Tien hanh them moi vao mang
            catalogService.save(newCatalog);

        }
    }

    public static void displayCatalog() {
        for (Catalog catalog : catalogService.getAllCatalogs()) {
            System.out.println(catalog);
        }
    }

    public static void deleteCatalog() {
        System.out.println("nhập vào id cần xóa ");
        int idDel = InputMethods.getInteger();
        catalogService.deleteCatalog(idDel);
        for (Catalog catalog : catalogService.getAllCatalogs()
        ) {
            if (catalog == null) {
                System.out.println("catalog trong, ko xoa duoc");
            } else if (catalog.getCatalogId() == idDel) {
                catalogService.deleteCatalog(idDel);
                System.out.println("Xoa thanh cong");
            }
        }
    }

    // update thông tin
//    public static void editCatalogInfo() {
//        System.out.println("Nhập vào id cần sửa");
//        int idEdit = InputMethods.getInteger();
//        Catalog editCatalog = catalogService.findById(idEdit);
//
//        if(editCatalog ==null){
//            System.err.println("Không tìm thấy catalog ");
//            return;
//        }
//        System.out.println("Đối tượng cần sửa là :");
//        System.out.println(editCatalog);
//        System.out.println("Nhap vao ID moi: ");
//        int newIdCatalog = InputMethods.getInteger();
//        editCatalog.setCatalogId(newIdCatalog);
//        // Nhap cac thong tin con lai
//        System.out.println("Hay nhap vap ten cua Catalog");
//        String newNameCatalog = InputMethods.getString();
//        editCatalog.setCatalogName(newNameCatalog);
//        // Tien hanh them moi vao mang
//        catalogService.save(editCatalog);
//    }
    public static void editCatalogInfo() {
        System.out.println("Nhập vào ID cần sửa: ");
        int idEdit = InputMethods.getInteger();
        Catalog editCatalog = catalogService.findById(idEdit);

        if (editCatalog == null) {
            System.err.println("Không tìm thấy catalog");
            return;
        }

        System.out.println("Thông tin đối tượng cần sửa là:");
        System.out.println(editCatalog);

        System.out.println("Nhập vào ID mới: ");
        int newIdCatalog = InputMethods.getInteger();
        editCatalog.setCatalogId(newIdCatalog);

        System.out.println("Nhập vào tên mới cho Catalog: ");
        String newNameCatalog = InputMethods.getString();
        editCatalog.setCatalogName(newNameCatalog);

        catalogService.save(editCatalog);
        System.out.println("Đã cập nhật thông tin của Catalog thành công.");
    }

    public static void menuProductManager() {
        System.out.println("********************PRODUCT-MANAGEMENT********************\n" +
                "1. Nhập số sản sản phẩm và nhập thông tin sản phẩm [5 điểm]\n" +
                "2. Hiển thị thông tin các sản phẩm [5 điểm]\n" +
                "3. Sắp xếp sản phẩm theo giá giảm dần [5 điểm]\n" +
                "4. Xóa sản phẩm theo mã [5 điểm]\n" +
                "5. Tìm kiếm sách theo tên sách [10 điểm]\n" +
                "6. Thay đổi thông tin của sách theo mã sách [10 điểm]\n" +
                "7. Quay lại");
        System.out.println("Nhap vao lua chon cua ban: ");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                // menu catalog
                addProduct();


                break;
            case 2:
                // display product
                displayProduct();


                break;
            case 3:
                // edit catalog
                productService.getAllProducts().sort(Comparator.comparingDouble(Product::getProductPrice));

                break;
            case 4:
                // delete catalog
                deleteProduct();


                break;
            case 5:
                // delete catalog


                break;
            case 6:
                // edit product
                editProductInfo();


                break;


            case 7:
                System.exit(0);
                break;
            default:
                System.out.println("Khong hop le, vui long nhap lai");

        }
    }


//    public static void addProduct() {
//        // Nhap vao so luong catalog can them moi
//        System.out.println("Nhap so luong catalog can them moi: ");
//        int n = InputMethods.getInteger();
//        for (int i = 0; i < n; i++) {
//            System.out.println("Nhap thong tin cho catalog thu " + (i + 1));
//            Product newProduct = new Product();
//            // id tu tang
////            String newId = catalogService.getNewId();
////            newProduct.setProductId(newId);
////            System.out.println("New ID: " + newId);
//            //id
//            System.out.println("Hay nhap id:");
//            String IdProduct = InputMethods.getString();
//            newProduct.setProductName(IdProduct);
//            // Nhap cac thong tin con lai
//            System.out.println("Hay nhap vap ten cua Catalog");
//            String NameProduct = InputMethods.getString();
//            newProduct.setProductName(NameProduct);
//
//            System.out.println("Hay nhap vap price cua Catalog");
//            double priceProduct = InputMethods.getDouble();
//            newProduct.setProductPrice(priceProduct);
//            System.out.println("Hay nhap vap ten cua Catalog");
//            String newNameCatalog = InputMethods.getString();
//            newProduct.setProductName(newNameCatalog);
//
//            System.out.println("Hay nhap vao description");
//            String descriptionProduct = InputMethods.getString();
//            newProduct.setProductName(descriptionProduct);
//
//            System.out.println("Hay nhap vao stock");
//            int stock = InputMethods.getInteger();
//            newProduct.setStock(stock);
//
//
//            System.out.println("Hay nhap vao status");
//            boolean status = InputMethods.getBoolean();
//            newProduct.setStatus(status);
//            // Tien hanh them moi vao mang
//            catalogService.save(newProduct);
//
//        }
//    }

    public static void addProduct() {

        System.out.println("Nhap so luong san pham can them moi: ");
        int n = InputMethods.getInteger();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap thong tin cho san pham thu " + (i + 1));
            Product newProduct = new Product();

            System.out.println("Nhap vao ID san pham: ");
            String productId = InputMethods.getString();
            newProduct.setProductId(productId);

            System.out.println("Nhap vao ten san pham: ");
            String productName = InputMethods.getString();
            newProduct.setProductName(productName);

            System.out.println("Nhap vao gia san pham: ");
            double productPrice = InputMethods.getDouble();
            newProduct.setProductPrice(productPrice);

            System.out.println("Nhap vao mo ta san pham: ");
            String description = InputMethods.getString();
            newProduct.setDescription(description);

            System.out.println("Nhap vao so luong ton kho: ");
            int stock = InputMethods.getInteger();
            newProduct.setStock(stock);

            System.out.println("Nhap vao trang thai san pham (true/false): ");
            boolean status = InputMethods.getBoolean();
            newProduct.setStatus(status);

            // Nhap vao danh muc cho san pham
            System.out.println("Nhap vao ID danh muc san pham: ");
            int catalogId = InputMethods.getInteger();
            Catalog catalog = catalogService.findById(catalogId);
            if (catalog == null) {
                System.out.println("Khong tim thay danh muc co ID " + catalogId);
                continue;
            }
            newProduct.setCatalog(catalog);


            productService.save(newProduct);
            System.out.println("Da them moi san pham: " + newProduct.getProductName());
        }


    }


//    public static void displayCatalog() {
//        for (Catalog catalog : catalogService.getAllCatalogs()) {
//            System.out.println(catalog);
//        }
//    }
//

    public static void displayProduct() {
        for (Product product : productService.getAllProducts()) {
            System.out.println(product);
        }
    }

    public static void deleteProduct() {
        System.out.println("nhập vào id cần xóa ");
        String idDel = InputMethods.getString();
        productService.deleteProduct(idDel);
        for (Product product : productService.getAllProducts()
        ) {
            if (product == null) {
                System.out.println("product trong, ko xoa duoc");
            } else if (product.getProductId().equals(idDel)) {
                productService.deleteProduct(idDel);
                System.out.println("Xoa thanh cong");
            }
        }
    }

    public static void editProductInfo() {
        System.out.println("Nhập vào ID cần sửa: ");
        String idEdit = InputMethods.getString();
        Product editProduct = productService.findById(idEdit);

        if (editProduct == null) {
            System.err.println("Không tìm thấy sản phẩm");
            return;
        }
        System.out.println("Thông tin đối tượng cần sửa là:");
        System.out.println(editProduct);
        System.out.println("Nhập vào ID mới: ");
        String newIdProduct = InputMethods.getString();
        editProduct.setProductId(newIdProduct);
        System.out.println("Nhập vào tên mới cho sản phẩm: ");
        String newNameProduct = InputMethods.getString();
        editProduct.setProductName(newNameProduct);
        System.out.println("Nhập vào giá mới cho sản phẩm: ");
        double newPriceProduct = InputMethods.getDouble();
        editProduct.setProductPrice(newPriceProduct);
        System.out.println("Nhập vào mô tả mới cho sản phẩm: ");
        String newDescription = InputMethods.getString();
        editProduct.setDescription(newDescription);
        System.out.println("Nhập vào số lượng tồn kho mới: ");
        int newStock = InputMethods.getInteger();
        editProduct.setStock(newStock);
        System.out.println("Nhập vào trạng thái mới cho sản phẩm (true/false): ");
        boolean newStatus = InputMethods.getBoolean();
        editProduct.setStatus(newStatus);
        productService.save(editProduct);
        System.out.println("Đã cập nhật thông tin của sản phẩm thành công.");
    }


    public static void menuUserManager() {
        System.out.println("**************************MENU-USER**************************\n" +
                "1. Xem danh sách sản phẩm\n" +
                "2. Thêm vào giỏ hàng\n" +
                "3. Xem tất cả sản phẩm giỏ hàng\n" +
                "4. Thay đổi số lượng sản phẩm trong giỏ hàng\n" +
                "5. Xóa 1 sản phẩm trong giỏ hàng\n" +
                "6. Xóa toàn bộ sản phẩm trong giỏ hàng\n" +
                "7. Quay lại");
        System.out.println("Nhap vao lua chon cua ban: ");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                // menu catalog
                displayProduct();


                break;
            case 2:
                // add product to cart
                addProductToCart();

                break;
            case 3:
                // display cart
                displayCart();

                break;
            case 4:
                editCartInfo();


                break;
            case 5:
                deleteCart();


                break;
            case 6:
                deleteAllCart();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                System.out.println("Khong hop le, vui long nhap lai");

        }
    }

    public static void addProductToCart() {
        Cart newCart = new Cart();
        System.out.println("Tat ca cac san pham hien dang co");
        displayProduct();
        System.out.println("Lua chon san pham muon them vao cart thong qua Id");
        System.out.println("Hay nhap id cua product");
        String idProduct = InputMethods.getString();
        System.out.println("Product them vao cart la: ");
        Product productAddToCart = productService.findById(idProduct);
        System.out.println(productAddToCart);

        int newId = cartService.getNewId();
        newCart.setCartItemId(newId);
        System.out.println("New Id " + newId);

        String newNameProduct = productAddToCart.getProductName();
        newCart.setNameProduct(newNameProduct);
        System.out.println("Name " + newNameProduct);

        double price = productAddToCart.getProductPrice();
        newCart.setPrice(price);
        System.out.println("Price " + price);

        System.out.println("Hay nhap so luong: ");
        int quantity = InputMethods.getInteger();
        newCart.setQuantity(quantity);

        if (productAddToCart.getStock() < 0) {
            System.out.println("San pham da het");
        } else if (productAddToCart.getStock() > 0) {
            for (Cart cart : cartService.getAllCart()
            ) {
                if (cart.getNameProduct()== newCart.getNameProduct()) {
                    newCart.setQuantity(newCart.getQuantity() + cart.getQuantity());
                }
            }
        }
        cartService.save(newCart);
    }

    public static void displayCart() {
        for (Cart cart : cartService.getAllCart()) {
            System.out.println(cart);
        }
    }

    public static void deleteCart() {
        System.out.println("nhập vào id cần xóa ");
        int idDel = InputMethods.getInteger();
        cartService.deleteCart(idDel);
        for (Cart cart : cartService.getAllCart()
        ) {
            if (cart == null) {
                System.out.println("cart trong, ko xoa duoc");
            } else if (cart.getCartItemId() == idDel) {
                cartService.deleteCart(idDel);
                System.out.println("Xoa thanh cong");
            }
        }
    }

    public static void deleteAllCart() {
        cartService.getAllCart().clear();
    }


    public static void editCartInfo() {
        System.out.println("Nhập vào ID cần sửa: ");
        int idEdit = InputMethods.getInteger();
        Cart editProduct = cartService.findById(idEdit);

        if (editProduct == null) {
            System.err.println("Không tìm thấy sản phẩm");
            return;
        }
        System.out.println("Thông tin đối tượng cần sửa là:");
        System.out.println(editProduct);
        System.out.println("Nhap so luong muon sua");
        editProduct.setQuantity(InputMethods.getInteger());

    }


}
