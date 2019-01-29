package brainteaser.model.tiles;

public class ObjectiveTile extends Tile {
    private Tile twin;
    public boolean completed = false;
    
    ////////////////////////////////////////////  
    
    public ObjectiveTile(int objectiveNb)
    {
        this.setObjectiveNb(objectiveNb);
        this.type="OBJECTIVE";
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Tile getTwin() {
        return twin;
    }

    public void setTwin(Tile twin) {
        this.twin = twin;
    }
}
