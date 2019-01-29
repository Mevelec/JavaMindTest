package brainteaser.model.tiles;

public class TilesFactory {
    private int nbObjectives = 0 ;
    
    ////////////////////////////////////////////   

    public Tile getTile(String tileType)
    {
        Tile r;		
        if(tileType.equalsIgnoreCase("DEFAULT")){
            return new DefaultTile();
        }
        else if(tileType.equalsIgnoreCase("WALL"))
        {
            return new WallTile();
        }
        else
        {
            return null;
        }
    }
    public Tile[] getTwin(String tileType)
    {
        Tile[] r = new Tile[2];		
        if(tileType.equalsIgnoreCase("OBJECTIVES")){
            nbObjectives++;
            r[0] = new ObjectiveTile(nbObjectives);
            r[1] = new ObjectiveTile(nbObjectives);
            return r;
        }
        else
        {
            return null;
        }
    }
}