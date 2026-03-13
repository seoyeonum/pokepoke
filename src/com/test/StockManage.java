import java.util.Scanner;
import java.io.File;
import java.util.Hashtable;
import java.util.Enumeration;

class StockManage
{
	private UseToHashTableFromFile stockFile = new UseToHashTableFromFile("IngrInfo","foodStock");
	private Hashtable<Object, Object> foodStock;
	
	private Scanner sc = new Scanner(System.in);
	
	StockManage() throws Exception
	{
		foodStock = stockFile.deserial();
	}
	
	public void menuRun() throws Exception
	{
		// 관리자의 선택메뉴를 담을 변수
		int num = 0;
		
		do
		{
			System.out.println();
			System.out.println("\t\t\t[재고 관리]");
			System.out.println();
			System.out.println("\t\t\t1. 재고 전체 보기");
			System.out.println("\t\t\t2. 재고 넣기");
			System.out.println("\t\t\t3. 뒤로 가기");
			System.out.println();
			System.out.print("\t\t원하시는 메뉴를 선택하세요 : ");
			String stemp = sc.nextLine();
			num = CorrectMenuValue.correctSelect(1,3,stemp);
			
			System.out.println();
			System.out.println("\t=================================================");

			switch (num)
			{
				case 1:
					stockView();
					break;
				case 2:
					stockAdd();
					break;
			}
		}
		while (num!=3);
		
		stockFile.serial(foodStock);
    }
	
    // 재고 전체 보기
    private void stockView() throws Exception
	{
        //키값을 담아둘 변수 선언
        String key;
		
        //벨류 값을 담아둘 변수 선언
        Integer value;
		
        //해쉬테이블을 반복 수행할 Enumeration타입 변수  e 선언
        Enumeration e;
        e = foodStock.keys();
		
		System.out.println();
		System.out.println("\t\t-----------------------------");
		System.out.println("\t\t|    품목명   |     개수    |");
		System.out.println("\t\t-----------------------------");
		int menuCnt = 0;
		
        while(e.hasMoreElements())
		{
            key =  (String)e.nextElement();
            value = (int)foodStock.get(key);
			System.out.printf("\t\t|%7s\t  %5d     |\n",key,value);
			System.out.println("\t\t-----------------------------");
			menuCnt++;
        }
		
        System.out.println("\t\t\t   총 품목 개수 : " + menuCnt);
		System.out.println();
        System.out.println("\t=================================================");

    }
	
	// 재고 추가
    private void stockAdd() throws Exception
	{	
		System.out.println();
        //추가시킬 물품 입력
        System.out.print("\t   추가할 품목 명을 입력하세요 : ");
        //물품명을 담아둘 변수 선언 및 입력 값 저장
        String key =sc.nextLine();
		System.out.println();
        //추가시킬 물품과 수량입력
        System.out.print("\t   추가할 품목 개수를 입력하세요 : ");
        //물품추가수량을 담아둘 변수 선언 및 입력 값 저장
		
        int temp = 0;
		String stemp = sc.nextLine();
		
		if(CorrectMenuValue.stringToint(stemp))
			temp = Integer.parseInt(stemp);	
		else 
			temp = Integer.parseInt(CorrectMenuValue.correctValue());
		
		
		if(foodStock.containsKey(key))
		{
			//입력한 물품의 수량을 잠시 담아둘 변수 선언 및 해당 수량 잠시 저장
			int value = (int)foodStock.get(key);

			//기존에 수량에 입력한 수량을 더해서 저장
			value = value + temp;

			//해당 수량을 증가시킨 값으로 변경
			foodStock.put(key,value);
			
			System.out.println();
			System.out.println("\t\t     [품목이 추가되었습니다]");
		}
		else
		{
			System.out.println();
			System.out.println("\t=================================================");
			System.out.println();
			System.out.println("\t\t\t  ※  확인 요망  ※ ");
			System.out.println();
			System.out.println("\t\t  해당 품목이 존재하지 않습니다.");
			System.out.println();
			System.out.println("\t메뉴 관리에서 메뉴를 추가하시고 재고를 추가해주세요.");
		}
		
		System.out.println();
		System.out.println("\t=================================================");
    }
}