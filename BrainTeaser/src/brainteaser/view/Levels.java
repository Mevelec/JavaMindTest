package brainteaser.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class Levels {
    private Scene scene;
    private Button ButtonMenu;
    private Button ButtonPlay;
    private ListView<String> list;
    private GridPane DescriptionPane;
    private Label selectedName;
    private Label selectedFormat;
    private Label selectedNbObjectives;
    private Label selectedNbWarps;
    private Label selectedNbBonusObjectives;
    private Label selectedNbWalls;
    
    //////////////////////////////////////////// 
    
    public Levels(double width, double height)
    {
        GridPane root = new GridPane();
        scene  = new Scene(root, width, height);
        //Setting size for the pane 
        root.setMinSize(width, height);

        //Setting the padding  
        root.setPadding(new Insets(10, 10, 10, 10)); 

        //Setting the vertical and horizontal gaps between the columns 
        root.setVgap(5); 
        root.setHgap(5);       

        //Setting the Grid alignment 
        root.setAlignment(Pos.CENTER); 
            
        list = new ListView<String>();
        list.setPrefWidth(400);
        list.setPrefHeight(600);
        root.add(list, 0 ,0);
        /********************************************/
        DescriptionPane = new GridPane();
        DescriptionPane.setPrefWidth(400);
        DescriptionPane.setPrefHeight(600);
        selectedName = new Label();
        selectedFormat = new Label();
        selectedNbObjectives = new Label();
        selectedNbWarps = new Label();
        selectedNbBonusObjectives = new Label();
        selectedNbWalls = new Label();
        
        DescriptionPane.setId("DescriptionLevel");

        selectedName.setPadding(new Insets(20, 0, 0, 5));
        DescriptionPane.add(selectedName, 0, 0); 
        DescriptionPane.add(selectedFormat, 0, 1);
        selectedFormat.setPadding(new Insets(10, 0, 0, 20));
        
        DescriptionPane.add(selectedNbObjectives, 0, 2);
        selectedNbObjectives.setPadding(new Insets(0, 0, 0, 20));
        
        DescriptionPane.add(selectedNbWarps, 0, 3);
        selectedNbWarps.setPadding(new Insets(0, 0, 0, 20));
        
        DescriptionPane.add(selectedNbBonusObjectives, 0, 4);
        selectedNbBonusObjectives.setPadding(new Insets(0, 0, 0, 20));
        
        DescriptionPane.add(selectedNbWalls, 0, 5);
        selectedNbWalls.setPadding(new Insets(0, 0, 0, 20));
        
        this.updateDescription("", "", "", "", "", "");
        root.add(DescriptionPane, 1, 0);
        
        
        ButtonMenu = new Button("Menu");
        root.add(ButtonMenu, 0 ,1);
        
        ButtonPlay = new Button("Jouer");
        root.add(ButtonPlay,1 ,1);
        
        scene.getStylesheets().add("css/menu.css");
    }
    public void updateDescription(String Name,String Format,String NbObjectives,String NbWarps,String NbBonusObjectives,String NbWalls)
    {
        selectedName.setText("Nom : "+Name);
        selectedFormat.setText("Format : "+Format);
        selectedNbObjectives.setText("NbObjectives : "+NbObjectives);
        selectedNbWarps.setText("NbWarps : "+NbWarps);
        selectedNbBonusObjectives.setText("NbBonusObjectives : "+NbBonusObjectives);
        selectedNbWalls.setText("NbWalls : "+NbWalls);
    }
    
    public Button getButtonMenu() 
    {
        return ButtonMenu;
    }

    public Scene getScene() 
    {
        return scene;
    }
    
    public ListView<String> getList() 
    {
        return list;
    }
    
    public void setList(String[]  itemsArray)
   {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i=0; i< itemsArray.length; i++)
        {
            items.add(itemsArray[i]);
        }
        //list.getItems().clear();
        list.setItems(items);
    }

    public Button getButtonPlay() {
        return ButtonPlay;
    }
    
}
