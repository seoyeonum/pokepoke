import java.util.Scanner;

// 올바른 int값을 입력받기 위한 클래스
// 1. stringToint(String convert) 
//    : 사용자한테 입력을 받을 때 문자열형태로 받는다. 해당 메소드의 매개변수로 입력받은 문자열을 넣는다.
//      입력받은 String을 int로 변환이 가능하면 올바른 숫자를 입력받은 것이라 true를 반환
//      변환이 불가능하면 false를 반환한다.

// 2. correctValue()
//    : 입력받은 값이 int로 변환이 불가능한 String값이면 변환가능한 값을 입력받을 때까지 입력을 반복한다.

// 3. correctSelect(int start, int end, String stringToint)
//    : 입력받게되는 int의 범위가 정해져있을 때 사용하는 메소드다.
//      예를 들어 선택지가 1번부터 10까지면 매개변수 start에는 1, end에는 10을 넘겨준다.
//      String 매개변수는 사용자에게 입력받은 값이다. 입력받은 값이 int로 변환이 가능하며 범위에 속하 때까지 반복해서 입력받는다.

class CorrectMenuValue
{
	// 변환 가능 여부 확인하는 메소드
	// 1. 매개변수로 받은 String 값을 int로 변환을 시도하고 NumberFormatException가 발생하면 
	//    변환 불가능이기에 false를 반환한다.
	// 2. NumberFormatException가 발생안하면 변환 가능이기에 true를 반환한다.
	public static boolean stringToint(String convert)
	{
		try
		{
			Integer.parseInt(convert);		
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		
		return true;
	}
	
	// 올바른 값을 입력받는 메소드
	// 1. 입력받은 값이 int로 변환이 불가능한 String값이면 변환가능한 값을 입력받을 때까지 입력을 반복한다.
	// 2. 변환 가능 여부는 stringToint(String convert)를 통해서 검사한다.
	// 3. 반환 값으로 int로 변환가능한 String 값이다. 
	public static String correctValue()
	{
		Scanner sc = new Scanner(System.in);
		String stemp = "";
		do
		{
			System.out.println();
			System.out.print("\t올바른 값을 입력해주세요 : ");
			stemp = sc.nextLine();
		}
		while (!stringToint(stemp));
		
		return stemp;
	}
	
	// 올바른 선택 범위로 값 입력받는 메소드
	// 1. 매개변수로 건네 받은 String의 입력 값이 int로 변환이 가능한지 stringToint(String convert)로 검사한다.
	// 2. 불가능이면 correctValue()를 통해서 int 변환 가능한 String 값으로 입력받는다.
	// 3. 1번에서 변환 가능한 String값이면 int로 변환했을 때 설정 범위에 속하는 지의 유무를 확인한다.
	// 4. 속하면 해당 String값을 int로 변환하여 사용자 정보에 초기화한다.
	// 5. 속하지 않는다면 int로 변환가능하면서 설정 범위에 속하는 String값을 입력받을 때까지 반복해서 입력받는다.
	public static int correctSelect(int start, int end, String stringToint)
	{
		Scanner sc = new Scanner(System.in);
		String target = "";
		
		if(!stringToint(stringToint))
			target = correctValue();
		else
			target = stringToint;

		while (start > Integer.parseInt(target) || end < Integer.parseInt(target))
		{
			do
			{
				System.out.println();
				System.out.print("\t올바른 값을 입력해주세요 : ");
				target = sc.nextLine();
			}
			while (!stringToint(target));
		}
		
		return Integer.parseInt(target);
	}
}