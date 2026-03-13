import java.util.Vector;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Enumeration;


public class Tool
{
	// 메뉴 관련 정보(품목명, 칼로리, 가격)
	protected Hashtable<String, ArrayList<Integer>> foodInfo;
	
	// 사용 가능한 메뉴명(품절X)
	protected Vector<String> possibleMenu = new Vector<String>();
	
	// 정렬된 메뉴 리스트(메뉴판)
	protected ArrayList<String> base1 = new ArrayList<String>();
	protected ArrayList<String> base2 = new ArrayList<String>();
	protected ArrayList<String> base3 = new ArrayList<String>();
	protected ArrayList<String> base4 = new ArrayList<String>();
	
	public void setPossibleMenu() throws Exception
	{
		Manage mg = new Manage();
		this.possibleMenu = mg.getPossibleMenu();
	}
	
	public void setBases() throws Exception
	{
		ArrayMenuInfo arrMenu1 = new ArrayMenuInfo("1");
		ArrayMenuInfo arrMenu2 = new ArrayMenuInfo("2");
		ArrayMenuInfo arrMenu3 = new ArrayMenuInfo("3");
		ArrayMenuInfo arrMenu4 = new ArrayMenuInfo("4");
		
		base1 = arrMenu1.getBase();
		base2 = arrMenu2.getBase();
		base3 = arrMenu3.getBase();
		base4 = arrMenu4.getBase();		
	}
	
	public void setFile() throws Exception
	{
		Manage mg = new Manage();
		
		Hashtable<Object, Object> foodinfo0 = mg.getMenuInfo();
		String key;
		ArrayList<Integer> value;
		Hashtable<String, ArrayList<Integer>> foodinfo = new Hashtable<String, ArrayList<Integer>>();
		
		//이누머레이션에 담기
		Enumeration en = foodinfo0.keys();
		
		while (en.hasMoreElements())
		{
			key = (String)en.nextElement();
			value = (ArrayList<Integer>)foodinfo0.get(key);
			foodinfo.put(key, value);
		}
		
		this.foodInfo = foodinfo;
	}

}