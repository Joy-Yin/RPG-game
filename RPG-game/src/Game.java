import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

	private static int spaceTime[] = { 1000, 1500, 2000, 2500, 3000 };
	private static String monsterTypeList[] = { "Land", "Water", "Sky" };
	private static Random rand = new Random();
	private static Scanner sc;

	public static void main(String[] args) {
		System.out.println("歡迎遊玩此遊戲...正在新建角色...");
		try {
			Thread.sleep(spaceTime[rand.nextInt(5)]);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Hunter hunter = new Hunter();
		System.out.println("這是你的初始武器");
		ArrayList<Weapon> w = hunter.getWeapon();
		System.out.println("類別:" + w.get(0).Type + " 攻擊力:" + w.get(0).Attack + " 適用怪物類型:"
				+ w.get(0).Buff + " 移動速度:" + w.get(0).Speed);
		System.out.println("\n趕快加入打怪的行列吧!");
		sc = new Scanner(System.in);
		while (hunter.getHP() > 0) {// in game
			System.out.println("搜索怪物中...");
			try {
				Thread.sleep(spaceTime[rand.nextInt(5)]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Monster monster = new Monster();
			monster = newMonster(monster); // new monster
			System.out.println("野生的" + monster.Name + "出現啦!!");
			battle:
			while (monster.HP > 0 && hunter.getHP() > 0) {
				System.out.println("請選擇要採取的行動:");
				System.out.println("1. 查看怪物狀態");
				System.out.println("2. 查看自身狀態");
				System.out.println("3. 裝備");
				System.out.println("4. 攻擊");
				System.out.println("5. 治療");
				System.out.println("6. 逃跑");
				System.out.println("7. 結束遊戲");
				String operation = sc.nextLine();
				switch (operation) {
				case "1": //查看怪物狀態
					showStatus(monster);
					break;
				case "2": //查看自身狀態
					showStatus(hunter);
					break;
				case "3": //裝備武器
					System.out.println("請選擇裝備武器或坐騎:");
					System.out.println("1. 武器\n2. 坐騎");
					String opt = sc.nextLine();
					if(opt.equals("1")) {
						hunter.equip(sc);
					}
					else if(opt.equals("2")){
						hunter.rideOn(sc);
					}
					break;
				case "4": //攻擊
					attack(hunter, monster);
					if(monster.HP > 0) {//怪物存活反擊
						fightBack(monster, hunter);
						hunter.autoRecoverMP();
						break;
					}
					else {//怪物死亡
						hunter.autoRecoverMP();
						gainEXP(hunter, monster);
						break battle;
					}
				case "5": //治療
					boolean eatElixir = hunter.heal(sc);
					if(eatElixir) {
						monster.sleep();
					}
					break;
				case "6": //逃跑
					if(flee(hunter, monster)) {
						break battle;
					}
					break;
				case "7": //結束遊戲
					System.exit(0);
				default:
					System.out.println("無效選項!請輸入有效的選項號碼!");
				}
			}
		}
		System.out.println("你已死亡，請重新修練~");
	}

	static private Monster newMonster(Monster monster) {
		String type = monsterTypeList[rand.nextInt(3)];
		switch (type) {
		case "Land":
			monster = new LandMonster();
			break;
		case "Water":
			monster = new WaterMonster();
			break;
		case "Sky":
			monster = new SkyMonster();
			break;
		}
		return monster;
	}
	
	static private void showStatus(Monster monster) {
		System.out.println("名稱:"+monster.Name);
		System.out.println("型態:"+monster.getType());
		System.out.println("描述:"+monster.Description);
		System.out.println("血量:"+monster.HP);
		System.out.println("速度:"+monster.Speed);
		System.out.println("攻擊力:"+monster.Attack);
	}
	
	static private void showStatus(Hunter hunter) {
		System.out.println("專長:"+hunter.getSkill());
		System.out.println("血量:"+hunter.getHP());
		System.out.println("魔力值:"+hunter.getMP());
		System.out.println("速度:"+hunter.getSpeed());
		System.out.println("攻擊力:"+hunter.getAttack());
		System.out.println("防禦力:"+hunter.getDefense());
		System.out.println("等級:"+hunter.getLV());
		System.out.println("當前經驗值:"+hunter.getEXP());
		System.out.println("當前裝備的武器:");
		Weapon w = hunter.getEquippedWeapon();
		if(w==null)
			System.out.println("無");
		else
			System.out.println(w.Type+" 攻擊力:"+w.Attack+" 適用怪物類型:"+ w.Buff + " 移動速度:" + w.Speed);
		
		System.out.println("魔法:");
		Magic magic = hunter.getMagic();
		for(int i = 0; i < magic.Type.length; i++) {
			if(magic.Type[i] != null) {
				System.out.println(magic.Type[i]);
			}
		}
		
		System.out.println("當前騎乘的坐騎:");
		Mount m = hunter.getRideOnMount();
		if(m==null)
			System.out.println("無");
		else
			System.out.println("坐騎加成:"+m.Buff+" 攻擊力:"+m.Attack+" 移動速度:"+m.Speed+" 防禦力:"+m.Defense);
		
		System.out.println("擁有仙丹數:"+hunter.getElixir());
	}
	
	static private boolean flee(Hunter hunter, Monster monster) {
		if(hunter.getSpeed() >= monster.Speed) {
			System.out.println("逃跑成功!");
			return true;
		}
		System.out.println("逃跑失敗，你的速度小於怪物的速度!");
		return false;
	}
	
	private static void attack(Hunter hunter, Monster monster) {
		System.out.println("請選擇攻擊方式:");
		System.out.println("1. 物理攻擊");
		System.out.println("2. 魔法攻擊(destroyer)");
		int opt = Integer.parseInt(sc.nextLine());
		if(opt == 1) {
			int check = checkSuitable(hunter, monster);
			int damage = hunter.getAttack();
			if(check == 0) {//不適當
				damage -= 10;
				System.out.println("效果薄弱!");
			}
			else if(check == 2) {//適當武器
				damage += 10;
				System.out.println("效果顯著!");
			}
			monster.HP -= damage;
			if(monster.HP < 0) {
				monster.HP = 0;
			}
			System.out.println(monster.Name+"受到"+damage+"點傷害，剩餘血量:"+monster.HP);
		}
		else if(opt == 2) {
			Magic m = hunter.getMagic();
			for(int i = 0; i < m.Type.length; i++) {
				if(m.Type[i] == null) {
					continue;
				}
				if(m.Type[i].equals("destroyer")) {
					int LVDamage = m.damage[hunter.getLV()-1];//該等級的傷害值
					if(LVDamage > hunter.getMP()) {//多少傷害就花多少MP
						System.out.println("你的MP不足!");
						return;
					}
					else {
						monster.HP -= LVDamage;
						if(monster.HP < 0) {
							monster.HP = 0;
						}
						hunter.minusMP(LVDamage);
						System.out.println(monster.Name+"受到"+LVDamage+"點傷害，剩餘血量:"+monster.HP+"，你剩餘魔力值:"+hunter.getMP());
						return;
					}
				}
			}
			System.out.println("未擁有破壞魔法!");
		}
		else {
			System.out.println("無效輸入!");
		}
	}
	
	private static void fightBack(Monster monster, Hunter hunter) {
		int damage = monster.Attack - hunter.getDefense();
		if(damage < 0) {
			damage = 0;
		}
		hunter.minusHP(damage);
		if(hunter.getHP() < 0) {
			hunter.setHP(0);
		}
		System.out.println("你受到"+damage+"點傷害，剩餘血量:"+hunter.getHP());
	}
	
	private static void gainEXP(Hunter hunter, Monster monster) {
		hunter.gainEXP(monster.EXP);
		System.out.println(monster.Name+"已被打倒!獲得"+monster.EXP+"點經驗值");
		while((hunter.getEXP() - 20) >= 0) {
			hunter.levelUp();
		}
	}
	
	private static int checkSuitable(Hunter hunter, Monster monster) {
		Weapon w = hunter.getEquippedWeapon();
		String monsterType = monster.getType();
		if(w == null) {//空手無加成
			return 1;
		}
		String skill = hunter.getSkill();
		if(skill.equals(w.Buff) && skill.equals(monsterType)) {//屬性完全正確
			return 2;
		}
		else if(skill.equals(monsterType) || w.Buff.equals(monsterType)) {//兩種相同無加成
			return 1;
		}
		return 0;
	}

}
