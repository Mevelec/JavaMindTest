package brainteaser.model.tiles;

public class WallTile extends Tile 
{
    public WallTile()
    {
        this.type="WALL";
    }
    
    public Tile getTwin() {
        return null;
    }

    public void setTwin(Tile twin) {
    }
}
