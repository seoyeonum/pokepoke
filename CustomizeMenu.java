// ● 사용자(소비자)가 메뉴 직접 선택 시 진행되는 기능(메소드)를 담은 클래스

//    0. CustomizeMenu(Hashtable<String, ArrayList<Integer>> h, int userKcal)
//       - 매개변수로 Hashtable과 int 자료형을 받는 CustomizeMenu 클래스 생성자
//		 - CustomizeMenu 클래스 인스턴스 생성과 동시에 customizeBase1()이 호출되며,
//         이후 메소드 끝부분에서 [이전 단계], [메뉴 재입력], [다음 단계] 의 3가지 선택지에 따라
//         customizeBase 각 단계 간의 호출 혹은 프로그램 종료(Base4-[다음단계])가 이루어진다.

//	  1. printHead(String Head, String numgae)
//		 - 각 단계에서 선택하는 메뉴 종류(ex.소스)와 개수(ex.1개) 매개변수로,
//		   각 단계 메뉴 상단 제목행 출력을 위한 메소드

//	  2. printTail(int addKcal, int addWon)
//		 - 각 단계에서 선택한 메뉴의 누적 칼로리와 누적 금액을
//		   메뉴 선택 이후 하단에 출력하는 메소드
//       - 출력 이후 다음 기능 수행을 위한 안내(번호)도 함께 출력

//	  3. printMenu(ArrayList<String> base)
//		 - ArrayList<String> base 를 매개변수로,
//		   만약 메뉴가 품절 상태가 아니라면 메뉴를 사용자에게 출력하는 메소드

//	  4. beforeStartAgain(int i)
//		 - 각 단계의 값이 가지는 인덱스 값 i 를 매개변수로,
//		   다른 단계에서 해당 단계에 재진입 시,
//		   기존에 합산된 칼로리 및 금액을 차감하는 메소드

//	  5. beforeNextStep(int i, String name)
//		 - 각 단계에서 최종 선택한 메뉴가 담기는 Vector 자료구조인 pickedMenu에서
//         각 단계의 값이 가지는 인덱스 값 i 와 최종 선택 메뉴명 name 을 매개변수로,
//         각 단계에서 다음 단계로 진행 전 이루어져야 하는 과정을 수행하는 메소드

//    6. customizeBase1()
//		 - 메뉴 직접 조합 ①: Base1 주문 메소드
//		 - 3가지 베이스 메뉴 중 선택 가능, 선택지 이외의 값 입력 시 재 입력 안내
//    7. customizeBase2()
//		 - 메뉴 직접 조합 ②: Base2 주문 메소드
//		 - 6가지 소스 메뉴 중 선택 가능, 선택지 이외의 값 입력 시 재 입력 안내
//    8. customizeBase3()
//		 - 메뉴 직접 조합 ③: Base3 주문 메소드
//		 - 20가지 토핑 메뉴 중 선택 가능, 중복 선택 불가, 복수 선택 가능, 선택지 이외의 값 입력 시 재 입력 안내
//    9. customizeBase4()
//		 - 메뉴 직접 조합 ④: Base4 주문 메소드
//		 - 4가지 사이드 메뉴 중 선택 가능, 선택없음 존재, 선택지 이외의 값 입력 시 재 입력 안내

import java.util.Scanner;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Vector;

class CustomizeMenu extends Tool								//-- Tool 클래스를 상속
{
	private boolean allPick = true;
	private int userKcal;										//-- 한 끼 권장 섭취 칼로리
	
	private Vector<String> pickedMenu = new Vector<String>();	//-- 최종 선택한 메뉴명 모음(Vector)
	private int kcalSum;										//-- 최종 선택한 메뉴 칼로리 합(총 칼로리)
	private int totSum;											//-- 최종 선택한 메뉴 가격 합(총 금액)
	
	private static String picked1;										//-- base1에서 선택한 메뉴의 이름
	private static String picked2;										//-- base2에서 선택한 메뉴의 이름
	private static Vector<String> picked3 = new Vector<String>();		//-- base3에서 선택한 메뉴의 이름(들)
	private static String picked4;										//-- base4에서 선택한 메뉴의 이름
																//** pickedKcal과 pickedWon은 각 단계에서 이전/다음 단계로 오갈 때 필요한 정보를 보관
	private int[] pickedKcal = new int[4];						//-- base1, base2, base3, base4 의 각 단계 선택 메뉴(들)의 칼로리를 저장
	private int[] pickedWon = new int[4];						//-- base1, base2, base3, base4 의 각 단계 선택 메뉴(들)의 가격을 저장
	
	private int cntB3 = 0;		//-- base3 관련 활용 변수
	private String names;		//-- base3 관련 활용 변수
	
	Scanner sc = new Scanner(System.in);						//-- Scanner 클래스 인스턴스 생성
	CorrectMenuValue cmv = new CorrectMenuValue();				//-- CorrectMenuValue 클래스 인스턴스 생성
	
	// ─────────────(getter)────────────────────────
	
	public Vector<String> getPickedMenu()
	{
		pickedMenu.add(picked1);
		pickedMenu.add(picked2);
		for (String p3 : picked3)
		{
			pickedMenu.add(p3);
			
		}
		
		pickedMenu.add(picked4);
		
		return this.pickedMenu;
	}
	
	public int getKcalSum()
	{
		return this.kcalSum;
	}
	
	public int getTotSum()
	{
		return this.totSum;
	}
	
	public boolean getPickResult()
	{
		return this.allPick;
	}
	
	// ─────────────(생성자)────────────────────────
	
	// int userKcal 을 매개변수로 받는 CustomizeMenu 클래스 생성자
	CustomizeMenu(int userKcal) throws Exception
	{
		this.userKcal = userKcal;	//-- 매개변수로 받은 int userKcal로 전역변수 userKcal 초기화
		
		super.setFile();			//-- 부모 클래스의 전역변수 중 품목명,칼로리,가격 정보(Hashtable)을 set 하는 메소드 실행
		super.setPossibleMenu();	//-- 부모 클래스의 전역변수 중 품절이 아닌 메뉴 정보(Vector)을 set 하는 메소드 실행
		super.setBases();			//-- 부모 클래스의 전역변수 중 단계별 메뉴 정보(ArrayList)들을 set 하는 메소드 실행
		
		customizeBase1();			//-- customizeBase1()를 시작으로, 생성 시 메소드 연쇄 진행
	}
	
	// ─────────────(메소드)────────────────────────
	
	// 메뉴 출력 시 상단 제목행 출력하는 메소드
	private void printHead(String Head, String numgae)
	{
		System.out.println(); // 개행
		System.out.println("\t================================================="); 
		System.out.println("\t " + Head + " 선택 (" + numgae + "선택)");
		System.out.println("\t================================================="); 
		System.out.println("\t메뉴명\t\t   칼로리(kcal)  \t가격(원)");
		System.out.println("\t================================================="); 
	}//end printHead()
	
	// 메뉴 선택 시 하단에 선택 정보를 출력하는 메소드
	private void printTail(int addKcal, int addWon)
	{
		System.out.println("\t* 현재 단계까지 담긴 칼로리 : " + (kcalSum + addKcal) + " / " + userKcal + " kcal");
		System.out.println("\t* 현재 단계까지 누적 금액   : ￦ " + (totSum + addWon));
			
		System.out.println("\n\t  [1] 이전 단계  [2] 메뉴 재입력  [3] 다음 단계");
		System.out.print("\n\t번호를 입력해주세요 : ");
	}//end printTail()
	
	// 다른 단계에서 해당 단계에 재진입 시, 기존에 합산된 칼로리 및 금액을 차감하는 메소드
	private void beforeStartAgain(int i)
	{
		if (pickedKcal[i] != 0)			//-- 기존에 해당 단계에서 선택된 값이 있다면,
		{
			kcalSum -= pickedKcal[i];	//-- 해당 칼로리 값만큼 칼로리 합 차감
			totSum -= pickedWon[i];		//-- 해당 금액 값만큼 가격 합 차감	
		}
	}//end beforeStartAgain()
	
	// 각 단계에서 다음 단계로 진행 전 이루어져야 칼로리 및 가격 저장하는 과정을 수행하는 메소드
	private void beforeNextStep(String picked, int i, String name)
	{
		picked = name;								//-- 해당 주문 단계에서 선택한 메뉴(들)의 이름 담기
		kcalSum += foodInfo.get(name).get(0);		//-- 해당 주문 단계에서 선택한 메뉴(들)의 칼로리 → 누적합
		totSum += foodInfo.get(name).get(1);		//-- 해당 주문 단계에서 선택한 메뉴(들)의 가격   → 누적합
					
		pickedKcal[i] = foodInfo.get(name).get(0);	//-- 해당 주문 단계에서 선택한 메뉴(들)의 칼로리 → pickedKcal[i]
		pickedWon[i] = foodInfo.get(name).get(1);	//-- 해당 주문 단계에서 선택한 메뉴(들)의 가격 → pickedWon[i]
	}//end beforeNextStep()
	
	// 품절 상태가 아닌 메뉴를 출력하는 메소드
	private void printMenu(ArrayList<String> base, int num)
	{
		for (int i=0; i<base.size(); i++)
		{
			if (possibleMenu.contains(base.get(i)))	//-- ex. base1.get(i)이 possibleMenu 에 있다면,
			{
				// 사용자에게 메뉴로 출력
				if (num == 1)
				{
					System.out.printf("\t[%d] %-3s\t     %3dkcal\t\t￦%5d\n", (i+1), base.get(i), foodInfo.get(base.get(i)).get(0), foodInfo.get(base.get(i)).get(1));
				}
				else if (num == 2)
				{
					System.out.printf("\t[%d] %-8s\t%3dkcal\t\t￦%5d\n", (i+1), base.get(i), foodInfo.get(base.get(i)).get(0), foodInfo.get(base.get(i)).get(1));
				}
				else if (num == 3)
				{
					System.out.printf("\t[%d] %-5s\t\t%3dkcal\t\t￦%5d\n", (i+1), base.get(i), foodInfo.get(base.get(i)).get(0), foodInfo.get(base.get(i)).get(1));
				}
				else if (num == 4)
				{
					System.out.printf("\t[%d] %-8s\t%3dkcal\t\t￦%5d\n", (i+1), base.get(i), foodInfo.get(base.get(i)).get(0), foodInfo.get(base.get(i)).get(1));
				}
			}
		}
	}//end printMenu()
	
	// ─────────────(아래는 주문 단계별 메소드)────────────────────────
	
	// 메뉴 직접 조합 ①: Base1 주문 메소드
	public void customizeBase1()
	{
		System.out.println();
		System.out.println("\t * 순차적으로 메뉴를 선택해주세요.");
		System.out.println("\t * 기본 야채는 포함되어 있습니다.");
		beforeStartAgain(0);
		printHead("\t      베이스", "1개");
		printMenu(base1,1);
		System.out.println("\t================================================="); 
		int button = 0;
		do
		{
			System.out.print("\t메뉴 번호 입력(1개) : ");
			String str = sc.nextLine();
			int b1 = cmv.correctSelect(1, 3, str) -1;	//-- b1에 메뉴의 인덱스를 담기 위해 『-1』
			System.out.println("\t=================================================");
			System.out.println("\n\t* 현재 단계까지 담긴 식재료 :");
			System.out.println("\t  베이스 ▶ [" + base1.get(b1) + "]");
			picked1 = base1.get(b1);
			System.out.println();
			System.out.println("\t=================================================");
			printTail(foodInfo.get(base1.get(b1)).get(0), foodInfo.get(base1.get(b1)).get(1));
			
			str = sc.nextLine();
			button = cmv.correctSelect(1, 3, str);
			System.out.println("\t=================================================");
			
			
			switch (button)
			{
				case 1:
					picked1 = null;						//-- picked1 값 → 삭제
					pickedKcal[0] = 0;
					pickedWon[0] = 0;
					allPick = false;
					return;								//	 return → customizeBase1() ***************메소드 종료****************
				case 2:
					continue;							//-- 계속 진행. 단, 아래 while 조건 따라 base1 입력 다시 안내
				case 3:
					beforeNextStep(picked1, 0, base1.get(b1));
					customizeBase2(); break;			//-- 다음 단계 (customizeBase2() 메소드) 호출
					
			}
		}
		while (button == 2);	//-- 정보 재입력 선택 시, 메뉴 번호 입력 다시 안내
	}//end customizeBase1()
	
	// 메뉴 직접 조합 ②: Base2 주문 메소드
	public void customizeBase2()
	{
		System.out.println();
		beforeStartAgain(1);
		printHead("\t      소스", "1개");
		printMenu(base2,2);
		System.out.println("\t================================================="); 	
		int button = 0;
		do
		{
			System.out.print("\t메뉴 번호 입력(1개) : ");	//-- b2에 메뉴의 인덱스를 담기 위해 『-1』
			String str = sc.nextLine();
			int b2 = cmv.correctSelect(1, 6, str) -1;
			System.out.println("\t================================================="); 
			System.out.println("\n\t* 현재 단계까지 담긴 메뉴");
			System.out.println("\t  베이스 ▶ [" + picked1 + "]");
			System.out.println("\t    소스 ▶ [" + base2.get(b2) + "]");
			picked2 = base2.get(b2);
			System.out.println();
			System.out.println("\t=================================================");
			printTail(foodInfo.get(base2.get(b2)).get(0), foodInfo.get(base2.get(b2)).get(1));
			str = sc.nextLine();
			button = cmv.correctSelect(1, 3, str);
			System.out.println("\t=================================================");
			
			switch (button)
			{
				case 1:
					picked2 = null;						//-- picked2 값 → 삭제
					pickedKcal[1] = 0;
					pickedWon[1] = 0;
					customizeBase1(); break;			//	이전 단계 (customizeBase1() 메소드) 호출
				case 2:
					continue;							//-- 계속 진행. 단, 아래 while 조건 따라 base2 입력 다시 안내
				case 3:
					beforeNextStep(picked2, 1, base2.get(b2));
					customizeBase3(); break;			//-- 다음 단계 (customizeBase3() 메소드) 호출
			}
		}
		while (button == 2);
	}//end customizeBase2()

	// 메뉴 직접 조합 ③: Base3 주문 메소드
	public void customizeBase3()
	{
		System.out.println();
		beforeStartAgain(2);
		printHead("\t      토핑", "복수");
		printMenu(base3,3);
		
		int button = 0;
		int kcals;			//-- base3 관련 활용 변수
		int wons;			//-- base3 관련 활용 변수
		do
		{
			Vector<Integer> b3 = new Vector<Integer>();			 //-- 사용자 입력한 숫자가 인덱스 값으로 담길 벡터 자료구조
			int cntB3=0;										 //-- 사용자가 입력한 메뉴의 개수 카운트
			System.out.println("\t================================================="); 
			System.out.print("\t메뉴 번호 입력(1개 필수) : ");	
			String str = sc.nextLine();
			do
			{
				try
				{
					int menuNumber = Integer.parseInt(str);
					
					if (menuNumber >= 1 && menuNumber <= 20)	//-- 유효범위(1 ~ 20) 숫자 입력 시,
					{
						
						if (b3.contains(menuNumber-1))
							System.out.println("\n        이미 선택한 메뉴입니다. 각 토핑은 1개씩만 선택 가능합니다.");
						else
						{
							b3.add(menuNumber - 1);				//-- b3[i]에 각 메뉴의 인덱스를 담기 위해 『-1』
							cntB3++;
						}
					}
					else										//-- 유효범위를 벗어나는 숫자 입력 시,
						System.out.println("\n        메뉴 번호는 1 이상 20 이하로 입력해주세요.");

				}
				catch (NumberFormatException e)
				{
					if (str.equals("N"))						//-- NumberFormatException 인데 "N" 일 경우,
					{
						if (b3.isEmpty())
							System.out.println("\n        토핑은 최소 1개 이상 추가되어야 합니다.");
						else
							break;
					}
					else										//-- NumberFormatException 인데 그 외 문자일 경우,
						System.out.println("\n        올바른 값을 입력해주세요");
				}
				
				System.out.print("\n\t메뉴 번호 입력(1개) [※ 입력 종료 시 : N] : ");
				str = sc.nextLine().toUpperCase();
			}
			while (true);
			System.out.println("\t=================================================");
			// 여기까지 왔으면 제대로 잘 입력된 숫자값들!
			names = "" ;		//-- 선택한 메뉴들의 이름을 누적시킬(모아놓을) 변수
			kcals = 0;			//-- 선택한 메뉴들의 열량을 누적시킬 변수
			wons = 0;			//-- 선택한 메뉴들의 가격을 누적시킬 변수
			
			for (int i = 0; i<cntB3; i++)
			{
				names += base3.get(b3.get(i));
				if (i<cntB3-1)
				{
					names += ", ";	//-- 선택한 메뉴들의 이름 누적 시 사이에 『, 』 추가
				}
				
				picked3.add(base3.get(b3.get(i)));							//-- 선택한 메뉴 이름 Vector 에 적재
				
				kcals += foodInfo.get(base3.get(b3.get(i))).get(0);			//-- 선택한 메뉴 칼로리 누적
				wons += foodInfo.get(base3.get(b3.get(i))).get(1);			//-- 선택한 메뉴 가격 누적
			}
			
			System.out.println("\n\t* 현재 단계까지 담긴 메뉴");
			System.out.println("\t  베이스 ▶ [" + picked1 + "]");
			System.out.println("\t    소스 ▶ [" + picked2 + "]");
			System.out.println("\t    토핑 ▶ [" + names + "]");
			System.out.println("\t=================================================");
			printTail(kcals, wons);
			String str2 = sc.nextLine();
			button = cmv.correctSelect(1, 3, str2);
			System.out.println("\t=================================================");
			switch (button)
			{
				case 1:
					for (int i=picked3.size()-1; i>=0; i--)
					{
						picked3.remove(i);			//-- picked3에 더해진 base3의 이름 모두 삭제
					}
					pickedKcal[2] = 0;
					pickedWon[2] = 0;
					customizeBase2(); break;		//-- 이전 단계 (customizeBase2() 메소드) 호출
					
				case 2:
					for (int i=picked3.size()-1; i>=0; i--)
					{
						picked3.remove(i);			//-- picked3에 더해진 base3의 이름 모두 삭제
					}
					continue;						//-- 계속 진행. 단, 아래 while 조건 따라 메뉴 번호 입력 다시 안내
				
				case 3:
					kcalSum += kcals;				//-- 선택한 base2의 칼로리 → 누적합
					totSum += wons;					//-- 선택한 base2의 가격   → 누적합
					
					pickedKcal[2] = kcals;
					pickedWon[2] = wons;
				
					customizeBase4(); break;		//-- 다음 단계 (customizeBase4() 메소드) 호출
			}
		}
		while (button == 2);	//-- 정보 재입력 선택 시, 메뉴 번호 입력 다시 안내
	}//end customizeBase3()
	
	// 메뉴 직접 조합 ④: Base4 주문 메소드
	public void customizeBase4()
	{
		System.out.println();
		beforeStartAgain(3);
		printHead("\t     사이드 메뉴", "1개");
		System.out.println("\t[0] (선택 안함)");
		printMenu(base4,4);
		System.out.println("\t=================================================");
		int button = 0;
		do
		{
			System.out.print("\t메뉴 번호 입력(1개) : ");	//-- b4에 메뉴의 인덱스를 담기 위해 『-1』
			String str = sc.nextLine();
			int b4 = cmv.correctSelect(0, 4, str) -1;
			System.out.println("\t=================================================");
			
			System.out.println("\n\t* 현재 단계까지 담긴 메뉴");
			System.out.println("\t  베이스 ▶ [" + picked1 + "]");
			System.out.println("\t    소스 ▶ [" + picked2 + "]");
			System.out.println("\t    토핑 ▶ [" + names + "]");
			if (b4==-1)		//-- 사이드 선택 안함 선택 시,
			{
				System.out.println("\t  사이드 ▶ [(선택 안함)]");
				picked4 = "(선택 안함)";
				System.out.println();
				System.out.println("\t=================================================");
				printTail(0, 0);
			}
			else			//-- 사이드 선택한 경우,
			{
				System.out.println("\t  사이드 ▶ [" + base4.get(b4) + "]");
				picked4 = base4.get(b4);
				System.out.println();
				System.out.println("\t=================================================");
				printTail(foodInfo.get(base4.get(b4)).get(0), foodInfo.get(base4.get(b4)).get(1));
			}
			
			str = sc.nextLine();																//-- 1,2,3 중 택해라~~
			button = cmv.correctSelect(1, 3, str);
			
			
			switch (button)
			{
				case 1:
					picked4 = null;						//-- picked4 값 → 삭제
					pickedKcal[3] = 0;
					pickedWon[3] = 0;
					customizeBase3(); break;			//-- 이전 단계 (customizeBase3() 메소드) 호출
				case 2:
					continue;
				case 3:
					if (b4==-1)	//-- 사이드 선택 안함 선택 시,
					{
						//picked = "선택안함";
						//kcalSum += 0;		//-- 해당 주문 단계에서 선택한 메뉴(들)의 칼로리 → 누적합
						//totSum += 0;		//-- 해당 주문 단계에서 선택한 메뉴(들)의 가격   → 누적합
						pickedKcal[4] = 0;	//-- 해당 주문 단계에서 선택한 메뉴(들)의 칼로리 → pickedKcal[i]
						pickedWon[4] = 0;	//-- 해당 주문 단계에서 선택한 메뉴(들)의 가격 → pickedWon[i]
					}
					else		//-- 사이드 선택한 경우,
						beforeNextStep(picked4, 3, base4.get(b4));
					System.out.println("\t=================================================");
					System.out.println();
					allPick = true;
					return;								//--해당 메소드 종료
			}
		}
		while (button == 2);
	}//end customizeBase4()
	
}//end CustomizeMenu
	