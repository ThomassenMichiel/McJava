package be.mcjava.controller;

import be.mcjava.model.AbstractOrderItem;
import be.mcjava.model.CustomerOrder;
import be.mcjava.model.PreMadeOrderMenu;
import be.mcjava.model.SingleOrderItem;
import be.mcjava.service.CustomerOrderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.List;

public class CustomerOrderOverviewController {

    @FXML
    private FlowPane overviewFlowPane;

    public void initialize(){
        for (AbstractOrderItem abstractOrderItem : CustomerOrderService.customerOrder.getItemsToOrder()) {
            if(abstractOrderItem instanceof SingleOrderItem){
                SingleOrderItem singleOrderItem = ((SingleOrderItem) abstractOrderItem);
                Label label = new Label(singleOrderItem.getItems().getName());
                Button button = new Button("Remove item ");
                HBox hBox = new HBox();
                Region region = new Region();
                HBox.setHgrow(region, Priority.ALWAYS);
                hBox.setPrefWidth(796);
                hBox.getChildren().add(label);
                hBox.getChildren().add(region);
                hBox.getChildren().add(button);
                overviewFlowPane.getChildren().add(hBox);
            }
            if(abstractOrderItem instanceof PreMadeOrderMenu){
                List<SingleOrderItem> singleOrderItemList = ((PreMadeOrderMenu) abstractOrderItem).getItems();
                Label label = new Label(((PreMadeOrderMenu) abstractOrderItem).getName());
                Button button = new Button("Remove menu");
                HBox hBox = new HBox();
                Region region = new Region();
                region.setMinWidth(10);
                HBox.setHgrow(region, Priority.ALWAYS);
                hBox.setPrefWidth(796);
                hBox.getChildren().add(label);
                hBox.getChildren().add(region);
                hBox.getChildren().add(button);
                overviewFlowPane.getChildren().add(hBox);
                for (SingleOrderItem singleOrderItem : singleOrderItemList) {
                    Region indentRegion = new Region();
                    indentRegion.setMinWidth(20);
                    Label itemLabel = new Label(singleOrderItem.getItems().getName());
                    Button itemButton = new Button("Remove item");
                    HBox itemHBox = new HBox();
                    itemHBox.setPrefWidth(796);
                    Region region1 = new Region();
                    HBox.setHgrow(region1,Priority.ALWAYS);
                    itemHBox.getChildren().add(indentRegion);
                    itemHBox.getChildren().add(itemLabel);
                    itemHBox.getChildren().add(region1);
                    itemHBox.getChildren().add(itemButton);
                    overviewFlowPane.getChildren().add(itemHBox);
                }
            }
        }
    }

    public void closeCustomerOverview(ActionEvent actionEvent) {
        ViewManager viewManager = new ViewManager();
        viewManager.displayFmxlScreen("/be/mcjava/view/CustomerMainMenuOverview.fxml");
    }
}
