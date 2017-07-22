package practice;

public class PrefixToPost {
	
	public static void main(String args[])
	{
		String string1 = "+1*23";
		String s2 = pre2post(string1);
		System.out.print(s2);
		
	}
	public static String pre2post(String pre){
	    if(pre.length() <= 1){
	        return pre;
	    }

	    if(!Character.isLetter(pre.charAt(0))){
	        String a = pre2post(pre.substring(1)) + pre.charAt(0);
	        String b = pre2post(pre.substring(a.length()));
	        return a + b;
	    }else if(!Character.isLetter(pre.charAt(1))){
	        return pre.substring(0,1);
	    }else{
	        return pre.substring(0,2);
	    }

	}
	
	
}
