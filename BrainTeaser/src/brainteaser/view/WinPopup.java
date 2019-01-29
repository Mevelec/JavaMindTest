package brainteaser.view;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WinPopup{
    private Popup popup;
    
    ///////////////////////////////////////////////////////////////////////////
    
    public WinPopup(Stage stage, String title, String message){
        this.popup = new Popup();
        
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: rgb(50, 50, 50);");
        pane.setPrefSize(600,200);
        
        Label titleLabel = new Label(title);
        titleLabel.setTextFill( Color.rgb(250, 250, 250 ));
        pane.setTop( titleLabel );
        
        Label descriptionLabel = new Label(message);
        descriptionLabel.setTextFill(Color.rgb(250, 250, 250 ));
        pane.setCenter( descriptionLabel );
        
        this.popup.getContent().addAll(pane);
        this.popup.show(stage);
        
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event -> this.popup.hide() );
        delay.play();
    }
    
    public WinPopup(Stage stage, String title, String message, int x, int y){
        this.popup = new Popup();
        
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: rgb(50, 50, 50);");
        pane.setPrefSize(600,200);
        
        Label titleLabel = new Label(title);
        titleLabel.setTextFill( Color.rgb(250, 250, 250 ));
        pane.setTop( titleLabel );
        
        Label descriptionLabel = new Label(message);
        descriptionLabel.setTextFill(Color.rgb(250, 250, 250 ));
        pane.setCenter( descriptionLabel );
        
        this.popup.getContent().addAll(pane);
        this.popup.setX(x);
        this.popup.setY(y);
        this.popup.show(stage);
        
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event -> this.popup.hide() );
        delay.play();
    }
}
