package be.mcjava.model;

public class AllowedMenuProduct {
    private String preMadeMenuName;
    private Integer itemPositionInMenu;
    private String productName;

    public String getPreMadeMenuName() {
        return preMadeMenuName;
    }

    public Integer getItemPositionInMenu() {
        return itemPositionInMenu;
    }

    public String getProductName() {
        return productName;
    }

    public AllowedMenuProduct(String preMadeMenuName, Integer itemPositionInMenu, String productName) {
        this.preMadeMenuName = preMadeMenuName;
        this.itemPositionInMenu = itemPositionInMenu;
        this.productName = productName;


    }
}
