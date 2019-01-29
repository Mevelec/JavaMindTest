/* Adrien MEVELEC 11706966 &  Emilie FOUREAU 11509942 */
package brainteaser;

import brainteaser.Achievements.Achieve;
import brainteaser.controler.Controler;
import brainteaser.model.Model;
import brainteaser.view.View;
import java.util.Vector;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
//Initialisation des succès
    public void init() {
            Achieve a = Achieve.getAchieve();
            a.defineProperty("started", 0, Achieve.ACTIVE_IF_EQUALS_TO, 1);
            a.defineProperty("niveaux terminés", 3, Achieve.ACTIVE_IF_EQUALS_TO, 3);
            a.defineProperty("50%", 0, Achieve.ACTIVE_IF_GREATER_THAN, 50);
            a.defineProperty("100%", 0, Achieve.ACTIVE_IF_GREATER_THAN, 100);

            Vector<String> prop1 = new Vector<String>();
            prop1.add("started");
            a.defineAchievement("Jeu Démarré", prop1);
            
            Vector<String> prop3 = new Vector<String>();
            prop3.add("niveaux terminés");
            a.defineAchievement("3 niveau de terminés", prop3);
            
            Vector<String> prop4 = new Vector<String>();
            prop4.add("50%");
            a.defineAchievement("Score superieur à 50", prop4); 
            
            Vector<String> prop5 = new Vector<String>();
            prop5.add("100%");
            a.defineAchievement("Score parfait", prop5); 

        }
            
    @Override
    public void start(Stage primaryStage) {
        init();
        Model model = new Model();
        View view = new View(primaryStage); 
        Controler controler = new Controler(model, view);
        controler.MenuControl();
        controler.LevelsControler();
        controler.PlayControl();
    }
     
}