import java.util.Hashtable;
import java.io.File;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

// Hashtable<T> 타입을 직렬화, 역직렬화 하는 클래스 
// 1. 사용자 정의 생성자 UseToHashTableFromFile(String directory, String fileName)
//    : 매개변수로 디렉토리, 파일 이름을 받아서 file 객체를 구성
// 2. 직렬화 메소드 serial(Hashtable<Object, Object> obj)
//    : 객체를 직렬화해 파일로 내보낸다.
// 3. 역직렬화 메소드 deserial()
//    : 반환 값은 Hashtable<Object, Object>이다. 파일을 역직렬화해 객체를 반환한다.
class UseToHashTableFromFile
{
	private String appDir = System.getProperty("user.dir");
	private File file;
	private Hashtable<Object, Object> objHash;

	// 사용자 정의 생성자이다. 
	// 1. 매개변수로 외부 파일이 저장된 디렉토리의 이름과 외부파일의 이름을 받는다. 
	// 2. 매개변수를 통해서 전역변수로 선언된 file에 객체를 구성한다.
	UseToHashTableFromFile(String directory, String fileName)
	{
		file = new File(appDir,"\\" + directory + "\\" + fileName + ".ser");
	}
	
	// 매개변수로 받은 객체를 직렬화해서 파일로 내보내는 메소드
	// 예시)
	// 1. 인스턴스 생성 UseToHashTableFromFile htff = new UseToHashTableFromFile("admin","admin")
	// 2. 직렬화하고자하는 객체를 생성 Hashtable<Object, Object> h2 = new Hashtable<Object, Object>()
	// 3. h2.put("admin",1234) key(String)와 value(int)는 put하는 과정에서 Object로 업캐스팅이 진행된다.
	//    (Object의 자식클래스면 key와 value값의 타입은 상관없다 넣는 과정에서 업캐스팅한다.)
	// 4. htff.serial(h2)로 직렬화한다.
	public void serial(Hashtable<Object, Object> obj) throws Exception
	{
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(obj);
		oos.close();
		fos.close();
	}
	
	// 파일을 역직렬화해서 객체를 불러오는 메소드
	// 1. 반환 값은 Hashtable<Object, Object>이다.
	// 2. 사용 시는 다운캐스팅을 진행해 사용한다.
	// 예시) String key, int value 직렬화 시 넣었던 타입으로 다운 캐스팅해서 받아서 사용한다.   
	public Hashtable<Object, Object> deserial() throws Exception
	{
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Hashtable<Object, Object> obj = (Hashtable<Object, Object>)ois.readObject();
		ois.close();
		fis.close();
		
		return obj;
	}
	
}