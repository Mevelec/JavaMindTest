package brainteaser.model.grids;

import java.io.File;
import java.io.FileReader;
import java.util.*;
import org.json.*;

public class GridsManager {
    private int higherLevel;
    private Vector<Grid> gridList = new Vector();
    private int indPlay = 0;
    
    ////////////////////////////////////////////    

    public GridsManager()
    {
        this.higherLevel =0;
        loadLevels();
    }
    
    public void setPlay(int ind)
    {
        this.indPlay = ind;
    }
    
    public Grid play()
    {
        return gridList.elementAt(indPlay);
    }
    
    private void loadLevels()
    {
        File dir = new File("src/ressources/levels");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
          for (File child : directoryListing) {
            try {
                /*******************PARSING*************************/
                JSONObject obj = new JSONObject(new JSONTokener(new FileReader(child)));
                String name = obj.getJSONObject("header").getString("name");
                int X = obj.getJSONObject("header").getJSONObject("grid").getJSONObject("dimension").getInt("X");
                int Y = obj.getJSONObject("header").getJSONObject("grid").getJSONObject("dimension").getInt("Y");
                int nbObjectives = obj.getJSONObject("header").getJSONObject("grid").getInt("nbObjectives");
                int nbWarps = obj.getJSONObject("header").getJSONObject("grid").getInt("nbWarps");
                int nbBonusObjectives = obj.getJSONObject("header").getJSONObject("grid").getInt("nbBonusObjectives");
                int nbWalls = obj.getJSONObject("header").getJSONObject("grid").getInt("nbWalls");
                JSONArray gridArrayJSON = obj.getJSONArray("grid");
                
                if (gridArrayJSON == null) { /*..TODO Throw error...*/ }
                
                double[][] gridArray = new double[X][];
                 for(int i = 0; i < X; i++){

                    JSONArray it = gridArrayJSON.getJSONArray(i);
                    double[] itRes = new double[Y];

                    for(int j = 0; j < Y; j++){               
                        itRes[j] = it.getDouble(j);               
                    }

                    gridArray[i] = itRes;
                }

                
                /*******************Asignation*************************/
//                System.out.println("Name : "+name);
//                System.out.println("  X : "+X+" "+" Y :"+Y);
//                System.out.println("  nbObjectives : "+nbObjectives);
//                System.out.println("  nbWarps : "+nbWarps);
//                System.out.println("  nbBonusObjectives : "+nbBonusObjectives);
//                System.out.println("  nbWalls : "+nbWalls);
                
                Grid tmp = new Grid(X, Y);
                tmp.setName(name);
                tmp.setNbBonusObjectives(nbBonusObjectives);
                tmp.setNbObjectives(nbObjectives);
                tmp.setNbWalls(nbWalls);
                tmp.setNbWarps(nbWarps);
                tmp.setGrid(gridArray);
                gridList.add(tmp);
            } catch(Exception e) {
                e.printStackTrace();
            }
          }
        }
        indPlay = gridList.size()-1;
    }
    
    public String[] getGridsName()
    {
        String[] r = new String[gridList.size()];
        for(int i =0; i< gridList.size(); i++)
        {
            r[i]=gridList.get(i).getName();
        }
        return r;
    }
    
    public Grid getGrid(int indice)
    {
        return gridList.get(indice);
    }
    
    public int getHigherLevel() {
        return higherLevel;
    }

    public void setHigherLevel(int higherLevel) {
        if(higherLevel<this.getGridsName().length && this.higherLevel < higherLevel)
        {
            this.higherLevel = higherLevel;
        }
    }

    public int getIndPlay() {
        return indPlay;
    }    
}
