import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Scanner;

import java.util.Calendar;
import java.text.SimpleDateFormat;

//-------------------------------------------------------------------------------------------------------------------------------------
//**class UsedMoney를 Tool 상속
class UsedMoney extends Tool
{
	CalculateDate cd = new CalculateDate();
	
	// 랜덤 가격을 담을 변수 선언
	Vector<Integer> randomPrice = new Vector<>();       //-------- 여기서 직접 선택이랑, 랜덤선택한 품목을 담은 Vector 메소드 사용
	
	// 가격을 담을 변수 선언
	Vector<Integer> pickedPrice = new Vector<>();				
			
	// 일반, 할인 받는 날의 가격을 반환할 변수 선언
	int pickedTotal = 0;
	int randomTotal = 0;
	
	
	// 생성자
	UsedMoney() throws Exception
	{
		super.setFile();			//-- 부모 클래스의 전역변수 중 품목명,칼로리,가격 정보(Hashtable)을 set 하는 메소드 실행
		super.setPossibleMenu();	//-- 부모 클래스의 전역변수 중 품절이 아닌 메뉴 정보(Vector)을 set 하는 메소드 실행
		super.setBases();			//-- 부모 클래스의 전역변수 중 단계별 메뉴 정보(ArrayList)들을 set 하는 메소드 실행
	}
		
	
	// 백터 getpickedMenu에 있는 식품들의 가격만 백터 price에 담는 메소드
	public void pickedPriceSort(Vector<String> v1) throws Exception	//*** throws 문 추가(line38 인스턴스 때문)
	{
		if (foodInfo == null)
		{
			System.out.println("foodInfo가 초기화되지 않았습니다.");
			//return;					//--retrun 문 추가: 만약 foodinfo가 초기화되지 않았으면 더 이상 실행하지 않도록 return
		}
		
		for(String item : v1)						//*** getPickedMenu() → v1
		{	
			//ArrayList<Integer> details = menuInfo.get(item);
			ArrayList<Integer> details = foodInfo.get(item);
			pickedPrice.add(details.get(1));
		}
		
	}
	
	// 백터 getpickedMenu 총합 구하는 메소드
	public int sumPickedPrice() throws Exception //*** throws 문 추가(line36에 쓴 throws 문 때문)
	{	
		System.out.println("\t=================================================");
		System.out.println();
		System.out.println("\t\t\t[결제를 진행합니다]");
		System.out.println();
		int total=0;
		
		for (int item : pickedPrice) 
		{  
            total += item;
        }
		
		if (cd.w == Calendar.MONDAY) 
			pickedTotal = (int)(total * 0.9);  // 10% 할인 적용
		else 
			pickedTotal = total;  // 할인 적용 없이 원래 값 반환
		System.out.println("\t   고객님이 결제하실 총 금액은 " + pickedTotal + "원 입니다.");
		System.out.println();
		return pickedTotal;
	}
	
	
	// 백터 랜덤메뉴에 있는 식품들의 가격만 백터 randomPrice에 담는 메소드
	public void randomPriceSort(Vector<String> v2)
	{
		if (foodInfo == null)
		{
			System.out.println("Info가 초기화되지 않았습니다.");
		}
		
		
		for(String item : v2)						//*** getRandomMenu() → v
		{											
			ArrayList<Integer> details = foodInfo.get(item);
			randomPrice.add(details.get(1));
		}
	}
	
	
	
	// 백터 getRandomMenu 총합 구하는 메소드
	public int sumRandomPrice()
	{
		System.out.println("\t=================================================");
		System.out.println();
		System.out.println("\t\t\t[결제를 진행합니다]");
		System.out.println();
		int total=0;
		
		for (int item : randomPrice) 
		{  
            total += item;
        }
		
		if (cd.w == Calendar.MONDAY) 
			randomTotal = (int)(total * 0.9);  // 10% 할인 적용
		else 
			randomTotal = total;  // 할인 적용 없이 원래 값 반환
		
		System.out.println("\t   고객님이 결제하실 총 금액은 " + randomTotal + "원 입니다.");
		System.out.println();
		return randomTotal;
	}
	

	// 금액 청구하는 메소드
	public void exchange(int totalPrice)
	{
		Scanner sc = new Scanner(System.in);
		int useMoney;

		do
		{
			System.out.print("\t   지불하실 금액을 입력하십시오 : ");
			useMoney = sc.nextInt();
			
		    if (useMoney < 0)
			{	
				System.out.println();
				System.out.println("\t\t\t[잘못된 입력입니다]");
				System.out.println();
			}
			else if (useMoney < totalPrice)
			{
				System.out.println();
				System.out.println("\t\t\t[금액이 부족합니다]");
				System.out.println();
			}
			else 
			{
				System.out.println();
				System.out.println("\t=================================================");
				System.out.println();
				System.out.println("\t\t\t   [영수증 내역]");
				System.out.println();
				System.out.println("\t   지불하신 금액은 " + useMoney + "원 입니다.");
				System.out.println();
				System.out.println("\t   거스름 돈은 " + (useMoney - totalPrice) + "원 입니다.");
				System.out.println();
			}
		}
		while (useMoney < totalPrice);
		System.out.println("      <(_ _)> [포케 포케]를 이용해주셔서 감사합니다. <(_ _)>");
		System.out.println();
		System.out.println("\t\t      다음에 또 이용해주세요");
		System.out.println();
		System.out.println("\t=================================================");
		System.out.println();
		//**결제 메소드 단, 아래 두 메소드로 호출해 쓸 것
	}
		
	// 선택메뉴 금액 청구
	public void pickedExchange() 
	{
        exchange(pickedTotal);		//-- pickedTotal : 이 클래스의 전역변수(int)
    }
	
	// 추천메뉴 금액 청구
    public void randomExchange() 
	{
        exchange(randomTotal);		//-- randomTotal : 이 클래스의 전역변수(int)
    }
	
}
// -----------------------------------------------------------------------------------------------------------------------------------------

class CalculateDate
{
	// Calendar 인스턴스 생성
	Calendar today = Calendar.getInstance();
	
	// w 에 오늘 날짜 대입
	int w = today.get(Calendar.DAY_OF_WEEK);
	
	// 현재 날짜 출력 인스턴스 생성
	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");
	
	// 오늘 날짜 반환 메소드
	public String getToday()			//-- getToday() 시 오늘 날짜를 "yyyy년 MM월 dd일 E요일" 형태로 반환
	{
		return sdf.format(today.getTime());
	}
	
	// 할인 적용하는 월요일일 때 출력 하는 매소드
	public String saleday()				//-- saleday() 시 오늘 날짜 월요일이면 할인 문구 반환
	{		
		if (w == 2)
			return "오늘은 전 메뉴 10% 할인";
		else
			return "";
		
	}
	
}// end CalculateDate