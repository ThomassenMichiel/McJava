package be.mcjava.service;

import be.mcjava.dao.ProductsDao;
import be.mcjava.model.Product;

public class ProductService {
    private static ProductsDao productsDao = new ProductsDao();

    /***
     * returns a Product object from the DB that corresponds to the given name
     * the Dao's method can return a list, in this case returns a List of size 1
     * @param chosenProductName
     * @return
     */
    public static Product getProductByName(String chosenProductName) {
        return productsDao.getProductsByName(chosenProductName).get(0);
    }
}
