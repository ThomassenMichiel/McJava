package be.mcjava.service;

import be.mcjava.model.CustomerOrder;
import be.mcjava.model.Product;
import be.mcjava.model.SingleOrderItem;

import java.util.ArrayList;
import java.util.List;

public class SingleOrderItemService {
    public static void addProductsAssSingleOrderItems(List<String> productToOrderNamesList) {
        List<SingleOrderItem> singleOrderItemList = new ArrayList<>();
        for (String productName : productToOrderNamesList) {
            Product product = ProductService.getProductByName(productName);
            SingleOrderItem singleOrderItem = new SingleOrderItem();
            singleOrderItem.setItems(product);
            singleOrderItem.setPrice(product.getPrice());
            singleOrderItem.setAmount(1);
            singleOrderItemList.add(singleOrderItem);
        }
        for (SingleOrderItem singleOrderItem : singleOrderItemList) {
            CustomerOrderService.customerOrder.addItem(singleOrderItem);
        }
    }
}
