package brainteaser.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Menu{
    private Scene scene;
    private Button ButtonPlay;
    private Button ButtonLevels;
    private Button ButtonQuit;
    
    //////////////////////////////////////////// 
    
    public Menu(double width, double height){    
        GridPane root = new GridPane();
        //Setting size for the pane 
        root.setMinSize(width, height);

        //Setting the padding  
        root.setPadding(new Insets(10, 10, 10, 10)); 

        //Setting the vertical and horizontal gaps between the columns 
        root.setVgap(5); 
        root.setHgap(5);       

        //Setting the Grid alignment 
        root.setAlignment(Pos.CENTER); 
      
        scene  = new Scene(root, width, height);
        ButtonPlay = new Button("Play");
        ButtonLevels = new Button("Levels");
        ButtonQuit = new Button("Quit");
        root.add(ButtonPlay, 0 ,0);
        root.add(ButtonLevels, 0 ,1);
        root.add(ButtonQuit, 0 ,2);
        
        
        scene.getStylesheets().add("css/menu.css");

        
    }
 
    public Button getButtonPlay() {
        return ButtonPlay;
    }

    public Button getButtonLevels() {
        return ButtonLevels;
    }

    public Button getButtonQuit() {
        return ButtonQuit;
    }   

    public Scene getScene() {
        return scene;
    }
}
