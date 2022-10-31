
public class Check {

	public static void main(String[] args) {
		String str = "$123, $1234";
		String[] tmp =str.substring(1).split(",");
		String s = str.substring(1);
		int i = Integer.parseInt(String.join("", tmp));
//System.out.println(s);
System.out.println(i);
	}
	
}
