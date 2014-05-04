package JVM;

import static java.lang.Math.*;

// JVM NIO
public class notfoundexception {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			// JVMœ‘ æº”‘ÿ
			Class.forName("HuaWei.Test1");
			assert(true);
			//Class.forName("notFoundClass");
			
			// assertion 
			int i = 1;			
			assert(i<0)?false:true;
			System.out.println("aa");
			
			// import static
			System.out.println(sqrt(4));
			
			System.out.println(hash(667));
			
		}catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
	}

	static int hash(int h) {  
	    h ^= (h >>> 20) ^ (h >>> 12);  
	    return h ^ (h >>> 7) ^ (h >>> 4);  
	}  
}
