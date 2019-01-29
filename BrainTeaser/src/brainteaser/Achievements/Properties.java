package brainteaser.Achievements;

public class Properties {
    private String name;
    private int value;
    private String activation;
    private int activationValue;
    private int initialValue;
    
    ////////////////////////////////////////////    

    public Properties( String name, int initialValue, String activation, int activationValue) {
    this.name = name;
    this.initialValue = initialValue;
    this.activation = activation;
    this.activationValue = activationValue;
  }

    public String getActivation(){
        return activation;
    }
    
    public int getValue(){
        return value;
    }
    
    public void setValue(int nb){
        value=nb;
    }
    
    public Boolean isActive() {
    Boolean aRet = false;
 
    switch(activation) {
      case Achieve.ACTIVE_IF_GREATER_THAN: 
          aRet = value > activationValue; 
          break;
      case Achieve.ACTIVE_IF_LESS_THAN:
          aRet = value < activationValue;
          break;
      case Achieve.ACTIVE_IF_EQUALS_TO:
          aRet = value == activationValue;
          break;
    }
 
    return aRet;
  }
    
}