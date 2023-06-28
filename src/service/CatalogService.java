package service;

import model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IService {
    private List<Catalog> catalogList = new ArrayList<>();


    public List<Catalog> getAllCatalogs() {
        return catalogList;
    }

    public void save(Catalog catalog) {
        if (findById(catalog.getCatalogId()) == null) {
            // khong co sang pham trong list
            // tien hanh them moi san pham
            catalogList.add(catalog);
        } else {
            // update
            catalogList.set(catalogList.indexOf(findById(catalog.getCatalogId())), catalog);
        }
    }

    public Catalog findById(int id) {
        for (Catalog catalog : catalogList
        ) {
            if (catalog.getCatalogId() == id) {
                // tim thay
                return catalog;
            }
        }
        // khong tim thay
        return null;
    }


    // id tu tang
    public int getNewId() {
        // id tu tang
        int max = 0;
        for (Catalog catalog : catalogList
        ) {
            if (catalog.getCatalogId() > max) {
                max = catalog.getCatalogId();
            }
        }
        return max + 1;
    }

    public void deleteCatalog(int idDel) {
        if (findById(idDel) == null) {
            // không ton tại
            System.err.println("ID không tồn tại");
            return;
        }
        // cho phép xóa
        catalogList.remove(findById(idDel));

    }


    @Override
    public List getAll() {
        return null;
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
