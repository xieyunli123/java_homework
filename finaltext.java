package homework;

public class finaltext {
	public static void main(String[] args) throws Exception {
	       String path = "D:\\A��ѧλ�γ�\\Java\\��ҵ\\test1.txt";
	       String hashpath =  "D:\\A��ѧλ�γ�\\Java\\��ҵ\\hash";
	       KeyValue test = new KeyValue(path,hashpath);
	       
	       String key = test.setKeyValue(path);
	       String value = test.getValue(key);
	       
	       System.out.println("key: "+key);
	       System.out.println("value: "+value);
	    }
}
