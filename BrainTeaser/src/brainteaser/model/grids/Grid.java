package brainteaser.model.grids;

import brainteaser.model.tiles.Tile;
import brainteaser.model.tiles.TilesFactory;
import java.util.Vector;

public class Grid {
    private String name;
    private TilesFactory tilesFactory;
    private Vector<Vector<Tile>> grid ;
    private int X, Y;
    private int nbObjectives;
    private int nbWarps;
    private int nbBonusObjectives;
    private int nbWalls;
    private boolean[] ObjectivesValid;
    
    ////////////////////////////////////////////    
    
    public Grid(int X, int Y)
    {
        tilesFactory = new TilesFactory();
        this.X = X;
        this.Y = Y;
        //TODO INIT grid
        grid = new Vector<Vector<Tile>>(X);
        for(int i=0; i < X; i++)
        {
            Vector<Tile> tmp = new Vector<Tile>(Y);
            for(int j=0; j < Y; j++)
            {
                tmp.add(tilesFactory.getTile("DEFAULT"));
            }
            grid.add(tmp);
        }
    }
    
    public void setGrid(double[][] gridArray)
    {
        Vector<Vector<int[]>> ObjectivesPos = new Vector<Vector<int[]>>(nbObjectives);
        for(int i =0; i<nbObjectives; i++)
        {
            ObjectivesPos.add(new Vector<int[]>());
        }
        for(int i=0; i<gridArray.length; i++)
        {
            //System.out.println(i);
            for(int j=0; j<gridArray[i].length; j++)
            {
                //System.out.println("  "+j);
                double val = gridArray[i][j];
                if(val > 0.0)
                {
                    int[] tmpPos = {j, i};
                    ObjectivesPos.get((int)(val-1)).add(tmpPos);
                }
                else if(val == -1)
                {
                    grid.get(j).set(i, tilesFactory.getTile("WALL"));
                }
            }
        }
        //**************INSTANCIATE OBJECTIVES **************************//
        for(int i =0; i<nbObjectives; i++)
        {
            Tile[] objectiveSet = tilesFactory.getTwin("OBJECTIVES");
            grid.get(ObjectivesPos.get(i).get(0)[0]).set(ObjectivesPos.get(i).get(0)[1], objectiveSet[0]);
            grid.get(ObjectivesPos.get(i).get(1)[0]).set(ObjectivesPos.get(i).get(1)[1], objectiveSet[0]);
            
        }
    }
    
    public void reset()
    {
        for(Vector<Tile> it : grid)
        {
            for(Tile it2 : it)
            {
                if(it2.getType()=="DEFAULT")
                {
                    it2.setObjectiveNb(0);
                }
            }
        }
        
        for(int i =0; i<this.ObjectivesValid.length; i++)
        {
            this.ObjectivesValid[i]=false;
        }

    }
    
    public double validObjective(int ind)
    {
        ind=ind-1;
        ObjectivesValid[ind] = true;
        
        for(boolean it : this.ObjectivesValid)
        {
            if(!it)
            {
                return 0;
            }
        }
        
        // Count nb tile used
        double r=X*Y;
        for(int i=0; i < X; i++)
        {
            for(int j=0; j < Y; j++)
            {
                if(grid.get(j).get(i).getType() == "DEFAULT" && grid.get(j).get(i).getObjectiveNb() == 0)
                {
                    r--;
                }
            }
        }
        return r/(X*Y)*100;
    }
    
    public boolean isValide(int i)
    {
        return this.ObjectivesValid[i-1];
    }
  
    public Vector<Vector<Tile>> getGrid() {
        return grid;
    }

    public int getNbObjectives() {
        return nbObjectives;
    }  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbWarps() {
        return nbWarps;
    }

    public void setNbWarps(int nbWarps) {
        this.nbWarps = nbWarps;
    }

    public int getNbBonusObjectives() {
        return nbBonusObjectives;
    }

    public void setNbBonusObjectives(int nbBonusObjectives) {
        this.nbBonusObjectives = nbBonusObjectives;
    }

    public int getNbWalls() {
        return nbWalls;
    }

    public void setNbWalls(int nbWalls) {
        this.nbWalls = nbWalls;
    }

    public void setNbObjectives(int nbObjectives) {
        this.nbObjectives = nbObjectives;
        ObjectivesValid = new boolean[nbObjectives];
        for(boolean it : ObjectivesValid)
        {
            it = false;
        }
    }  
}
