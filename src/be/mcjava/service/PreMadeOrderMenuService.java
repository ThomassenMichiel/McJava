package be.mcjava.service;

import be.mcjava.dao.PreMadeOrderMenuDao;
import be.mcjava.model.PreMadeOrderMenu;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class PreMadeOrderMenuService {
    private static PreMadeOrderMenuDao preMadeOrderMenuDao = new PreMadeOrderMenuDao();

    public static PreMadeOrderMenu preMadeOrderMenu;

    /***
     * this creates a new PreMadeOrderMenu
     * @param nextOrderItemId id to assign to the new PreMadeMenuOrder
     * @param orderItemName fixed menu name
     * @param price fixed menu price
     */

    public static void createPreMadeOrderMenu(long nextOrderItemId, String orderItemName, BigDecimal price) {
        preMadeOrderMenu = new PreMadeOrderMenu.Builder()
                .withId(nextOrderItemId)
                .withName(orderItemName)
                .withPrice(price)
                .withAmount(1)
                .build();
    }

    /***
     * returns a list on PreMadeOrderMenus with Id's in the given range
     * @param fromId lower bound
     * @param toId higher bound
     * @return
     * @throws SQLException
     */

    public static List<PreMadeOrderMenu> getMenusByIdRange(int fromId, int toId) throws SQLException {
        return preMadeOrderMenuDao.populatePreMadeOrderMenuByIdRange(fromId,toId);
    }

    /***
     * returns a PreMadeMenuOrder from given list with the supplied name
     * @param preMadeOrderMenuList list of PreMadeOrderMenus to search in
     * @param menuName name to find in the supplied list
     * @return
     */
    public static PreMadeOrderMenu getOriginalPremadeOrderByMenuName(List<PreMadeOrderMenu> preMadeOrderMenuList, String menuName) {
        return preMadeOrderMenuList.stream().filter(p -> p.getName().equals(menuName)).findFirst().get();
    }
}
