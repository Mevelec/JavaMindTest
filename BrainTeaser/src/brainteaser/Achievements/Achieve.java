package brainteaser.Achievements;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class Achieve {

  private static Achieve instance = null;
    
  // activation rules
  public static final String ACTIVE_IF_GREATER_THAN = ">";
  public static final String ACTIVE_IF_LESS_THAN = "<";
  public static final String ACTIVE_IF_EQUALS_TO = "==";
 
  private HashMap<String, Properties> props ; // dictionary of properties
  private HashMap<String, Achievement> achievements; // dictionary of achievements
 
  ////////////////////////////////////////////    

  protected  Achieve() {
    this.props = new HashMap<String, Properties>();
    this.achievements = new HashMap<String, Achievement>();
  }
  
  public static Achieve getAchieve()
  {
      if(instance == null) {
         instance = new Achieve();
      }
      return instance;
  }
  
    public void defineProperty(String name, int initialValue, String activationMode, int value){
        Properties tmp =new Properties(name, initialValue, activationMode, value);
        this.props.put(name, tmp);
    }

    public void defineAchievement(String name, Vector<String> relatedProps){
        Vector<Properties> tmp =  new Vector<Properties>();
        for(String it : relatedProps)
        {
            tmp.add(props.get(it));
        }
      achievements.put(name,new Achievement(name, tmp));
}
    public int getValue(String prop){
      return props.get(prop).getValue();
    }

    public void addValue(Vector<String> props, int theValue){
        for (int i = 0; i < props.size(); i++) {
            String propName = props.get(i);
            setValue(propName, getValue(propName) + theValue);
        }
    }
        
      public Vector<Achievement> checkAchievements() {
          Vector<Achievement> aRet = new Vector();

            for (Map.Entry<String,Achievement> it : achievements.entrySet()) {
               Achievement aAchievement = it.getValue();

            if (aAchievement.getUnlocked() == false) {
              int aActiveProps = 0;

              for (int p = 0; p < aAchievement.getProps().size(); p++) {
                  Properties aProp = aAchievement.getProps().get(p);
                  System.out.println(aProp.getValue());

                if (aProp.isActive()) {
                  aActiveProps++;
                }
              }

              if (aActiveProps == aAchievement.getProps().size()) {
                aAchievement.setUnlocked();
                aRet.add(aAchievement);
              }
            }
          }
            System.out.println(aRet);
          return aRet;
        }
       
      public void setValue(String theProp, int theValue) {
           switch(props.get(theProp).getActivation()) {
             case Achieve.ACTIVE_IF_GREATER_THAN:
                 theValue = theValue > props.get(theProp).getValue() ? theValue : props.get(theProp).getValue();
                 break;
             case Achieve.ACTIVE_IF_LESS_THAN:
                 theValue = theValue < props.get(theProp).getValue() ? theValue : props.get(theProp).getValue();
                 break;
          }
          props.get(theProp).setValue(theValue);
        }
}