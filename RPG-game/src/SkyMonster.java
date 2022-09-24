
import java.util.*;

/**
 * 
 */
public class SkyMonster extends Monster {

	private Random rand;
	private String monsterList[] = {"成群結隊的麻雀", "飛天彩虹貓", "外星人", "風箏"};
	private HashMap<String, String> info = new HashMap<>();
    /**
     * Default constructor
     */
    public SkyMonster() {
    	super();
    	loadInfo(info);
    	rand = new Random();
    	Name = monsterList[rand.nextInt(4)];
    	Description = info.get(Name);
    	switch(Name) {
    	case "成群結隊的麻雀":
    		HP = 30;
        	Attack = 40;
        	Speed = 30;
        	EXP = 10;
        	break;
    	case "飛天彩虹貓":
    		HP = 40;
        	Attack = 30;
        	Speed = 40;
        	EXP = 15;
        	break;
    	case "外星人":
    		HP = 200;
        	Attack = 90;
        	Speed = 25;
        	EXP = 20;
        	break;
    	case "風箏":
    		HP = 50;
        	Attack = 20;
        	Speed = 10;
        	EXP = 5;
        	break;
    	}
    }

    private void loadInfo(HashMap<String, String> info) {
    	info.put("成群結隊的麻雀", "啾啾啾啾啾啾啾啾啾!");
    	info.put("飛天彩虹貓", "由果醬吐司和貓咪組合成的永動機--飛天彩虹貓，這趟旅程永不落地!");
    	info.put("外星人", "嗯，就是來自別的星球的人。");
    	info.put("風箏", "我是向來不愛放風箏的，不但不愛，並且嫌惡它，因為我以為這是沒出息孩子所做的玩藝。--魯迅");
    }
    
    @Override
    public void sleep() {
    	switch(Name) {
    	case "成群結隊的麻雀":
    		HP = 30;
        	break;
    	case "飛天彩虹貓":
    		HP = 40;
        	break;
    	case "外星人":
    		HP = 200;
        	break;
    	case "風箏":
    		HP = 50;
        	break;
    	}
    	System.out.println(Name+"在你吃仙丹的期間睡了一覺，感到精力充沛，HP回滿了!");
    }
    
    @Override
    String getType() {
    	return "Sky";
    }
}