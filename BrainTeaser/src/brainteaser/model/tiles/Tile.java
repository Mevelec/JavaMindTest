package brainteaser.model.tiles;

public abstract class Tile {
    private int objectiveNb = 0;  // set to -1 when not asigned
    protected String type;    
    
    ////////////////////////////////////////////  
    
    public int getObjectiveNb() {
        return objectiveNb;
    }
    public void setObjectiveNb(int objectiveNb) {
        this.objectiveNb = objectiveNb;
    } 

    public String getType() {
        return type;
    }
}

