
import java.util.*;

/**
 * 
 */
public class Mount {

	private Random rand;
	private String buffList[] = {"speedUp", "defenseUp", "attackUp"};
    /**
     * Default constructor
     */
    public Mount() {
    	rand = new Random();
    	Buff = buffList[rand.nextInt(3)];
    	if(Buff.equals("speedUp")) {
    		Speed = 10;
    		Attack = -10;
    	}
    	else if(Buff.equals("defenseUp")){
    		Speed = -10;
    		Defense = 10;
    	}
    	else if(Buff.equals("attackUp")){
    		Attack = 10;
    		Defense = -10;
    	}
    }

    /**
     * 
     */
    public String Buff;

    /**
     * 
     */
    public int Speed;
    
    public int Defense;
    
    public int Attack;

}