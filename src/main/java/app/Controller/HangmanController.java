package app.Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class HangmanController {
    @FXML
    private VBox mainVBox;
    @FXML
    public ImageView img1,img2,img3,img4,img5,img6;

    public void initialize() {
        imageViews.add(img1);
        imageViews.add(img2);
        imageViews.add(img3);
        imageViews.add(img4);
        imageViews.add(img5);
        imageViews.add(img6);
        addClickEventHandlers(mainVBox);
    }

    private void addClickEventHandlers(Parent parent) {
        if (parent instanceof VBox) {
            VBox vBox = (VBox) parent;
            for (Node node : vBox.getChildren()) {
                if (node instanceof HBox) {
                    HBox hBox = (HBox) node;
                    for (Node innerNode : hBox.getChildren()) {
                        if (innerNode instanceof Label) {
                            Label label = (Label) innerNode;
                            label.setOnMouseClicked(getLabelClickHandler(label));
                        }
                    }
                }
            }
        }
    }

    private EventHandler<MouseEvent> getLabelClickHandler(Label label) {
        return event -> {
            System.out.println("Clicked on label: " + label.getText());
            imageViews.get(i).setVisible(true);
            i++;
        };
    }

    private int i = 0;
    private final List<ImageView> imageViews = new ArrayList<>();
}
