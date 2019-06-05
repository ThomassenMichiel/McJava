package be.mcjava.model;

public class AllowedMenuProduct {
    private Long premadeMenuId;
    private Integer itemPositionInMenu;
    private Long productId;

    public AllowedMenuProduct(Long premadeMenuId, Integer itemPositionInMenu, Long productId) {
        this.premadeMenuId = premadeMenuId;
        this.itemPositionInMenu = itemPositionInMenu;
        this.productId = productId;
    }

    public Long getPremadeMenuId() {
        return premadeMenuId;
    }

    public Integer getItemPositionInMenu() {
        return itemPositionInMenu;
    }

    public Long getProductId() {
        return productId;
    }
}
