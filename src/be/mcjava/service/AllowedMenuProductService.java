package be.mcjava.service;

import be.mcjava.dao.AllowedMenuProductsDao;
import be.mcjava.model.AllowedMenuProduct;

import java.util.List;

public class AllowedMenuProductService {
    private static AllowedMenuProductsDao allowedMenuProductsDao = new AllowedMenuProductsDao();

    /***
     * returns a list of AllowedMenuProducts by getting the current PreMadeOrderMenu's name
     * trough its service and getting the right AllowedMEnuProducts from the DB trough the Dao
     * @return
     */

    public static List<AllowedMenuProduct> getAllowedMenuProductsByPremadeMenuName(){
        String preMadeOrderMenuName = PreMadeOrderMenuService.preMadeOrderMenu.getName();
        return allowedMenuProductsDao.getAllowedMenuProductsByPremadeMenuName(preMadeOrderMenuName);
    }
}
