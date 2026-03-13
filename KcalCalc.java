import java.util.Scanner;

// 한끼 권장 칼로리를 계산하는 클래스
// 1. setKcal()로 입력을 통해 사용자 정보를 초기화한다.
// 2. getKcal()로 한끼 권장 칼로리를 반환받는다.
class KcalCalc
{
	// 입력 받을 사용자 정보 
	// (외부에서의 접근은 setKcal()와 getKcal() 메소드를 통해서만 가능하다)
	private int height;           // 키
	private int weight;           // 몸무게
	private int age;              // 나이
	private int gender;			  // 성별
	private double activityFator; // 활동요소
	
	// 1. 입력받은 사용자 정보 중 정수로 저장해야 하는 변수들이 있다. 
	// 2. 사용자가 정수형태로 올바르게 입력했는지 확인하기 위해
	//    먼저 String으로 값을 입력받고 int로 변환이 가능한지를 통해 올바른 입력인지를 구분한다.
	// 3. 그래서 먼저 입력받기 위한 String 변수
	private String stemp;
	
	// 입력받은 정보를 통해 계산한 한끼 권장 칼로리를 담을 변수
	private int setMealKcal;
	
	// 외부에서 한끼 권장 칼로리를 얻을 수 있는 메소드
	public int getKcal()
	{
		return setMealKcal;
	}
	
	// 사용자의 입력을 통해 사용자 정보를 초기화한다.
	public void setKcal()
	{
		/////////////////////////////////////////////////////////////////////////////////////
		// 1. 키, 몸무게, 나이는 입력 범위가 없는 값이다.
		// 2. 전역 변수로 선언된 String stemp로 입력을 받는다.
		// 3. CorrectIntValue 클래스의 static stringToint(String str)메소드는 넘겨 받은 String 매개변수를 int로 변환 가능한 지를 검사한다.
		//    가능하면 true를 불가능하면 false를 반환한다.
		// 4. true면 int로 변환하여 사용자 정보 변수에 초기화한다.
		// 5. CorrectIntValue 클래스의 static correctValue()메소드는 int로 변환이 가능한 값을 입력받을 때까지 입력을 받는다.
		//    반환 값은 int변환이 가능한 String값이다.
		// 6. 3번에서 false면 5번이 진행되고 리턴값을 int로 변화하여 사용자 정보 변수에 초기화한다.
		///////////////////////////////////////////////////////////////////////////////////////
		Scanner sc = new Scanner(System.in);
		System.out.println("\t    고객님의 한끼 권장 칼로리 계산하기 위한");
		System.out.println();
		System.out.println("\t\t  [간단한 개인정보 입력]입니다.");
		System.out.println();
		System.out.println("\t※ 입력하신 정보는 이용을 끝내시면 자동소멸됩니다.");
		System.out.println();
		System.out.print("\t고객님의 키를 기입해주세요 : ");
		stemp = sc.nextLine();
		
		if(CorrectMenuValue.stringToint(stemp))
			height = Integer.parseInt(stemp);	
		else 
			height = Integer.parseInt(CorrectMenuValue.correctValue());
		
		System.out.println();
		System.out.print("\t고객님의 몸무게를 기입해주세요 : ");
		stemp = sc.nextLine();
		
		if(CorrectMenuValue.stringToint(stemp))
			weight = Integer.parseInt(stemp);	
		else 
			weight = Integer.parseInt(CorrectMenuValue.correctValue());
		
		System.out.println();
		System.out.print("\t고객님의 나이를 기입해주세요 : ");
		stemp = sc.nextLine();
		
		if(CorrectMenuValue.stringToint(stemp))
			age = Integer.parseInt(stemp);	
		else 
			age = Integer.parseInt(CorrectMenuValue.correctValue());
		///////////////////////////////////////////////////////////////////////////////////////////////
		// 1. 성별, 주당 활동량은 입력 범위가 있는 값이다.
		// 2. 전역 변수로 선언된 String stemp로 입력을 받는다.
		// 3. CorrectMenuValue 클래스의 static correctSelect(int start, int end, String str)은 매개변수로 시작지점과 종료지점과 입력받은 값을 가진다.
		//    해당 메소드는 입력받은 값 String이 int로 변환이 가능하지를 CorrectMenuValue 클래스의 static stringToint(String str)를 통해 검사한다.
		// 4. 가능하면 시작 지점과 종료 지점의 범위에 속해있는지를 확인한다.
		// 5. 입력받은 값 String이 int로 변환이 가능하며 설정 범위에 속해있다면 반환 값은 입력받은 값을 int로 변환하여 반환한다.
		// 6. 반환한 값은 사용자 정보에 초기화한다.
		// * 올바른 값을 입력받을 때까지 매소드는 반복한다.
		///////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println();
		System.out.println("\t고객님의 성별을 선택해주세요");
		System.out.print("\t(1. 남성 , 2. 여성) : ");
		stemp = sc.nextLine();
		gender = CorrectMenuValue.correctSelect(1,2,stemp);
		
		System.out.println();
		System.out.println("\t고객님의 주당 활동량을 선택해주세요");
		System.out.println("\t(1. 거의 운동하지 않음)"); 
		System.out.println("\t(2. 가벼운 운동(주 1~3일)"); 
		System.out.println("\t(3. 보통 수준(주 3~5일)"); 
		System.out.println("\t(4. 적극적으로 운동(주 6~7일)");
		System.out.print("\t(5. 매우 적극적으로 운동(주 6~7일)) : ");
		stemp = sc.nextLine();
		int num = CorrectMenuValue.correctSelect(1,5,stemp);
		System.out.println();
		System.out.println("\t================================================="); 
		
		switch (num)
		{
			case 1:activityFator = 1.2;  break; 
			case 2:activityFator = 1.375;  break; 
			case 3:activityFator = 1.55;  break; 
			case 4:activityFator = 1.725;  break; 
			case 5:activityFator = 1.9;  break; 
		}
		
		// 전역변수로 선언된 사용자 정보를 모두 초기화하면, 칼로리 계산 메소드인 kcalResult() 메소드를 실행합니다.
		// 반환 값은 int타입의 한끼 권장 칼로리이다. 
		// 전역변수로 선언된 한끼 권장 칼로리를 담을 변수에 초기화한다.
		setMealKcal = kcalResult();
	}
	
	// 한끼 권장 칼로리를 계산하는 메소드이다. 반환 값은 int값인 한끼 권장 칼로리이다.
	private int kcalResult()
	{	
		// Kcalcalc클래스는 로컬 클래스로 하여 더욱 타이트하게 캡슐화했다.
		class Kcalcalc
		{	
			// 한끼 권장 칼로리를 계산하는 메소드
			// 반환 값은 double값인 한끼 권장 칼로리이다.
			private double OneMealKcal() 
			{
				// 1. 기초대사량 * 활동 요소는 하루 소비 칼로리이다.
				// 2. 하루 소비 칼로리를 3으로 나누어 한끼 권장 칼로리를 계산한다.
				return (defaultKcal() * activityFator)/3;
			}
			
			// 기초대사량을 계산하는 메소드
			private double defaultKcal() 
			{	
				double doubleBmr = 0;
				
				switch (gender)
				{
					case 1:
						doubleBmr = 66 + (13.7 * weight) + (5 * height) - (6.76 * age);
						break;
			
					case 2:
						doubleBmr = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
						break;
				}	
				
				return doubleBmr;
			}
		}	
		
		Kcalcalc calc = new Kcalcalc();
		// 1. calc.OneMealKcal()은 반환 값으로 double값의 한끼 권장 칼로리를 가진다.
		// 2. double값을 소수 첫째자리에서 반올림하고 int로 형변환한다.
		// 3. 반환 값은 int값의 한끼 권장 칼로리이다.
		return (int)Math.round(calc.OneMealKcal());
	}
}
