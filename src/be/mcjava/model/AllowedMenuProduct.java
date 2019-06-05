package be.mcjava.model;

public class AllowedMenuProduct {
    private String premadeMenuName;
    private Integer itemPositionInMenu;
    private String productName;

    public String getPremadeMenuName() {
        return premadeMenuName;
    }

    public Integer getItemPositionInMenu() {
        return itemPositionInMenu;
    }

    public String getProductName() {
        return productName;
    }

    public AllowedMenuProduct(String premadeMenuName, Integer itemPositionInMenu, String productName) {
        this.premadeMenuName = premadeMenuName;
        this.itemPositionInMenu = itemPositionInMenu;
        this.productName = productName;


    }
}
