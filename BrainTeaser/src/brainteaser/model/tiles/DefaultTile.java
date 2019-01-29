package brainteaser.model.tiles;

public class DefaultTile extends Tile{

    public DefaultTile()
    {
        this.type="DEFAULT";
    }
    
    public Tile getTwin() {
        return null;
    }

    public void setTwin(Tile twin) {
    }

}
