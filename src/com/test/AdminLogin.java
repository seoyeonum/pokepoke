import java.util.Scanner;
import java.util.Hashtable;

class AdminLogin
{
	private Hashtable<Object, Object> adminData;
	private UseToHashTableFromFile htff;
	private int password;
	private Scanner sc = new Scanner(System.in);
	private String stemp;
	
	AdminLogin() throws  Exception  
	{
		int cnt = 0;
		boolean flag = false;
		
		htff = new UseToHashTableFromFile("admin","admin");
		adminData = htff.deserial();
		
		// 콘솔 출력을 깨끗하게 보기 위해서 80줄 밑으로 내린다.
		for (int i = 0; i < 80; i++)
		{
			System.out.println("");
		}
		
		System.out.println("\t\t\t  [ 관리자 메뉴 ]");
		System.out.println();
		
		do
		{
			System.out.print("\t\t\t 비밀번호 : ");
			
			stemp = sc.nextLine();
		
			if(CorrectMenuValue.stringToint(stemp))
				password = Integer.parseInt(stemp);	
			else 
				password = Integer.parseInt(CorrectMenuValue.correctValue());

			System.out.println("\t================================================="); 
			
			if(!adminData.contains(password))       
			{	
				System.out.println("\t비밀번호가 틀립니다.");
				System.out.println();
				System.out.println("\t비밀번호를 다시 입력해주세요.");
				System.out.println();
				cnt++;
			}
			
			if (cnt>4)
			{
				if (flag)
				{
					System.out.println("\t프로그램 강제 종료합니다.");
					System.out.println();
					System.exit(0);
				}
				
				System.out.println("\t비밀번호를 5번 틀리셨습니다.");
				getPassword();
				cnt = 0;
				flag = true;	
			}
		}
		while (!adminData.contains(password));
		
		System.out.println("\t\t\t   로그인 성공");
		System.out.println();
		System.out.println("\t\t      환영합니다  관리자님");
		System.out.println("\t================================================="); 
		
		adminPage();
	}
	
	private void adminPage() throws Exception
	{
		int selectNum = 0;
		
		do
		{
			System.out.println();
			System.out.println("\t\t\t[관리자 페이지]");
			System.out.println();
			System.out.println("\t\t\t1. 비밀번화 재설정");
			System.out.println("\t\t\t2. 관리 기능");
			System.out.println("\t\t\t3. 일 수입 그래프");
			System.out.println("\t\t\t4. 종료");
			System.out.println();
			System.out.print("\t\t원하시는 메뉴를 선택하세요 : ");
			stemp = sc.nextLine();
			selectNum = CorrectMenuValue.correctSelect(1,4,stemp);
			System.out.println();
			System.out.println("\t=================================================");
			
			switch (selectNum)
			{
				case 1:
					resetPassword();
					break;
				case 2:
					Manage admin = new Manage();
					admin.mainMenu();
					break;
				case 3:
					IncomeGraph income = new IncomeGraph();
					income.view();
					break;			
			}
		}
		while (selectNum!=4);
	}
	
	// 비밀번호 불러오기
	private void getPassword() throws Exception
	{	
		System.out.println();
		System.out.println("\t관리자 비밀번호는 "+(int)adminData.get("admin") + "입니다.");	
		System.out.println();
	}
	
	
	// 로그인해서 들어왔을 때 비밀번호 재설정을 위해
	private void resetPassword() throws  Exception
	{
		int newPassword = 0;
		System.out.println();
		System.out.print("\t   변경할 비밀 번호를 4자리 입력해주세요 : ");
		String stemp = sc.nextLine();
		
		if(CorrectMenuValue.stringToint(stemp))
			newPassword = Integer.parseInt(stemp);	
		else 
			newPassword = Integer.parseInt(CorrectMenuValue.correctValue());
		
		System.out.println();
		System.out.println("\t================================================="); 	
		adminData.put("admin", newPassword);

		htff.serial(adminData);
		System.out.println();
		System.out.println("\t\t      비밀번호 변경 성공");
		System.out.println();
		System.out.println("\t================================================="); 	
	}
	
	private void incomeGraph()
	{
		
	}
}

