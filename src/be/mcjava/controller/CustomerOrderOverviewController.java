package be.mcjava.controller;

import be.mcjava.model.AbstractOrderItem;
import be.mcjava.model.PreMadeOrderMenu;
import be.mcjava.model.SingleOrderItem;
import be.mcjava.service.CustomerOrderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.List;

public class CustomerOrderOverviewController {

    @FXML
    private FlowPane overviewFlowPane;

    public void initialize() {
        for (AbstractOrderItem abstractOrderItem : CustomerOrderService.customerOrder.getItemsToOrder()) {
            if (abstractOrderItem instanceof SingleOrderItem) {
                SingleOrderItem singleOrderItem = ((SingleOrderItem) abstractOrderItem);
                overviewFlowPane.getChildren().add(createProductHBoxLine(singleOrderItem));
            }
            //TODO maak hier een else if van
            if (abstractOrderItem instanceof PreMadeOrderMenu) {
                PreMadeOrderMenu preMadeOrderMenu = (PreMadeOrderMenu) abstractOrderItem;
                VBox preMadeMenuVBox = new VBox();
                preMadeMenuVBox.getChildren().add(createProductHBoxLine(abstractOrderItem));
                List<SingleOrderItem> singleOrderItemList = preMadeOrderMenu.getItems();
                for (SingleOrderItem aSingleOrderItem : singleOrderItemList) {
                    Region indentRegion = new Region();
                    indentRegion.setMinWidth(50);
                    preMadeMenuVBox.getChildren().add(createProductHBoxLine(indentRegion, aSingleOrderItem));
                }
                overviewFlowPane.getChildren().add(preMadeMenuVBox);
            }
        }
    }

    private HBox createProductHBoxLine(Region indentRegion, SingleOrderItem singleOrderItem) {
        HBox itemHBox = createProductHBoxLine(singleOrderItem);
        itemHBox.getChildren().add(0, indentRegion);
        itemHBox.getChildren().remove(itemHBox.getChildren().size() - 1);
        return itemHBox;
    }

    private HBox createProductHBoxLine(AbstractOrderItem abstractOrderItem) {
        Button button;
        Label label;
        Label amountLabel;
        if (abstractOrderItem instanceof PreMadeOrderMenu) {
            amountLabel = new Label(Integer.toString(abstractOrderItem.getAmount()));
            label = new Label(((PreMadeOrderMenu) abstractOrderItem).getName());
            button = new Button("Remove menu");
        } else {
            amountLabel = new Label(Integer.toString( abstractOrderItem.getAmount()));
            label = new Label(((SingleOrderItem) abstractOrderItem).getItems().getName());
            button = new Button("Remove product ");
        }
        Region region = new Region();
        button.setOnMouseClicked(mouseEvent -> removeItemFromOrderHandler(mouseEvent));
        HBox hBox = new HBox();
        HBox.setHgrow(region, Priority.ALWAYS);
        hBox.setPrefWidth(796);
        hBox.getChildren().addAll(amountLabel, label, region, button);
        return hBox;
    }

    private void removeItemFromOrderHandler(MouseEvent mouseEvent) {
        String productNameToRemoveFromOrder = ((Label) ((HBox) ((Button) mouseEvent.getSource()).getParent()).getChildren().get(1)).getText();
        CustomerOrderService.removeProductFromCurrentOrderByName(productNameToRemoveFromOrder);
        removeCurrentProductFromOverview((Button) mouseEvent.getSource());
    }

    private void removeCurrentProductFromOverview(Button buttonOfWhichParentNeedsRemoval) {
        if (buttonOfWhichParentNeedsRemoval.getParent().getParent() instanceof VBox) {
            overviewFlowPane.getChildren().remove(buttonOfWhichParentNeedsRemoval.getParent().getParent());
        } else {
            overviewFlowPane.getChildren().remove(buttonOfWhichParentNeedsRemoval.getParent());
        }
    }

    public void closeCustomerOverview() {
        ViewManager viewManager = new ViewManager();
        viewManager.displayFmxlScreen("/be/mcjava/view/CustomerMainMenuOverview.fxml");
    }
}
