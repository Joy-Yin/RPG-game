
import java.util.*;

/**
 * 
 */
public class Hunter {

	private Random rand;
	private String skill[] = {"Land", "Water", "Sky"};//不同專長，適合打不同怪物(+5??)
	private int originHP[] = {100,110,120,130,140,150,160,170,180,190,200};//各等級的基礎能力值
	private int originMP[] = {100,110,120,130,140,150,160,170,180,190,200};
	private int elixir = 5;
	private Weapon equipped = null;
	private Mount ride = null;
    /**
     * Default constructor
     */
    public Hunter() {
    	rand = new Random();
    	Skill = skill[rand.nextInt(3)];
    	Weapon = new ArrayList<Weapon>();
    	Mount = new ArrayList<Mount>();
    	Weapon.add(new Weapon());
    	Magic = new Magic();
    }

    /**
     * 
     */
    private String Skill;

    /**
     * 
     */
    private int HP = 100;

    /**
     * 
     */
    private int MP = 100;

    /**
     * 
     */
    private ArrayList<Weapon> Weapon;
    
    /**
     * 基礎攻擊力(徒手)=10
     */
    private int Attack = 10;
    
    /**
     * 基礎防禦力=10
     */
    private int Defense = 10;
    
    /**
     * 基礎速度=20
     */
    private int Speed = 20;

    /**
     * 
     */
    private Magic Magic;

    /**
     * 
     */
    private ArrayList<Mount> Mount;

    /**
     * 20 points to next LV
     */
    private int EXP = 0;

    /**
     * 
     */
    private int LV = 1;
    
    String getSkill() {
    	return Skill;
    }
    
    int getHP() {
    	return HP;
    }
    
    void minusHP(int number) {
    	HP -= number;
    }
    
    void setHP(int number) {
    	HP = number;
    }
    
    int getMP() {
    	return MP;
    }
    
    void minusMP(int number) {
    	MP -= number;
    }
    
    int getAttack() {
    	return Attack;
    }
    
    int getSpeed() {
    	return Speed;
    }
    
    int getDefense() {
    	return Defense;
    }
    
    Magic getMagic() {
    	return Magic;
    }
    
    ArrayList<Weapon> getWeapon(){
    	return Weapon;
    }
    
    ArrayList<Mount> getMount(){
    	return Mount;
    }
    
    int getLV() {
    	return LV;
    }
    
    int getEXP() {
    	return EXP;
    }
    
    void gainEXP(int number) {
    	EXP += number;
    }
    
    int getElixir() {
    	return elixir;
    }
    
    Weapon getEquippedWeapon() {
    	return equipped;
    }
    
    Mount getRideOnMount() {
    	return ride;
    }


    void equip(Scanner sc) {
    	if(equipped==null) {
    		System.out.println("現在裝備的武器:徒手 當前攻擊力:" + Attack +" 當前速度:" + Speed);
    	}
    	else {
    		System.out.println("現在裝備的武器:" + equipped.Type + " 適用怪物類型:" + equipped.Buff + " 當前攻擊力:" + Attack +" 當前速度:" + Speed);
    	}
    	
		System.out.println("可裝備之武器:");
		
		int count = 1;
		while(count <= Weapon.size()) {
			System.out.println(count+". "+Weapon.get(count-1).Type+" 攻擊力:"+Weapon.get(count-1).Attack+" 適用怪物類型:"
					+ Weapon.get(count-1).Buff + " 移動速度:" + Weapon.get(count-1).Speed);
			count++;
		}
		System.out.println(count+". 徒手");
		int opt = Integer.parseInt(sc.nextLine());
		if(opt == count) {
			resetWeapon();
			equipped = null;
		}
		else {
			resetWeapon();
			Attack += Weapon.get(opt-1).Attack;
			Speed += Weapon.get(opt-1).Speed;
			if(Speed < 0) {
				Speed = 0;
			}
			equipped = Weapon.get(opt-1);
		}
		System.out.println("更換成功!");
    }
    
    private void resetWeapon() {
    	if(equipped==null) {
    		return;
    	}
		Attack -= equipped.Attack;
		Speed -= equipped.Speed;
	}
    
    void rideOn(Scanner sc) {
    	if(ride==null) {
    		System.out.println("現在騎乘的坐騎:無 當前攻擊力:" + Attack +" 當前速度:" + Speed + " 當前防禦力:"+ Defense);
    	}
    	else {
    		System.out.println("現在騎乘的坐騎: 坐騎加成:" + ride.Buff +" 當前攻擊力:" + Attack +" 當前速度:" + Speed + " 當前防禦力:"+ Defense);
    	}
    	
    	System.out.println("可騎乘之坐騎:");
    	int count = 1;
		while(count <= Mount.size()) {
			System.out.println(count+". 坐騎加成:"+Mount.get(count-1).Buff+" 攻擊力:"+Mount.get(count-1).Attack+" 速度:"+Mount.get(count-1).Speed+" 防禦力:"+Mount.get(count-1).Defense);
			count++;
		}
		System.out.println(count+". 不騎坐騎");
		int opt = Integer.parseInt(sc.nextLine());
		if(opt == count) {
			resetMount();
			ride = null;
		}
		else {
			resetMount();
			Attack += Mount.get(opt-1).Attack;
			if(Attack < 0)
				Attack = 0;
			Speed += Mount.get(opt-1).Speed;
			if(Speed < 0)
				Speed = 0;
			Defense += Mount.get(opt-1).Defense;
			if(Defense < 0)
				Defense = 0;
			ride = Mount.get(opt-1);
		}
		System.out.println("更換成功!");
    }
    
    void resetMount() {
    	if(ride==null) {
    		return;
    	}
    	Attack -= ride.Attack;
    	Speed -= ride.Speed;
    	Defense -= ride.Defense;
    }
    
    void autoRecoverMP() {
    	if(LV < 11) {
    		if((originMP[LV-1]-MP) < (originMP[LV-1]-95)) {//回復量比魔力槽多
    			MP = originMP[LV-1];
    			System.out.println("你的MP已回復"+(originMP[LV-1]-MP));
    		}
    		else {
    			MP += (originMP[LV-1]-95);
    			System.out.println("你的MP已回復"+(originMP[LV-1]-95));
    		}
    	}
    	else {
    		if((200-MP) < 110) {//回復量比魔力槽多
    			MP = 200;
    			System.out.println("你的MP已回復"+(200-MP));
    		}
    		else {
    			MP += 110;
    			System.out.println("你的MP已回復110");
    		}
    	}
    }
    
    boolean heal(Scanner sc) {
    	System.out.println("請選擇治療方式:");
    	System.out.println("1. 吃仙丹");
    	System.out.println("2. 使用治療魔法(therapy)");
    	int opt = Integer.parseInt(sc.nextLine());
    	if(opt == 1) {
    		System.out.println("擁有仙丹數:" + elixir);
    		System.out.println("請問要使用嗎?");
    		System.out.println("1. 是\n2. 否");
    		int check = Integer.parseInt(sc.nextLine());
    		if(check == 1) {
    			if(elixir > 0) {
    				HP += 50;
    				if(HP > originHP[LV-1]) {
    					HP = originHP[LV-1];
    				}
    				elixir -= 1;
    				System.out.println("你的HP已回復50");
    				return true;
    			}
    			else {
    				System.out.println("你的仙丹不足!");
    				return false;
    			}
    		}
    	}
    	else if(opt == 2) {
    		for(int i = 0; i < Magic.Type.length; i++) {
				if(Magic.Type[i] == null) {
					continue;
				}
				if(Magic.Type[i].equals("therapy")) {
					if(Magic.recover[LV-1] > MP) {//多少回復量就花多少MP
						System.out.println("你的MP不足!");
						return false;
					}
					else {
						MP -= Magic.recover[LV-1];
						if((originHP[LV-1]-HP) < Magic.recover[LV-1]) {//超過血量上限
							HP = originHP[LV-1];
							System.out.println("你的HP已回復"+(originHP[LV-1]-HP));
						}
						else {
							HP += Magic.recover[LV-1];
							System.out.println("你的HP已回復"+Magic.recover[LV-1]);
						}
						return false;
					}
				}
			}
    		System.out.println("未擁有治療魔法!");
    	}
    	return false;
    }
    
    void levelUp() {
    	LV += 1;
		EXP -= 20;
		if(LV >= 12) {
			HP = originHP[10];
			MP = originMP[10];
			System.out.println("恭喜升級，你現在等級為"+LV);
			System.out.println("血量已回滿，血量:"+HP);
			System.out.println("魔力值已回滿，魔力值:"+MP);
		}
		else {
			HP = originHP[LV-1];
			MP = originMP[LV-1];
			Attack += 5;
			Speed += 2;
			Defense += 5;
			
			System.out.println("恭喜升級，你現在等級為"+LV);
			System.out.println("血量已回滿，血量:"+HP);
			System.out.println("魔力值已回滿，魔力值:"+MP);
			System.out.println("攻擊力上升5點，攻擊力:"+Attack);
			System.out.println("速度上升2點，速度:"+Speed);
			System.out.println("防禦力上升5點，防禦力:"+Defense);
			if(LV%2==0)
				acquireNewWeapon();
			if(LV%3==0) {
				acquireNewMount();
			}
		}
    }
    
    private void acquireNewWeapon() {
    	Weapon.add(new Weapon());
    	int l = Weapon.size()-1;
		System.out.println("獲得新武器: "+Weapon.get(l).Type+" 攻擊力:"+Weapon.get(l).Attack+" 適用怪物類型:"
				+ Weapon.get(l).Buff + " 移動速度:" + Weapon.get(l).Speed);
    }
    
    private void acquireNewMount() {
    	Mount.add(new Mount());
		int l = Mount.size()-1;
		System.out.println("獲得新坐騎: 坐騎加成:"+Mount.get(l).Buff+" 攻擊力:"+Mount.get(l).Attack+" 移動速度:"+Mount.get(l).Speed+" 防禦力:"+Mount.get(l).Defense);
    }
}