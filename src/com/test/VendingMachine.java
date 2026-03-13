import java.util.Scanner;

public class VendingMachine
{
	public static int gusetNum;
	public static void main(String[] args) throws Exception
	{
		String secretCode = "";  // 관리자, 종료 코드를 구분하기 위한 변수
		int setMealKcal = 0;     // 한끼 권장 칼로리를 담는 변수
		
		do
		{
			Scanner sc = new Scanner(System.in);
			CalculateDate cd = new CalculateDate();
			System.out.print("\t" + cd.getToday() + "\t" +cd.saleday() + "\n");
			System.out.println("\t=================================================");
			System.out.println("\t####  ####  #  ## ####     ####  ####  #  ## ####");   
			System.out.println("\t#  # ##  ## # #   #        #  # ##  ## # #   #   ");   
			System.out.println("\t#  # #    # ##    ###      #  # #    # ##    ### ");   
			System.out.println("\t###  #    # # #   #        ###  #    # # #   #   ");   
			System.out.println("\t#    ##  ## # #   #        #    ##  ## # #   #   ");   
			System.out.println("\t#     ####  #  ## ####     #     ####  #  ## ####");   
			System.out.println("\t=================================================");
			System.out.println();                     
			System.out.println("\t    ♬ ♬ ♬ ＼(´▽`)／ 환영합니다 ＼(´▽`)／♬ ♬ ♬");
			System.out.println();
			System.out.println("\t    고객님의 건강을 먼저 생각하는 포케 자판기");
			System.out.println();
			System.out.println("\t            [포케 포케] 입니다!!!");
			System.out.println();
			System.out.print("\t      이용하시려면 아무 키나 입력해주세요 ");
			secretCode = sc.nextLine();
			System.out.println();
			System.out.println("\t================================================="); 
			System.out.println();
			
			// 관리자 메뉴 진입 코드
			if (secretCode.equals("admin"))
			{
				// 관리자 클래스 진입 전 로그인
				AdminLogin ad = new AdminLogin();
				
				// 콘솔 출력을 깨끗하게 보기 위해서 80줄 밑으로 내린다.
				for (int i = 0; i < 80; i++)
				{
					System.out.println("");
				}
			}
			// 자판기 종료 코드
			else if (secretCode.equals("end"))
			{
				break;
			}
			// 손님 입장
			else
			{
				CorrectMenuValue cmv = new CorrectMenuValue();
				Manage mg = new Manage();
				
				// 1. 칼로리 계산 진행
				KcalCalc kc = new KcalCalc();
				kc.setKcal();
				setMealKcal = kc.getKcal();  
	
				// 2. 메뉴판 클래스
				System.out.println();
				System.out.println("\t     [원하시는 메뉴 선택방법을 골라주세요]");
				System.out.println();
				System.out.println("\t     내 마음대로 내 입맛대로 내 건강에 맞게 ");
				System.out.println("\t          ▼ 맞춤 한 끼를 원하시면 ▼");
				System.out.println("\t            [1 : 메뉴 직접 선택]");
				System.out.println();
				System.out.println("\t        초간단 초간편 쉽고 빠른 1초 컷!!");
				System.out.println("\t          ▼ 랜덤 한 끼를 원하시면 ▼");
				System.out.println("\t            [2 : 메뉴 랜덤 선택]");
				System.out.println();
				System.out.print("\t       고객님의 선택은 무엇인가요? : ");
				String strSelect = sc.nextLine();
				int userSelect = cmv.correctSelect(1, 2, strSelect);
				UsedMoney um = new UsedMoney();
				System.out.println();
				switch (userSelect)
				{
					case 1:
						System.out.println("\t=================================================");
						System.out.println();
						System.out.println("\t\t\t[메뉴 직접 선택]");
						System.out.println();
						CustomizeMenu custom = new CustomizeMenu(setMealKcal);

						if (custom.getPickResult()== true)
						{
							um.pickedPriceSort(custom.getPickedMenu());
							um.sumPickedPrice();
							um.pickedExchange();
							mg.ingredient(custom.getPickedMenu());
							gusetNum++;
						}
						else
						{
							for (int i = 0; i < 80; i++)
							{
								System.out.println("");
							}
						}
						break;
					case 2:
						System.out.println("\t=================================================");
						System.out.println();
						System.out.println("\t\t\t[메뉴 랜덤 선택]");
						System.out.println();
						Randomenu rm = new Randomenu(setMealKcal);
						rm.Callchoose(rm.Question());
						
						um.randomPriceSort(rm.getRandomMenu());
						um.sumRandomPrice();
						um.randomExchange();
						mg.ingredient(rm.getRandomMenu());
						gusetNum++;
						break;
				
				}
				
			}
		}
		while (true);
		
		System.out.println("\t           포케 포케 전원을 종료합니다. ");
		System.out.println();
		System.out.println("\t================================================="); 
	}
}