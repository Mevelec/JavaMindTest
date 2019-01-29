package brainteaser.model;

import brainteaser.model.grids.GridsManager;

public class Model {
    private GridsManager gridsManager;

    ////////////////////////////////////////////    
    
    public Model()
    {
        this.gridsManager =  new GridsManager();
    }
    
    public GridsManager getGridsManager() {
        return gridsManager;
    }  
}