
import java.util.*;

/**
 * 
 */
public class WaterMonster extends Monster {

	private Random rand;
	private String monsterList[] = {"海天使", "扁面蛸", "水滴魚", "花園鰻"};
	private HashMap<String, String> info = new HashMap<>();
    /**
     * Default constructor
     */
    public WaterMonster() {
    	super();
    	loadInfo(info);
    	rand = new Random();
    	Name = monsterList[rand.nextInt(4)];
    	Description = info.get(Name);
    	switch(Name) {
    	case "海天使":
    		HP = 30;
        	Attack = 80;
        	Speed = 20;
        	EXP = 10;
        	break;
    	case "扁面蛸":
    		HP = 50;
        	Attack = 30;
        	Speed = 25;
        	EXP = 10;
        	break;
    	case "水滴魚":
    		HP = 120;
        	Attack = 90;
        	Speed = 15;
        	EXP = 20;
        	break;
    	case "花園鰻":
    		HP = 50;
        	Attack = 50;
        	Speed = 50;
        	EXP = 15;
        	break;
    	}
    }
    
    private void loadInfo(HashMap<String, String> info) {
    	info.put("海天使", "海中的小天使，肉食性生物。");
    	info.put("扁面蛸", "長得像章魚小香腸，很怕自己被誤食。");
    	info.put("水滴魚", "憂鬱症患者，每天都在想什麼時候可以下班。");
    	info.put("花園鰻", "憧憬著鰻魚大哥，希望自己有朝一日能加入鰻魚幫。");
    }
    
    @Override
    public void sleep() {
    	switch(Name) {
    	case "海天使":
    		HP = 30;
        	break;
    	case "扁面蛸":
    		HP = 50;
        	break;
    	case "水滴魚":
    		HP = 120;
        	break;
    	case "花園鰻":
    		HP = 50;
        	break;
    	}
    	System.out.println(Name+"在你吃仙丹的期間睡了一覺，感到精力充沛，HP回滿了!");
    }
    
    @Override
    String getType() {
    	return "Water";
    }

}