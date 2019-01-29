package brainteaser.Achievements;

import java.util.Vector;

public class Achievement {
    private String name;
    private Vector<Properties> props;
    private boolean unlocked;
    
    ////////////////////////////////////////////    

    public Achievement( String id, Vector<Properties> relatedprops) {
    this.name = id;
    this.props = relatedprops;
    this.unlocked = false;
    }
       
    public String getName(){
        return this.name;
    }
    
    public Vector<Properties> getProps(){
        return props;
    }
    
    public boolean getUnlocked(){
        return unlocked;
    }
    
    public void setUnlocked(){
        unlocked = true;
    }
    
}