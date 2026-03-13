import java.util.TreeMap;
import java.util.Enumeration;
import java.util.Vector;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

class IncomeGraph
{
	public void view() throws Exception
	{
		String appDir = System.getProperty("user.dir");
		File file1 = new File(appDir,"\\IngrInfo\\0.ser");
		
		FileInputStream fis1 = new FileInputStream(file1);
		ObjectInputStream ois1 = new ObjectInputStream(fis1);
		
		int obj1 = (int)ois1.readObject();
		
		ois1.close();
		fis1.close();

		File file2 = new File(appDir,"\\IngrInfo\\1.ser");
		
		FileInputStream fis2 = new FileInputStream(file2);
		ObjectInputStream ois2 = new ObjectInputStream(fis2);
		
		int obj2 = (int)ois2.readObject();

		ois2.close();
		fis2.close();
		
		File file3 = new File(appDir,"\\IngrInfo\\2.ser");
		
		FileInputStream fis3 = new FileInputStream(file3);
		ObjectInputStream ois3 = new ObjectInputStream(fis3);
		
		int obj3 = (int)ois3.readObject();

		ois3.close();
		fis3.close();
		
		File file4 = new File(appDir,"\\IngrInfo\\3.ser");
		
		FileInputStream fis4 = new FileInputStream(file4);
		ObjectInputStream ois4 = new ObjectInputStream(fis4);
		
		int obj4 = (int)ois4.readObject();

		ois4.close();
		fis4.close();
		
		File file5 = new File(appDir,"\\IngrInfo\\4.ser");
		
		FileInputStream fis5 = new FileInputStream(file5);
		ObjectInputStream ois5 = new ObjectInputStream(fis5);
		
		int obj5 = (int)ois5.readObject();
		
		ois5.close();
		fis5.close();
		
		File file6 = new File(appDir,"\\IngrInfo\\5.ser");
		
		FileInputStream fis6 = new FileInputStream(file6);
		ObjectInputStream ois6 = new ObjectInputStream(fis6);
		
		int obj6 = (int)ois6.readObject();
		
		ois6.close();
		fis6.close();

		
		Vector<String> graphX = new Vector<String>();
		Vector<Integer> graphY = new Vector<Integer>();
		
		graphX.add("11-14"); 
		graphY.add(obj1/5000);
		graphX.add("11-15"); 
		graphY.add(obj2/5000);
		graphX.add("11-16"); 
		graphY.add(obj3/5000);
		graphX.add("11-17"); 
		graphY.add(obj4/5000);
		graphX.add("11-18"); 
		graphY.add(obj5/5000);
		
		System.out.println();
		System.out.println("      \t    ※  포케 자판기 [포케 포케] 일 수입 그래프");
		System.out.println();
		System.out.println("  [ X축 요소 : 2024 - 월 - 일 ] [ Y축 요소 : 수입액 (단위 천원) ]");
		System.out.println();

		int yAxis = 50000;
		// y축 그리기
		for (int i = 9 ; i >= 0 ; i-- )
		{
			System.out.printf("%6d",yAxis);
			yAxis-=5000;
			for (int j = 0 ; j < graphY.size() ; j++ )
			{
				if (graphY.get(j)-i > 0)
				{
					System.out.print("\t  ■");
				}
				else if(graphY.get(j)-i<=0)
				{	
					System.out.print("\t    ");
				}
			}
			System.out.println();
		}
		
		// x축 그리기
		for (int i = 0 ; i < graphX.size() ; i++ )
		{
			System.out.print("\t" + graphX.get(i) );
		}
		System.out.println();
		
		System.out.println();
		System.out.println("\t=================================================");
		
	}
}