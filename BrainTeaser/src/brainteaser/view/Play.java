package brainteaser.view;

import brainteaser.model.tiles.Tile;
import java.util.Vector;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Play {
    private Button buttonMenu;
    private Button buttonRetry;
    private Button buttonNext;
    private Scene scene;
    private Label timerLabel;
    private Canvas  canevas;
    private Vector<String> objectivesColor;
    private int X, Y;
    private double width, height;
    private GraphicsContext g ;
    
   //////////////////////////////////////////////////////////////////////////// 
    
//    private PaintPanel paintPan;
    public Play(double width, double height) {
        canevas = new Canvas ();
        g = canevas.getGraphicsContext2D();
                
        BorderPane root = new BorderPane();
        HBox pane2 = new HBox();
        //Setting size for the pane 

        //Setting the padding  
        root.setPadding(new Insets(10, 10, 10, 10));         

        timerLabel = new Label("00 : 00 "); 
        scene  = new Scene(root, width, height-75);
        
        buttonMenu = new Button("Menu");
        buttonRetry = new Button("Recommencer");
        
        Pane pane = new Pane();
        canevas.widthProperty().bind( pane.widthProperty());
        canevas.heightProperty().bind( pane.heightProperty());
        pane.getChildren().add(canevas);
        
        buttonNext = new Button("Suivant");
        buttonNext.setDisable(true);    
        
        root.setTop(timerLabel); 
        root.setCenter(pane); 
        pane2.setPadding(new Insets(5, 5, 5, 5));
        
        pane2.setSpacing(5);
        pane2.setAlignment(Pos.CENTER);
        pane2.getChildren().add(buttonMenu);
        pane2.getChildren().add(buttonRetry);
        pane2.getChildren().add(buttonNext);
        
        root.setBottom(pane2);
        scene.getStylesheets().add("css/menu.css");
    }

    public Button getButtonRetry() {
        return buttonRetry;
    }
    
    public void draw(Vector<Vector<Tile>> grid) {
        width = canevas.getWidth();
        height = canevas.getHeight();
        
        X = grid.size();
        Y = grid.get(0).size();
        //System.out.println(X+", "+Y);
        g.clearRect(0, 0, width, height); 
        for(int i=0; i < X; i++)
        {
            for(int j=0; j < Y; j++)
            {
                int tmpCompare = grid.get(i).get(j).getObjectiveNb();
                String type = grid.get(i).get(j).getType();
                if(type == "DEFAULT"){
                    if(tmpCompare == 0)
                    {
                        g.setFill(Color.rgb(30, 30, 30));
                        g.fillRect(i+i*width/X, j+j*height/Y,width/X-X*0.1, height/Y-Y*0.1);

                    }
                    else if(tmpCompare>0)
                    {
                        g.setFill(Color.web(objectivesColor.get(tmpCompare-1), 0.6));
                        g.fillRect(i+i*width/X, j+j*height/Y,width/X-X*0.1, height/Y-Y*0.1);
                    }
                }
                else if(type == "OBJECTIVE")
                {
                    g.setFill(Color.web(objectivesColor.get(tmpCompare-1), 1));
                    g.fillRect(i+i*width/X, j+j*height/Y,width/X-X*0.1, height/Y-Y*0.1);
                }
                else if(type == "WALL")
                {
                    for(int t = 0; t!=10; t++)
                    {
                        if(t%2 == 0)
                        {
                            g.setFill(Color.rgb(175, 175, 175));
                        }
                        else
                        {
                            g.setFill(Color.rgb(125, 125, 125));
                        }
                        g.fillRect(i+t*4+i*width/X, j+t*4+j*height/Y,width/X-X*0.1-t*8, height/Y-Y*0.1-t*8);
                    }
                }   
            }
        }   
        
    }
    public void selectedTemp(int x, int y, int numColor) {   
        g.setFill(Color.web(objectivesColor.get(numColor-1), 0.8));
        g.fillRect(x+x*width/X, y+y*height/Y,width/X-X*0.1, height/Y-Y*0.1);

    }

    public Scene getScene() {
        return scene;
    }
    
    public Button getButtonMenu() {
        return buttonMenu;
    }

    public Canvas getCanevas() {
        return canevas;
    }
    
    public void setObjectivesColor(int nbObjectives) {
        int ratio =  (255*255*255)/nbObjectives;
        this.objectivesColor = new Vector<String>();
        for(int i = 1; i <= nbObjectives; i++)
        {
            //System.out.println((String)("0x"+Integer.toHexString(i*ratio)));
            String tmpColor = (String)("0x"+Integer.toHexString(i*ratio));
            objectivesColor.add(tmpColor);
        }
    }    
    public void UpdateTimer(int val) {
        int min = val/60 ;
        int sec = val%60;
        
        this.timerLabel.setText(""+min+":"+sec);
    }

    public Button getButtonNext(){
        return buttonNext;
    }
}

