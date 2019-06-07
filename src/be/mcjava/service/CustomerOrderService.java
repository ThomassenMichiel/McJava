package be.mcjava.service;


import be.mcjava.dao.CustomerOrderDao;
import be.mcjava.model.CustomerOrder;
import be.mcjava.model.PreMadeOrderMenu;

public class CustomerOrderService {
    public static CustomerOrder customerOrder;

    private static CustomerOrderDao customerOrderDao = new CustomerOrderDao();

    /***
     * creates a new empty CustomerOrder object to store all entered data
     * the customer's name and phoneNumber is set during
     * creation
     * @param name
     * @param customerPhoneNumber
     */

    public static void startNewCustomerOrder(String name,String customerPhoneNumber){
        customerOrder = new CustomerOrder.Builder()
                .name(name)
                .telephoneNumber(customerPhoneNumber)
                .build();
    }

    /***
     * creates a new orderItem that can be added to the CustomerOrder's
     * list of orderItems
     * id,name and price are set during creation
     * id is calculated
     * name from arguments
     * price is copied from Pre-existing OrderItem from arguments
     * @param orderItemName
     * @param orginalOrderItem)
     */
    public static void createNewOrderItem(String orderItemName, PreMadeOrderMenu orginalOrderItem) {
        PreMadeOrderMenuService.createPreMadeOrderMenu(getNextOrderItemId(),orderItemName,orginalOrderItem.getPrice());
    }

    /***
     * returns an id to give assign to a new orderItem calculated as
     * number of orderItems currently in this CustomerOrder + 1
     * if there are no orderItems present the default value (of 1)
     * is returned
     * @return
     */
    private static long getNextOrderItemId(){
        long id = 1L;
        if(customerOrder.getItemsToOrder() != null){
            id += customerOrder.getItemsToOrder().size();
        }
        return id;
    }

    /***
     * this saves the current order into the database
     * by using the CustomerOrderDao
     */
    public static void saveCustomerOrder() {
        customerOrderDao.saveCustomerOrder(customerOrder);
    }
}
