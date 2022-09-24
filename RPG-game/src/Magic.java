
import java.util.*;

/**
 * 
 */
public class Magic {

	private String magicType[] = {"therapy", "destroyer", "none"};//魔法種類
	private Random rand;
	int damage[] = {10,20,30,40,50,60,70,80,90,100};//各等級的魔法傷害量和回復量
	int recover[] = {30,40,50,60,70,80,90,100,110,120};
    /**
     * Default constructor
     */
    public Magic() {
    	rand = new Random();
    	Type = new String[2];//最多可以學兩種魔法
    	String temp = magicType[rand.nextInt(3)];
    	if(!temp.equals("none")) {//有可能不會魔法
    		Type[0] = temp;
    	}
    	
    }

    /**
     * 會的魔法種類
     */
    public String Type[];

}