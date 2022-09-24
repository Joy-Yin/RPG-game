
import java.util.*;

/**
 * 
 */
public class LandMonster extends Monster {

	private Random rand;
	private String monsterList[] = {"史萊姆", "炸蝦", "獨角獸", "屁孩"};
	private HashMap<String, String> info = new HashMap<>();
	
    /**
     * Default constructor
     */
    public LandMonster() {
    	super();
    	loadInfo(info);
    	rand = new Random();
    	Name = monsterList[rand.nextInt(4)];
    	Description = info.get(Name);
    	switch(Name) {
    	case "史萊姆":
    		HP = 30;
        	Attack = 11;
        	Speed = 10;
        	EXP = 5;
        	break;
    	case "炸蝦":
    		HP = 50;
        	Attack = 60;
        	Speed = 5;
        	EXP = 10;
        	break;
    	case "獨角獸":
    		HP = 150;
        	Attack = 85;
        	Speed = 30;
        	EXP = 20;
        	break;
    	case "屁孩":
    		HP = 90;
        	Attack = 90;
        	Speed = 15;
        	EXP = 15;
        	break;
    	}
    }
    
    private void loadInfo(HashMap<String, String> info) {
    	info.put("史萊姆", "像果凍的怪物，最近的煩惱是要如何成為大明星。");
    	info.put("炸蝦", "油炸後重獲新生的蝦子，興趣是看動物星球頻道。");
    	info.put("獨角獸", "住在幻想世界的生物，夢想是環遊各次元。");
    	info.put("屁孩", "隨處可見的角色，破壞模型公仔能力超群。");
    }
    
    @Override
    public void sleep() {
    	switch(Name) {
    	case "史萊姆":
    		HP = 30;
        	break;
    	case "炸蝦":
    		HP = 50;
        	break;
    	case "獨角獸":
    		HP = 150;
        	break;
    	case "屁孩":
    		HP = 90;
        	break;
    	}
    	System.out.println(Name+"在你吃仙丹的期間睡了一覺，感到精力充沛，HP回滿了!");
    }
    
    @Override
    String getType() {
    	return "Land";
    }

}