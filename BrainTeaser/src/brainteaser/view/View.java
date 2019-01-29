package brainteaser.view;

import java.awt.Toolkit;
import javafx.stage.Stage;

public class View {   
    public Stage stage;
    private Menu menu;
    private Levels levels;
    private Play play;
    private double height, width;

    ///////////////////////////////////////////////////////////////////////////    

    public View(Stage primaryStage){
        stage = primaryStage;      
        stage.sizeToScene();
        stage.setTitle("Brain Teaser");

    //        frame.getContentPane().setLayout(new BorderLayout());                                          
    //        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    //        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    //        frame.setUndecorated(true);
                
        height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        
        menu = new Menu(width, height);
        levels = new Levels(width, height);
        play = new Play(width, height);
             
        showMenu();
        primaryStage.show();

    //        Popup popup = new Popup(); popup.setX(0); popup.setY(0);
    //        popup.getContent().addAll(new Label("a label"));
    //        popup.show(stage);
    }
    
    public void showMenu(){
        stage.setScene(menu.getScene());
        stage.setMaximized(true);
        
    }
    
    public void showLevels(){
        stage.setScene(levels.getScene());
        stage.sizeToScene();
    }
    
    public void showPlay(){
        stage.setScene(play.getScene());
        stage.sizeToScene();
        play.UpdateTimer(0);
    }
    
    public void popup(String title, String description){
        WinPopup tmp = new WinPopup(this.stage,title, description);
    }
    
    public void popupTop(String title, String description){
        WinPopup tmp = new WinPopup(this.stage,title, description, 0, 0);
    }
        
    public Menu getMenu() {
        return menu;
    }

    public Levels getLevels() {
        return levels;
    }    

    public Play getPlay() {
        return play;
    }
}