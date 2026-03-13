import java.util.Scanner;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Enumeration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

class Manage
{	
	private UseToHashTableFromFile menuFile = new UseToHashTableFromFile("IngrInfo","foodInfo");
	private Hashtable<Object, Object> foodMenu;
	
	private UseToHashTableFromFile stockFile = new UseToHashTableFromFile("IngrInfo","foodStock");
	private Hashtable<Object, Object> foodStock;
	
	
	Manage() throws Exception
	{
		foodMenu = menuFile.deserial();
		foodStock = stockFile.deserial();
	}
	
	// 외부에서 접근해서 정보를 가져가는 메소드 모음
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
    // 메뉴 파일을 메뉴 클래스에 전달하는 메소드
	// 1. 반환 타입은 Hashtable<Object, Object> 이다.
	// 2. 파일의 타입은 Hashtable<String, ArrayList<Integer>> 이다.
	// 3. 메소드 호출 후 파일 사용 시 String key, ArrayList<Integer> value로 다운캐스팅해서 사용한다.
    public Hashtable<Object, Object> getMenuInfo() 
	{
		//  메뉴 정보 (메뉴 명, 칼로리, 가격)를 역직렬화해서 전달한다.
        return foodMenu;
    }
	
    // 재고 파일에서 재고가 있는 품목 리스트를 Vector로 담아 넘겨주는 메소드
    public Vector<String> getPossibleMenu()
	{
		Vector<String> vt = new Vector<String>();
        String key; int value; Enumeration e;
        e = foodStock.keys();
		
        while (e.hasMoreElements())
		{
           key = (String) e.nextElement();
           value = (int)foodStock.get(key);
           if(value<=0)
               continue;

           vt.add(key);
        }
        return vt;
    }
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
    // 메뉴 클래스에서 판매한 메뉴를 Vector<String> 타입으로 전달받는 메소드 (= 외부에서 호출해서 매개변수로 넘겨받는 메소드)
    public void ingredient(Vector<String> usedMenu) throws Exception 
	{
        changeIngre(usedMenu);
    }

    // 메뉴 클래스에서 주문 완료 후 보낸 판매한 메뉴을 받아와 재고파일에 내용을 수정하는 메소드
	// 1. 메뉴 재고 파일의 타입은 Hashtable<String, Integer> (= 메뉴명, 재고 개수) 이다.
	// 2. 매개변수로 받아오는 판매된 메뉴는 Vector<String> 타입이다. 
    // 3. 판매된 메뉴와 재고 파일의 key(메뉴명)을 비교해서 해당하는 메뉴의  value(재고 개수)를 -1한다.  
    private void changeIngre(Vector<String> usedMenu) throws Exception 
	{	
		String key; int value; Enumeration e; 
		e = foodStock.keys();
		
		while (e.hasMoreElements())
		{
			key = (String)e.nextElement();
            value = (int)foodStock.get(key);
			
			for (int i = 0; i<usedMenu.size();i++)
			{
				if(usedMenu.elementAt(i).equals(key))
				{
                    foodStock.put(key,(value-1));
                    break;
                }
			}
		}
		
		// 변경된 재고를 직렬화해서 저장한다.
		stockFile.serial(foodStock);
		
		// 판매한 메뉴를 통해서 하루 수입을 계산하고 저장한다.
        oneDayIncome(usedMenu);
    }


	// 판매한 메뉴를 통해서 하루 수입을 계산하고 직렬화해 저장하는 메소드
	private void oneDayIncome(Vector<String> usedMenu) throws Exception
	{
        // 판매된 메뉴의 총합 가격
        int sum = 0;

		//ArrayList<Integer> value;
		
        for(String key : usedMenu)
		{
            ArrayList<Integer> value = (ArrayList<Integer>)foodMenu.get(key);
            sum += value.get(1);
        }
		VendingMachine vm = new VendingMachine();
        
		String appDir = System.getProperty("user.dir");
		File file = new File(appDir,"\\IngrInfo\\"+ vm.gusetNum +".ser");

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
		
        oos.writeObject(sum);
        fos.close();
        oos.close();
	}
	
    //관리자 관리 선택사항 항목을 선택에 따라 실행시켜주는 메소드
    public void mainMenu() throws  Exception
	{
		// 관리자의 선택번호를 담을 변수
		int num = 0;
        //Ingredient ig = new Ingredient();
        //MoneyManager mon = new MoneyManager();
        //MenuManage men = new MenuManage();

        //만약 관리자가 숫자 이외의 값을 입력하였을 시 예외처리 수행
        //우선 한 번 수행 후, 해당 조건 충족 시 반복 수행
		do 
		{
			Scanner sc = new Scanner(System.in);
			System.out.println();
			System.out.printf("\t\t\t[관리 기능]\n");
			System.out.println();
			System.out.println("\t\t\t1. 메뉴 관리");
			System.out.println("\t\t\t2. 재고 관리");
			System.out.println("\t\t\t3. 뒤로 가기");
			System.out.println();
			System.out.print("\t\t원하시는 메뉴를 선택하세요 : ");
			String stemp = sc.nextLine();
			num = CorrectMenuValue.correctSelect(1,3,stemp);
			
			System.out.println();
			System.out.println("\t=================================================");
			
			//관리자에 의해 입려받은 번호에 따라 해당 기능 수행
			switch (num)
			{	
				// 메뉴 관리
				case 1:
					MenuManage mm = new MenuManage();
					mm.menuRun();
					break;
				// 재고 관리
				case 2:
					StockManage sm = new StockManage();
					sm.menuRun();
					break;
				case 3://mon.moneyRan();break;
				//4번을 선택 시 관리자 관리 종료.
			}
		 
		}while (num != 3);
    }
}