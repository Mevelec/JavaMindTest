package brainteaser.controler;

import brainteaser.Achievements.Achieve;
import brainteaser.Achievements.Achievement;
import brainteaser.view.View;
import brainteaser.model.Model;
import javafx.event.*;
import java.util.Vector;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;

public class Controler {
    
    private int selectedObjective = 0;
    private boolean dragValid = false;
    private Vector<int[]> selectedBuffer;
    private int sTime =0;
    private boolean isStarted = false;
    private Timeline timer;
    private Model model;
    private View view;

    ////////////////////////////////////////////    
    
    public Controler(Model model, View view){
        this.model = model;
        this.view = view;       
        
        //update Achievements
        Achieve a = Achieve.getAchieve();
        Vector<String> prop1 = new Vector<String>();
        prop1.add("started");
        a.addValue(prop1, 1);
        this.checkAchievements();

        
    }
    
    public void LevelsControler()
    {
        view.getLevels().getButtonMenu().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.showMenu();
            }
        });
        
        view.getLevels().getButtonPlay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.getPlay().getButtonNext().setDisable(true);

                //affichage de la scene Play
                view.showPlay();
                //set la grille 
                model.getGridsManager().setPlay(   view.getLevels().getList().getSelectionModel().getSelectedIndex()   );
                //reset la grille
                model.getGridsManager().play().reset();
                //reset les couleurs des objectifs pour la vue
                view.getPlay().setObjectivesColor(model.getGridsManager().play().getNbObjectives());
                //draw le canevas
                view.getPlay().draw(model.getGridsManager().play().getGrid());

            }
        });
        
        view.getLevels().getList().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() { 
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int indice = view.getLevels().getList().getSelectionModel().getSelectedIndex();
                if(indice>=0)
                {
                    //cherche a display la page levels et les informmations relatives aux levels
                String name = model.getGridsManager().getGrid(indice).getName();
                String NbBonusObjectives = Integer.toString(model.getGridsManager().getGrid(indice).getNbBonusObjectives());
                String NbObjectives = Integer.toString(model.getGridsManager().getGrid(indice).getNbObjectives());
                String NbWalls = Integer.toString(model.getGridsManager().getGrid(indice).getNbWalls());
                String NbWarps = Integer.toString(model.getGridsManager().getGrid(indice).getNbWarps());
                String Format = Integer.toString(model.getGridsManager().getGrid(indice).getGrid().size())+", "+Integer.toString(model.getGridsManager().getGrid(indice).getGrid().get(0).size());
                view.getLevels().updateDescription(name, Format, NbObjectives, NbWarps, NbBonusObjectives, NbWalls);
                }
            }
        });
    }
    
    public void PlayControl()
    {
        //view.getPlay().getTimer().textProperty().bind(sTime);

        //--------MENU----------//
        view.getPlay().getButtonMenu().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(timer != null)
                {
                timer.stop();
                }
                sTime=0;
                isStarted = false;
                view.showMenu();
            }
        });
        
        //--------RETRY----------//
        view.getPlay().getButtonRetry().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(timer != null)
                {
                timer.stop();
                }
                sTime=0;
                isStarted = false;
                view.showPlay();
                model.getGridsManager().play().reset();
                //model.getGridsManager().setPlay(model.getGridsManager().getHigherLevel()-1);
                model.getGridsManager().play().reset();

                view.getPlay().setObjectivesColor(model.getGridsManager().play().getNbObjectives());
                view.getPlay().draw(model.getGridsManager().play().getGrid());

            }
        });
        
        
        //--------NEXT----------//
        view.getPlay().getButtonNext().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.getPlay().getButtonNext().setDisable(true);
                if(timer != null)
                {
                timer.stop();
                }
                sTime=0;
                isStarted = false;
                
                view.showPlay();
                model.getGridsManager().play().reset();
                model.getGridsManager().setPlay(model.getGridsManager().getHigherLevel());
                view.getPlay().setObjectivesColor(model.getGridsManager().play().getNbObjectives());
                view.getPlay().draw(model.getGridsManager().play().getGrid());

            }
        });
        //--------CLICKED--------//
        view.getPlay().getCanevas().setOnMouseClicked(e-> {
            //System.out.println(e.getX()+", "+e.getY());
        });
        
        

        //--------PRESSED--------//
        view.getPlay().getCanevas().setOnMousePressed(e-> {
            //System.out.println("PRESSED");
            if(!this.isStarted)
            {
                timer = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>(){
                                        @Override
                                        public void handle(ActionEvent arg0) {
                                            
                                                view.getPlay().UpdateTimer(sTime++);				
                                        }}));
                timer.setCycleCount(Timeline.INDEFINITE);
                timer.play();
                sTime=0;
                this.isStarted=true;
            }
            
            selectedBuffer = new Vector<int[]> ();
            
            
            
            double width = view.getPlay().getCanevas().getWidth();
            double height = view.getPlay().getCanevas().getHeight();
            
            int X = model.getGridsManager().play().getGrid().size();
            int Y = model.getGridsManager().play().getGrid().get(0).size();
            //System.out.println(X);
            
            int[] pos = formatePos((int)(e.getX()/width*X),(int)(e.getY()/height*Y));
            
            //init
            int[] tmp = {pos[0], pos[1]};
            selectedBuffer.add(tmp);
            dragValid = true;
            
            //System.out.println(pos[0]+", "+pos[1]);
            int objNb = model.getGridsManager().play().getGrid().get(pos[0]).get(pos[1]).getObjectiveNb();
            if(objNb>0 && !model.getGridsManager().play().isValide(objNb))
            {
                selectedObjective = objNb;
                //System.out.println("PRESSED "+objNb);
            }
            
            view.getPlay().draw(model.getGridsManager().play().getGrid());
        });
        //--------RELEASED--------//
        view.getPlay().getCanevas().setOnMouseReleased(e-> {
            
            double width = view.getPlay().getCanevas().getWidth();
            double height = view.getPlay().getCanevas().getHeight();
            
            int X = model.getGridsManager().play().getGrid().size();
            int Y = model.getGridsManager().play().getGrid().get(0).size();
            
            int[] pos = formatePos((int)(e.getX()/width*X),(int)(e.getY()/height*Y));
            
            //System.out.println(pos[0]+", "+pos[1]);
            int objNb = model.getGridsManager().play().getGrid().get(pos[0]).get(pos[1]).getObjectiveNb();
            //System.out.println("RELEASED");
            //System.out.println(dragValid);


            //chech if drag is continuous
            for(int i =1; i<selectedBuffer.size();  i++)
            {
                int t= i-1;
                int[] prev = selectedBuffer.get(t);
                int[] actu = selectedBuffer.get(i);
                
                

                if(!(prev[0] == actu[0] && prev[1]== actu[1]))
                {
                                                System.out.println(prev[0]+" "+prev[1]+"     " + actu[0]+" "+actu[1]);

                    if(prev[0] == actu[0])
                    {
                        if(prev[1] !=  actu[1]-1 && prev[1] != actu[1]+1)
                        {
                            System.out.println(actu[1]+ " " + prev[1]);
                            dragValid = false;
                        }
                    }
                    else if(prev[1] == actu[1])
                    {
                        if(actu[0] != prev[0]-1 && actu[0] != prev[0]+1)
                        {
                            System.out.println(actu[0]+ " " + prev[0]);
                        dragValid = false;  
                        }
                    }
                }
            }
            boolean notSameTile = (selectedBuffer.firstElement()[0] != selectedBuffer.lastElement()[0]) && (selectedBuffer.firstElement()[1] != selectedBuffer.lastElement()[1]);

            if(selectedObjective == objNb && model.getGridsManager().play().getGrid().get(pos[0]).get(pos[1]).getType() == "OBJECTIVE" && notSameTile && dragValid  )
            {
                for(int[] i: selectedBuffer)
                {
                    //System.out.println(i[0]+", "+i[1]);
                    model.getGridsManager().play().getGrid().get(i[0]).get(i[1]).setObjectiveNb(selectedObjective);
                }
                
                int valide = (int)model.getGridsManager().play().validObjective(selectedObjective);
                if(valide != 0){
                    view.popup(" Gagné", " vous avez gagné avec un score de : "+ valide);
                    model.getGridsManager().setHigherLevel(model.getGridsManager().getIndPlay()+1);
                    timer.stop();
                    view.getPlay().getButtonNext().setDisable(false);
                    this.isStarted = false;
                    
                    //ACHIEVEMENTS UPDATES
                    //update Achievements
                    Achieve a = Achieve.getAchieve();
                    Vector<String> prop1 = new Vector<String>();
                    prop1.add("niveaux terminés");
                    a.addValue(prop1, 1);
                    a.setValue("50%", valide);
                    a.setValue("100%", valide);
                    
                    this.checkAchievements();
                }
            }
            selectedObjective = 0;

            view.getPlay().draw(model.getGridsManager().play().getGrid());
            
        });
        //--------DRAGGED--------//
        view.getPlay().getCanevas().setOnMouseDragged(e-> {
            double width = view.getPlay().getCanevas().getWidth();
            double height = view.getPlay().getCanevas().getHeight();
            
            int X = model.getGridsManager().play().getGrid().size();
            int Y = model.getGridsManager().play().getGrid().get(0).size();
            
            int[] pos = formatePos((int)(e.getX()/width*X),(int)(e.getY()/height*Y));

                        
            int objNb = model.getGridsManager().play().getGrid().get(pos[0]).get(pos[1]).getObjectiveNb();
            //System.out.println("DRAGGED OVER "+objNb+" at : "+pos[0]+", "+pos[1]);
            
            if(selectedObjective>0 && objNb == 0)
            {
                int[] tmp = {pos[0], pos[1]};
                if(!selectedBuffer.isEmpty())
                {
                    boolean exist = false;
                    for(int[] it : selectedBuffer)
                    {
                        if((it[0] ==  tmp[0]) && (it[1] == tmp[1]))
                        {
                            exist = true;
                        }
                    }
                    if(!exist)
                    {
                        if( model.getGridsManager().play().getGrid().get(pos[0]).get(pos[1]).getType() == "DEFAULT" && (model.getGridsManager().play().getGrid().get(pos[0]).get(pos[1]).getObjectiveNb() ==0 || model.getGridsManager().play().getGrid().get(pos[0]).get(pos[1]).getObjectiveNb()== selectedObjective))
                        {
                            //Check adjacent X
                            if(  (selectedBuffer.lastElement()[0]+1 == tmp[0] || selectedBuffer.lastElement()[0]-1 == tmp[0]) && selectedBuffer.lastElement()[1] == tmp[1])
                            {
                                //System.out.println("added"+tmp[0]+"  "+tmp[1]);
                                dragValid = true;
                            }
                            //check adjacent Y
                            else if(  (selectedBuffer.lastElement()[1]+1 == tmp[1] || selectedBuffer.lastElement()[1]-1 == tmp[1]) && selectedBuffer.lastElement()[0] == tmp[0])
                            {
                                //System.out.println("added"+tmp[0]+"  "+tmp[1]);
                                dragValid = true;
                            }
                            else 
                            {
                                dragValid = false;
                            }
                        }
                        else
                        {
                            dragValid = false;
                        }
                        if(dragValid)
                        {
                           selectedBuffer.add(tmp); 
                        }
                    }

                }
                view.getPlay().selectedTemp( pos[0], pos[1], selectedObjective);
            }
        });
        //--------EXITED--------//
         view.getPlay().getCanevas().setOnMouseExited(e-> {
             selectedObjective=0;
             view.getPlay().draw(model.getGridsManager().play().getGrid());
         });
        
        //--------RESIZE WIDTH--------//
        view.getPlay().getScene().widthProperty().addListener((obs, oldVal, newVal) -> {
            //System.out.println(oldVal+" -> "+newVal);
            view.getPlay().draw(model.getGridsManager().play().getGrid());

        });
        //--------RESIZE HEIGHT--------//
        view.getPlay().getScene().heightProperty().addListener((obs, oldVal, newVal) -> {
             //System.out.println(oldVal+" -> "+newVal);
             view.getPlay().draw(model.getGridsManager().play().getGrid());
        });
        

    }
    
    public void MenuControl()
    {
        

        view.getMenu().getButtonPlay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.showPlay();
                model.getGridsManager().play().reset();
                model.getGridsManager().setPlay(model.getGridsManager().getHigherLevel());
                model.getGridsManager().play().reset();

                view.getPlay().setObjectivesColor(model.getGridsManager().play().getNbObjectives());
                view.getPlay().draw(model.getGridsManager().play().getGrid());

            }
        });
        
        /*************LEVELS***************/
        view.getMenu().getButtonLevels().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.getPlay().getButtonNext().setDisable(true);
                String[] param = model.getGridsManager().getGridsName();
                view.getLevels().setList(param);
                view.getLevels().getList().getSelectionModel().selectFirst();
                view.showLevels();
            }
        });
        
        view.getMenu().getButtonQuit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 Platform.exit();
            }
        });
    }   
    
    private int[] formatePos(int X, int Y)
    {
        int[] r =new int[2];
        if(X<0)
        {
            r[0]=0;
        }
        else if(X >  model.getGridsManager().play().getGrid().size())
        {
            r[0]=model.getGridsManager().play().getGrid().size();
        }
        else
        {
            r[0]=X;
        }
        
        if(Y<0)
        {
            r[1]=0;
        }
        else if(Y >  model.getGridsManager().play().getGrid().get(0).size())
        {
            r[1]=model.getGridsManager().play().getGrid().get(0).size();
        }
        else
        {
            r[1]=Y;
        }
        return r;
    }
    
    //check achievements et les displays
    public void checkAchievements()
    {
        Vector<Achievement> aRet = Achieve.getAchieve().checkAchievements();
        for(Achievement it : aRet)
        {
            view.popupTop(" SUCCES", " vous avez débloqué le succé : " + it.getName());
        }
    }
}