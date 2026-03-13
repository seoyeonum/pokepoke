import java.util.Scanner;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ListIterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

import java.util.Random;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Hashtable;


public class Randomenu extends Tool
{
	//올바른 범위의 값을 받기 위한 클래스 인스턴스 생성
	CorrectMenuValue cmv = new CorrectMenuValue();
	
	// 입력해서 받은, 유저의 한 끼 칼로리
	static int user_kcal;
	
	//사용자 지정 생성자
	// 유저 칼로리를 받아서 바로 세팅하게끔 코딩.
	Randomenu(int user_kcal) throws Exception
	{
		// 음식 정보 담긴 파일 받아오기 
		this.user_kcal = user_kcal;
		super.setPossibleMenu();
		super.setBases();
		super.setFile();
	}
	
	//메뉴 호출용 스태틱 변수
	static final int SIDEYES_ALLYES = 1;
	static final int SIDENO_ALLYES = 2;
	static final int SIDEYES_ALLNO = 3;
	static final int SIDENO_ALLNO = 4;
	
	// 스캐너 인스턴스 생성
	Scanner sc = new Scanner(System.in);
	
	// 사이드와 알레르기 여부를 보여주는 경우의 수를 담는 변수. 
	static int choose;		// 1 2 3 4 넷 중 하나.
	
	int KcalSum = 0;			//뽑는 동안 더해지는 칼로리
	int totSum = 0;			//뽑는 동안 더해지는 돈(가격)
	int rdbase;				//랜덤으로 뽑힐 베이스
	int rdsauce;			//랜덤으로 뽑힐 소스
	int rdtop;				//랜덤으로 뽑힐 토핑
	int rdside;				//랜덤으로 뽑힐 사이드
	
	
	// 미리 뽑아놨던 난수를 이용해 재료 리스트에서 재료 문자열을 뽑았다. 그걸 담을 변수.
	String bsresult = "이게 보인다면 문의하세요.";	
	String scresult = "이게 보인다면 문의하세요.";	
	String tpresult = "이게 보인다면 문의하세요.";	
	String sdresult = "이게 보인다면 문의하세요.";	
	// 이 문자열로 foodinfo랑 foodstock에 접근할 것이다. 	
	
	String yesno;		// 대답 담을 문자열 변수
	boolean sideyes;	// 사이드 먹을 건지 안 먹을 건지 결정하는 변수
	
	
	// 뽑힌 토핑을 담아 출력할 리스트를 만든다. 다른 애들은 하나면 되지만 토핑은 두 개 이상일 수도 있으니까.
	ArrayList<String> topprint = new ArrayList<String>();
	
	// 랜덤 인스턴스 생성
	Random rd = new Random();
	
	// 알레르기 번호를 담는 배열.
	String[] allergy;
	int[] allergy2; 
	
	// 다시 뽑을지 정하는 변수 // Y 혹은 그 이외의 문자열 형태.
	String repick2;
	
	// 선택된 메뉴를 넣을 벡터
	Vector<String> pickedMenu = new Vector<String>();
	
	// 질문해서 4가지 선택 중 하나를 넘기는 메소드
	public int Question() throws Exception
	{
		//제대로 입력했는지 확인하는 클래스 생성
		CorrectMenuValue cmv = new CorrectMenuValue();
		
		// 사용자 인적사항 입력 클래스 생성
		//KcalCalc KcalC = new KcalCalc();
		//KcalC.setKcal();
		//user_kcal = KcalC.getKcal();
		//System.out.printf("당신의 한 끼 권장 칼로리는 %dkcal입니다.\n", user_kcal);
		
		// 사이드 추가 안내문
		System.out.println();
		System.out.println("\t     ※  조합되는 메뉴들은 중복되지 않습니다 ※");
		System.out.println();
		System.out.println("\t    조합에 사이드를 추가하시겠습니까? (Y / N) ");
		System.out.print("\t  [Y 외의 다른 문자는 모두 N으로 간주합니다] : ");
		yesno = sc.next();
		yesno = yesno.toUpperCase();
		sideyes = yesno.equals("Y") ? true : false; //사이드 먹는지 안 먹는지 확인하는 변수
		
		// 알레르기 안내문
		System.out.println();
		System.out.println("\t    알레르기가 있으신가요? (Y / N) ");
		System.out.print("\t  [Y 외의 다른 문자는 모두 N으로 간주합니다] : ");
		yesno = sc.next();
		yesno = yesno.toUpperCase();
		System.out.println();
		System.out.println("\t=================================================");
		String allyn = yesno;
		System.out.println();
		
		String[] all = new String[base3.size()];
		
		for (int i = 0 ; i < all.length ; i++)
		{
			all[i] = base3.get(i);
		}
		
		String[][] all2 = new String[8][3];
		
		//토핑 메뉴를 순서 똑같이 그대로 가져왔다. 
		if (allyn.equals("Y"))
		{
			int n = 0;
			System.out.println("\t\t\t[알레르기 목록]");
			System.out.println();
			// 메뉴에서 알레르기일 법한 재료들 전체 출력
			for (int i = 0; i < all2.length; i++)
			{
				for (int j = 0; j < all2[i].length && n < all.length; j++)
				{
					all2[i][j] = all[n++];
					System.out.printf("  \t%3d.%5s", n, all2[i][j]);
				}
				System.out.println();
			}
			System.out.println("\t=================================================");
			System.out.println();
			System.out.println("\t해당 목록에서 알레르기가 두 개 이상이십니까? (Y / N)");
			System.out.print("\t  [Y 외의 다른 문자는 모두 N으로 간주합니다] : ");
			String two = sc.next();
			
			Integer a = -1;
			
			String alg = "입력 오류.";
			two = two.toUpperCase();
			if (two.equals("Y"))
			{
				System.out.println();
				System.out.println("\t해당 목록에서 알레르기가 있는 번호를 입력해 주세요");
				System.out.print("        [항목이 여러 개면 쉼표를 붙여 주세요. (예: 1,2,3)] :"); 
				alg = sc.next();
				
			}
			else if (!two.equals("Y"))
			{
				System.out.println();
				System.out.print("\t 목록 중 알레르기가 있는 번호를 입력해 주세요 : ");
				alg = sc.next();
			}
			
			
			
			//알레르기 항목을 받아와서 배열로 생성
			allergy = alg.split(",");	//예를 들어 "2" "5" "10"
			
			allergy2 = new int[allergy.length];	//알레르기 배열 길이와 똑같은 길이의 알레르기2 배열 생성.
			
			//int 타입인 알레르기2 배열 생성, 알레르기배열의 String요소를 int로 형변환해 담았다.
			for (int i = 0; i < allergy.length; i++)
			{
				cmv.stringToint(allergy[i]);
				if (!cmv.stringToint(allergy[i]))
				{
					System.out.println();
					System.out.println("\t\t숫자 이외의 문자를 입력하지 마십시오.");
					System.out.println();
					System.out.println("\t\t      [처음으로 돌아갑니다]");
					System.out.println();
					System.out.println("\t=================================================");
					System.out.println();
					Question();
				}
				
				if (Integer.parseInt(allergy[i]) < 1 || Integer.parseInt(allergy[i]) > 20)
				{
				   System.out.println();
				   System.out.println("\t=================================================");
				   System.out.println();
				   System.out.println("\t      ※  선택 범위를 초과해 선택하셨습니다 ※");
				   System.out.println();
				   System.out.println("\t\t       다시 선택해주세요");
				   System.out.println();
				   System.out.println("\t=================================================");
				   Question();
				}
				
				allergy2[i] = Integer.parseInt(allergy[i]);	//2 5 10으로 담음
			}
			
			if (allergy2.length > 15)
			{
				System.out.println("\t자판기 메뉴의 다양성이 부족하여 고객님에게 제품을 제공하기 어렵습니다.");
				System.out.println();
				System.out.println("\t\t다시 선택해주세요");
				Question();
			}
			
	
		
		} //알레르기 if문 종료
		
		
		if (sideyes && allyn.equals("Y"))		//사이드 먹고 알레르기 있음
		{
			choose = SIDEYES_ALLYES;
		}
		else if (!sideyes && allyn.equals("Y"))	//사이드 안 먹고 알레르기 있음
		{
			choose = SIDENO_ALLYES;
		}
		else if (sideyes && !allyn.equals("Y"))	//사이드 먹고 알레르기 없음
		{
			choose = SIDEYES_ALLNO;
		}
		else if (!sideyes && !allyn.equals("Y"))	//사이드 안 먹고 알레르기 없음
		{
			choose = SIDENO_ALLNO;
		}
		
		
		return choose;
		
		
	}
	
	public void Callchoose(int choose) throws Exception
	{
		switch (choose)
		{
		case SIDEYES_ALLYES: SdY_AlY(); break;
		case SIDENO_ALLYES: SdN_AlY(); break;
		case SIDEYES_ALLNO: SdY_AlN(); break;
		case SIDENO_ALLNO: SdN_AlN(); break;
		}
		
	}
	
	
	// 사이드 먹고 알레르기 있음
	protected void SdY_AlY() throws Exception
	{	
		do
		{	
			// foodinfo에 접근하자. 
			// Parent클래스에 있던 메소드 소환
			//Hashtable<String, ArrayList<Integer>> foodinfo = super.getFile();
			
			//지정된 칼로리가 될 때까지 조합 중 하나를 뽑아서 칼로리와 돈을 각각 더한다. 누적합.
			//난수를 생성한다. 베이스 3 중 하나 / 소스 6 중 하나 / 토핑 20 중 하나 / 사이드 4 중 하나
			// 베이스와 소스는 알레르기 범위에 넣지 않았으니 제외하고 그대로 뽑는다.
			// 재고 파일에 없으면 다시 뽑으세요
			do
			{
				rdbase = rd.nextInt(3);
				bsresult = base1.get(rdbase);
			}
			while (!possibleMenu.contains(bsresult));
			
			do
			{
				rdsauce = rd.nextInt(6);
				scresult = base2.get(rdsauce);
			}
			while (!possibleMenu.contains(scresult));
			
			
			//만약 배열에 3과 16이 전부 있음 > 양송이 제외
			// 21 있음 > 단호박 제외
			// 4 || 11 있음 > 토마토 제외
			// 를 하고 싶었으나 배움의 한계로 호박 제외.
			
			jump2:
			for (int i = 0; i < allergy2.length; i++)
			{
				rdside = rd.nextInt(4);
				sdresult = base4.get(rdside);
				do
				{
					if (allergy2[i] == 16 && rdside == 0)
					{
						continue jump2;
					}
					if((allergy2[i] == 4 || allergy2[i] == 11) && rdside == 3)
					{
						continue jump2;
					}
				}
				while (!possibleMenu.contains(sdresult));
				
			}
			
			// 관리자에게서 받아온 파일, Vector<String> possibleMenu는 재고가 있는 문자열의 모음집이다. 
			
			// 알레르기가 들어간 숫자를 뽑았다면 반복. 알레르기2의 숫자들 = 배열 all의 인덱스값+1이니 주의.
			rdtop = rd.nextInt(20);	//0~19.
			jump:
			for (int i = 0; i < allergy2.length; i++)
			{
				if (rdtop+1 == allergy2[i])
				{ 
					rdtop = rd.nextInt(20);
					continue jump;
				}
				
				// 알레르기 외의 숫자를 베이스에 넣어서 문자열 뽑기
				tpresult = base3.get(rdtop);
				if (!possibleMenu.contains(bsresult))	//문자열이 재고 파일 안에 없으면 품절이므로
				{
					continue jump;					//다시.
				}
			}
			
			//rd들을 get()에 넣고 그에 따른 값을 가져온다. 예) ArrayList base의 rdbase 번째 값. 형태는 문자열.
			
			
			topprint.add(tpresult);		//토핑은 토핑을 프린트할 리스트에 추가한다. 
			
			//베이스 칼로리와 가격을 sumkcal과 summoney에 넣는다. bsresult 키를 넣어서 foodinfo의 밸류를 구했다.
			//→ 그 밸류는 Integer가 두 개인 ArrayList
			KcalSum += foodInfo.get(bsresult).get(0) + foodInfo.get(scresult).get(0) + foodInfo.get(tpresult).get(0) + foodInfo.get(sdresult).get(0); 
					//-----------------------		
				//키 bsresult를 넣어서 구한 foodinfo의 밸류.의 첫번째값(칼로리)
			
			// 같은 형태.
			totSum += foodInfo.get(bsresult).get(1) + foodInfo.get(scresult).get(1) + foodInfo.get(tpresult).get(1) + foodInfo.get(sdresult).get(1);
			
			//만약 다 뽑았는데 총합 칼로리보다 모자란다면, 토핑만 추가로 뽑는 과정을 반복한다. 
			jump3:
			
			while (KcalSum < user_kcal-50)		// 칼로리 < 유저 칼로리 -10이면 반복
			{
				rdtop = rd.nextInt(20);
				for (int i = 0; i < allergy2.length; i++)	//알레르기와 중복이면 다시
				{ 	
					if (rdtop+1 == allergy2[i])
					{
						continue jump3;
					}
				
				} 
				tpresult = base3.get(rdtop);
				
				// 중복 생기면 다시 뽑아야 함
				if (topprint.contains(tpresult) || !possibleMenu.contains(tpresult))
				{
					continue jump3;
				}
				
				
				KcalSum += foodInfo.get(tpresult).get(0);
				
				//user_kcal에 조금만 더해도 값을 넘어버릴 때..다시 뽑게끔 조치해야 한다. 
				if (KcalSum > user_kcal+50)
				{
					KcalSum -= foodInfo.get(tpresult).get(0);
					continue jump3;
				}
				
				topprint.add(tpresult);
				totSum += foodInfo.get(tpresult).get(1);
			}
			//토핑 추가 뽑기 구문 종료
			
			// 답 프린트 메소드 소환, 답변을 담는다.
			//"이대로 결제하시겠습니까? N을 택하신다면 처음부터 다시 뽑습니다. (Y / N) : "
			repick2 = Resultprint();
			// 초기화
			if (!repick2.equals("Y"))
			{
				KcalSum = 0;
				totSum = 0;
				topprint.removeAll(topprint);
			}
			
		}
		while (!repick2.equals("Y"));	//Y가 아니면 반복.
		
		
		
	}
	
	// 사이드 안 먹고 알레르기 있음
	protected void SdN_AlY() throws Exception
	{
		do
		{
			
			
			do
			{
				rdbase = rd.nextInt(3);
				bsresult = base1.get(rdbase);
			}
			while (!possibleMenu.contains(bsresult));
			
			do
			{
				rdsauce = rd.nextInt(6);
				scresult = base2.get(rdsauce);
			}
			while (!possibleMenu.contains(scresult));
			
			rdtop = rd.nextInt(20);	//0~19.
			jump:
			for (int i = 0; i < allergy2.length; i++)
			{
				if (rdtop+1 == allergy2[i])
				{ 
					rdtop = rd.nextInt(20);
					continue jump;
				}
			}
			
			tpresult = base3.get(rdtop);
			
			topprint.add(tpresult);
			
			KcalSum += foodInfo.get(bsresult).get(0) + foodInfo.get(scresult).get(0) + foodInfo.get(tpresult).get(0); 
			
			totSum += foodInfo.get(bsresult).get(1) + foodInfo.get(scresult).get(1) + foodInfo.get(tpresult).get(1);
			
			jump:
			while (KcalSum < user_kcal-50)		// 칼로리 < 유저 칼로리 -10이면 반복
			{
				rdtop = rd.nextInt(20);
				for (int i = 0; i < allergy2.length; i++)	//알레르기와 중복이면 다시
				{ 	
					if (rdtop+1 == allergy2[i])
					{
						continue jump;
					}
				
				} 
				tpresult = base3.get(rdtop);
				
				// 중복 생기면 다시 뽑아야 함
				if (topprint.contains(tpresult) || !possibleMenu.contains(tpresult))
				{
					continue jump;
				}
				KcalSum += foodInfo.get(tpresult).get(0);
				
				//user_kcal에 조금만 더해도 값을 넘어버릴 때..다시 뽑게끔 조치해야 한다. 
				if (KcalSum > user_kcal+50)
				{
					KcalSum -= foodInfo.get(tpresult).get(0);
					continue jump;
				}
				
				topprint.add(tpresult);
				totSum += foodInfo.get(tpresult).get(1);
			}
			//토핑 추가 뽑기 구문 종료
			
			repick2 = Resultprint();
			// 초기화
			if (!repick2.equals("Y"))
			{
				KcalSum = 0;
				totSum = 0;
				topprint.removeAll(topprint);
			}
			
		}
		while (!repick2.equals("Y"));
		
		
		
	}	
	
	// 사이드 먹고 알레르기 없음
	protected void SdY_AlN() throws Exception
	{
		do
		{

			do
			{
				rdbase = rd.nextInt(3);
				bsresult = base1.get(rdbase);
			}
			while (!possibleMenu.contains(bsresult));
			
			
			do
			{
				rdsauce = rd.nextInt(6);
				scresult = base2.get(rdsauce);
			}
			while (!possibleMenu.contains(scresult));
			
			do
			{
				rdtop = rd.nextInt(20);
				tpresult = base3.get(rdtop);
			}
			while (!possibleMenu.contains(tpresult));
			
			do
			{
				rdside = rd.nextInt(4);
				sdresult = base4.get(rdside);
			}
			while (!possibleMenu.contains(sdresult));
			
			topprint.add(tpresult);		//뽑힌 토핑을 topprint 리스트에 넣는다.
			
			KcalSum += foodInfo.get(bsresult).get(0) + foodInfo.get(scresult).get(0) + foodInfo.get(tpresult).get(0) + foodInfo.get(sdresult).get(0); 
			totSum += foodInfo.get(bsresult).get(1) + foodInfo.get(scresult).get(1) + foodInfo.get(tpresult).get(1) + foodInfo.get(sdresult).get(1);
			
			if (KcalSum < user_kcal-50)
			{
				jump:
				while (KcalSum < user_kcal-50 )		// 칼로리 < 유저 칼로리 -10이면 반복
				{
					rdtop = rd.nextInt(20);
					tpresult = base3.get(rdtop);
					
					// 중복 생기면 다시 뽑아야 함 // 재고 없으면 다시 뽑아야 함
					if (topprint.contains(tpresult) || !possibleMenu.contains(tpresult))
					{
						continue jump;
					}
						
					KcalSum += foodInfo.get(tpresult).get(0);
					
					//user_kcal에 조금만 더해도 값을 넘어버릴 때..다시 뽑게끔 조치해야 한다. 
					if (KcalSum > user_kcal+50)
					{
						KcalSum -= foodInfo.get(tpresult).get(0);
						continue jump;
					}
					topprint.add(tpresult);
					totSum += foodInfo.get(tpresult).get(1);
					
				} //반복문 종료
				
			} //추가토핑뽑기 구문 종료
			
			repick2 = Resultprint();
			if (!repick2.equals("Y"))
			{
				KcalSum = 0;
				totSum = 0;
				topprint.removeAll(topprint);
			}
			
		}
		while (!repick2.equals("Y"));
		
	}	
	
	
	// 사이드 안 먹고 알레르기 없음
	protected void SdN_AlN() throws Exception
	{
		do
		{
			do
			{
				rdbase = rd.nextInt(3);
				bsresult = base1.get(rdbase);
			}
			while (!possibleMenu.contains(bsresult));
		
			do
			{
				rdsauce = rd.nextInt(6);
				scresult = base2.get(rdsauce);
			}
			while (!possibleMenu.contains(scresult));
			rdtop = rd.nextInt(20);
			
			do
			{
				rdtop = rd.nextInt(20);
				tpresult = base3.get(rdtop);
			}
			while (!possibleMenu.contains(tpresult));
						
			topprint.add(tpresult);		//뽑힌 토핑을 topprint 리스트에 넣는다.
			
			KcalSum += foodInfo.get(bsresult).get(0) + foodInfo.get(scresult).get(0) + foodInfo.get(tpresult).get(0); 
			totSum += foodInfo.get(bsresult).get(1) + foodInfo.get(scresult).get(1) + foodInfo.get(tpresult).get(1);
			
			//만약 다 뽑았는데 모자란다면, 토핑만 추가로 뽑는 과정을 반복한다. 
			if (KcalSum < user_kcal-50)
			{
				jump:
				while (KcalSum < user_kcal-50 )		// 칼로리 < 유저 칼로리 -10이면 반복
				{
					rdtop = rd.nextInt(20);
					tpresult = base3.get(rdtop);
					
					// 중복 생기면 다시 뽑아야 함
					if (topprint.contains(tpresult))
					{
						continue jump;
					}
					
					KcalSum += foodInfo.get(tpresult).get(0);
					
					//user_kcal에 조금만 더해도 값을 넘어버릴 때..다시 뽑게끔 조치해야 한다. 
					if (KcalSum > user_kcal+50)
					{
						KcalSum -= foodInfo.get(tpresult).get(0);
						continue jump;
					}
					topprint.add(tpresult);
					totSum += foodInfo.get(tpresult).get(1);
					
				} //반복문 종료
				
			} //추가토핑뽑기 구문 종료
			
			repick2 = Resultprint();
			// 초기화
			if (!repick2.equals("Y"))
			{
				KcalSum = 0;
				totSum = 0;
				topprint.removeAll(topprint);
			}
		}
		while (!repick2.equals("Y"));
		
		
		
	}	
	
	
	
	// 출력 메소드
	public String Resultprint() throws Exception
	{
		//선택된 토핑을 담은 Arraylist를 출력하기 위해 Iterator 생성
		ListIterator<String> li = topprint.listIterator();		
		
		System.out.println();
		System.out.println("\t=================================================");
		System.out.println();
		System.out.println("\t\t\t   [조합 결과]");
		System.out.println();
		System.out.printf("\t베이스 ▶ %s\n", bsresult);
		System.out.printf("\t소스   ▶ %s\n", scresult);
		System.out.print("\t토핑   ▶ ");
		while (li.hasNext())				//토핑 출력
		{
			System.out.print(li.next() + " ");
		}
		
		System.out.println();				//개행
		if (sideyes)				//사이드 추가했으면 사이드 출력하는 구문
		{
			System.out.printf("\t사이드 ▶ %s\n", sdresult);
		}
		System.out.printf("\n\t칼로리 ▶ %d \n\t총  액 ▶ %d\n", KcalSum, totSum);
		System.out.println();
		System.out.println("\t=================================================");
		System.out.println();
		System.out.println("\t이대로 결제하시겠습니까?");
		System.out.println("\t[Y 외의 다른 문자는 N으로 간주합니다]");
		System.out.print("\tN 을 선택 시 처음부터 다시 뽑습니다 (Y / N) : ");
		yesno = sc.next();
		yesno = yesno.toUpperCase();
		System.out.println();
		String repick = yesno;
		sc.nextLine();
		return repick;
		
		
	}
		
	// 총 가격 반환하는 메소드
	public int gettotSum()
	{
		return totSum;
	}	
	
	// 총 칼로리 반환
	public int getKcalSum()
	{
		return KcalSum;
	}
	
	// 선택된 전체 메뉴 반환
	public Vector<String> getRandomMenu()
	{
		pickedMenu.add(bsresult);
		pickedMenu.add(scresult);
		pickedMenu.addAll(topprint);
		if (sideyes)
		{
			pickedMenu.add(sdresult);
		}
		
		
		return pickedMenu;
	}
	
}
