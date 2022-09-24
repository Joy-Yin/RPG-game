
import java.util.*;

/**
 * 
 */
public class Weapon {

	private Random rand;
	private String typeList[] = {"Gun", "Sword", "Bow"};
	private String buffList[] = {"Land", "Water", "Sky"};
    /**
     * Default constructor
     */
    public Weapon() {
    	rand = new Random();
    	Type = typeList[rand.nextInt(3)];
    	Buff = buffList[rand.nextInt(3)];
    	switch(Type) { //基礎攻擊力與其降低的移動速度
    	case "Gun":
    		Speed = -10;
    		Attack = 15;
    		break;
    	case "Sword":
    		Speed = -15;
    		Attack = 10;
    		break;
    	case "Bow":
    		Speed = -5;
    		Attack = 5;
    		break;
    	}
    }

    /**
     * 
     */
    public int Attack;

    /**
     * 
     */
    public String Buff;

    /**
     * 
     */
    public int Speed;

    /**
     * 
     */
    public String Type;

}