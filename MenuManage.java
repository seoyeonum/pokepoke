import java.util.Scanner;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;

public class MenuManage
{
	private UseToHashTableFromFile menuFile = new UseToHashTableFromFile("IngrInfo","foodInfo");
	private Hashtable<Object, Object> foodMenu;
	private UseToHashTableFromFile stockFile = new UseToHashTableFromFile("IngrInfo","foodStock");
	private Hashtable<Object, Object> foodStock;
	
	private Scanner sc = new Scanner(System.in);
	
	MenuManage() throws Exception
	{
		foodMenu = menuFile.deserial();
		foodStock = stockFile.deserial();
	}
	
    public void menuRun() throws Exception
	{
		// 관리자의 선택메뉴를 담을 변수
		int num = 0;
		
		do
		{
			System.out.println();
			System.out.println("\t\t\t[메뉴 관리]");
			System.out.println();
			System.out.println("\t\t\t1. 메뉴 전체 보기");
			System.out.println("\t\t\t2. 메뉴 추가");
			System.out.println("\t\t\t3. 메뉴 제거");
			System.out.println("\t\t\t4. 뒤로 가기");
			System.out.println();
			System.out.print("\t\t원하시는 메뉴를 선택하세요 : ");
			String stemp = sc.nextLine();
			num = CorrectMenuValue.correctSelect(1,4,stemp);
			System.out.println();
			System.out.println("\t=================================================");

	
			switch (num)
			{
				case 1:
					menuView();
					break;
				case 2:
					menuAdd();
					break;
				case 3:
					menuDel();
					break;
			}
			
		}
		while (num!=4);
		
		menuFile.serial(foodMenu);
		stockFile.serial(foodStock);
    }

    //메뉴내용을 추가하는 메소드
    private void menuAdd() throws  Exception 
	{
        //계속할지 여부를 저장할 변수 선언 및 초기화
        int sel=0;

        //추가할 key값을 저장할 변수 선언
        String key;


        //메뉴 추가기능을 반복수행
        do 
		{
            //메뉴의 칼로리와 가격을 받아둘 ArrayList 인스턴스 생성
            //반복문 안에 인스턴스를 생성한 이유
            //→반복문 밖에 인스턴스를 생성 시 누적된 [칼로리 가격]이 전부 저장되기 때문에
            ArrayList<Integer> value = new ArrayList<Integer>();

            //메뉴이름 [칼로기 가격]의 추가할 것을 기입
			System.out.println();
            System.out.println("   \t 추가할 메뉴 정보를 입력하세요");
			System.out.print("   \t (형식 : 메뉴명(공백)칼로리(공백)가격) : ");
            //관리자가 입력한 것들을 키값과 벨류값에 저장
            key = sc.next();
            value.add(sc.nextInt());
            value.add(sc.nextInt());
			sc.nextLine();
			System.out.println();
			System.out.println("   \t추가할 메뉴의 타입을 입력하세요");
			System.out.print("\t (1. 베이스, 2. 소스, 3. 토핑, 4. 사이드 메뉴) : ");
			String stemp = sc.nextLine();			
			ArrayMenuInfo baseInfo = new ArrayMenuInfo(stemp);
			baseInfo.addBase(key);
            //관리자가 입력한 것들을 키값과 벨류값으로 Hashtable 저장
            foodMenu.put(key, value);
			foodStock.put(key, 10);
			
			// 
            //계속 수행 할지의 여부 확인
			System.out.println();
            System.out.println("\t\t계속 진행하시겠습니까 ?");
            System.out.print("\t\t(1. 계속  2. 그만하기) : ");
            stemp = sc.nextLine();
			sel = CorrectMenuValue.correctSelect(1,2,stemp);
			System.out.println();
			System.out.println("\t================================================="); 

        }while (sel==1);
    }

    //메뉴내용을 제거하는 메소드
    private void menuDel() throws  Exception
	{
        //제거할 key값을 저장할 변수 선언
        String key;

        //계속할지 여부를 저장할 변수 선언 및 초기화
        int sel=0;

        //메뉴 추가기능을 반복수행
        do 
		{
			//메뉴에서 제거할 메뉴 입력
			System.out.println();
            System.out.print(" \t\t제거할 메뉴 명을 입력하세요 : ");

			//관리자가 입력한 값을 키값으로 담아 둠
			key = sc.nextLine();
			String stemp = "";
			if (foodMenu.containsKey(key))
			{
				//관리자가 입력한 키값으로 해당 메뉴판의 값제거
				foodMenu.remove(key);
				foodStock.remove(key);
				
				System.out.println();
				System.out.println("   \t제거할 메뉴의 타입을 입력하세요");
				System.out.print("\t (1. 베이스, 2. 소스, 3. 토핑, 4. 사이드 메뉴) : ");
				stemp = sc.nextLine();	
				ArrayMenuInfo baseInfo = new ArrayMenuInfo(stemp);
				baseInfo.removeBase(key);	
			}
			else
			{
				System.out.println("   \t제거할 메뉴가 없습니다.");
				System.out.println();
			}
			//계속 진행할지의 여부 확인
			System.out.println();
            System.out.println("\t\t계속 진행하시겠습니까 ?");
            System.out.print("\t\t(1. 계속  2. 그만하기) : ");
            stemp = sc.nextLine();
			sel = CorrectMenuValue.correctSelect(1,2,stemp);
			
			System.out.println();
			System.out.println("\t================================================="); 


        }while (sel==1);
    }

    //메뉴판을 메뉴전체를 확인하는 메소드
    private void menuView() throws Exception
	{
        //키값을 담아둘 변수 선언
        String key;
        //벨류 값을 담아둘 변수 선언
        ArrayList<Integer> value;
        //해쉬테이블을 반복 수행할 Enumeration타입 변수  e 선언
        Enumeration e;
		// 메뉴 개수를 세는 변수
		int menuCnt = 0; 
        e = foodMenu.keys();
		System.out.println();
		System.out.println("\t-------------------------------------------------");
		System.out.println("\t|   메뉴명    |    칼로리(kcal)   |    가격(원) |");
		System.out.println("\t-------------------------------------------------");
        while(e.hasMoreElements())
		{
            key =  (String)e.nextElement();
            value = (ArrayList<Integer>)foodMenu.get(key);
			System.out.printf("\t|%7s\t\t%5d\t\t%5d   |\n",key,value.get(0),value.get(1));
			System.out.println("\t-------------------------------------------------");
			menuCnt++;
        }
		System.out.println("\t\t\t\t\t총 메뉴 개수 : " + menuCnt);
		System.out.println();
        System.out.println("\t=================================================");

    }

}