import java.io.File;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

class ArrayMenuInfo
{	
	private String appDir = System.getProperty("user.dir");
	private File file;
	private ArrayList<String> base;
	
	ArrayMenuInfo(String num) throws Exception
	{
		file = new File(appDir,"\\IngrInfo\\base" + num +".ser");
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		base = (ArrayList<String>)ois.readObject();
		ois.close();
		fis.close();
	}

	// 베이스 정보 받아오기
    public ArrayList<String> getBase() throws Exception
	{
        return base;
    }
	
	// 각 베이스에 집어넣기
	public void addBase(String menuName)  throws Exception
	{
		base.add(menuName);
	}
	
	// 각 베이스에서 제거하기
	public void removeBase(String menuName)  throws Exception
	{
		base.remove(menuName);
	}
}