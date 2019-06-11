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
                Label amountLabel = new Label(Integer.toString(singleOrderItem.getAmount()));
                Label label = new Label(singleOrderItem.getItems().getName());
                Button button = new Button("Remove product ");
                button.setOnMouseClicked(mouseEvent -> removeItemFromOrderHandler(mouseEvent));
                HBox hBox = new HBox();
                Region region = new Region();
                HBox.setHgrow(region, Priority.ALWAYS);
                hBox.setPrefWidth(796);
                hBox.getChildren().add(amountLabel);
                hBox.getChildren().add(label);
                hBox.getChildren().add(region);
                hBox.getChildren().add(button);
                overviewFlowPane.getChildren().add(hBox);
            }
            if (abstractOrderItem instanceof PreMadeOrderMenu) {
                List<SingleOrderItem> singleOrderItemList = ((PreMadeOrderMenu) abstractOrderItem).getItems();
                Label amountLabel = new Label(Integer.toString(abstractOrderItem.getAmount()));
                Label label = new Label(((PreMadeOrderMenu) abstractOrderItem).getName());
                Button button = new Button("Remove menu");
                button.setOnMouseClicked(mouseEvent -> removeItemFromOrderHandler(mouseEvent));
                HBox hBox = new HBox();
                Region region = new Region();
                region.setMinWidth(10);
                HBox.setHgrow(region, Priority.ALWAYS);
                hBox.setPrefWidth(796);
                hBox.getChildren().add(amountLabel);
                hBox.getChildren().add(label);
                hBox.getChildren().add(region);
                hBox.getChildren().add(button);
                VBox preMadeMenuVBox = new VBox();
                //overviewFlowPane.getChildren().add(hBox);
                preMadeMenuVBox.getChildren().add(hBox);
                for (SingleOrderItem singleOrderItem : singleOrderItemList) {
                    Region indentRegion = new Region();
                    indentRegion.setMinWidth(50);
                    Label itemLabel = new Label(singleOrderItem.getItems().getName());
                    HBox itemHBox = new HBox();
                    itemHBox.setPrefWidth(796);
                    Region region1 = new Region();
                    HBox.setHgrow(region1, Priority.ALWAYS);
                    itemHBox.getChildren().add(indentRegion);
                    itemHBox.getChildren().add(itemLabel);
                    itemHBox.getChildren().add(region1);
                    //overviewFlowPane.getChildren().add(itemHBox);
                    preMadeMenuVBox.getChildren().add(itemHBox);
                }
                overviewFlowPane.getChildren().add(preMadeMenuVBox);
            }
        }
    }

    private void removeItemFromOrderHandler(MouseEvent mouseEvent) {
        String productNameToRemoveFromOrder = ((Label) ((HBox) ((Button) mouseEvent.getSource()).getParent()).getChildren().get(1)).getText();
        CustomerOrderService.removeProductFromCurrentOrderByName(productNameToRemoveFromOrder);
        removeCurrentProductFromOverview((Button) mouseEvent.getSource());
    }

    private void removeCurrentProductFromOverview(Button buttonOfWhichParentNeedsRemoval) {
        if(buttonOfWhichParentNeedsRemoval.getParent().getParent() instanceof VBox) {
            overviewFlowPane.getChildren().remove(buttonOfWhichParentNeedsRemoval.getParent().getParent());
        }else{
            overviewFlowPane.getChildren().remove(buttonOfWhichParentNeedsRemoval.getParent());
        }
    }

    public void closeCustomerOverview(ActionEvent actionEvent) {
        ViewManager viewManager = new ViewManager();
        viewManager.displayFmxlScreen("/be/mcjava/view/CustomerMainMenuOverview.fxml");
    }
}
