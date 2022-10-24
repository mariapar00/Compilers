import java.util.*;

public class Main {
	public static String name1(){ 
		return "John";
	}
	public static String surname(){ 
		return "Doe";
	}
	public static String fullname(String first_name, String sep, String last_name){ 
		return first_name+sep+last_name;
	}
	public static String name2(){ 
		return "John";
	}
	public static String repeat(String x){ 
		return x+x;
	}
	public static String cond_repeat(String c, String x){ 
		return ("yes".startsWith(c)?(c.startsWith("yes")?repeat(x):x):x);
	}
	public static String findLangType(String langName){ 
		return (langName.startsWith("Java")?("Java".startsWith(langName)?"Static":((new StringBuilder(langName)).reverse().toString().startsWith((new StringBuilder("script")).reverse().toString())?"Dynamic":"Unknown")):((new StringBuilder(langName)).reverse().toString().startsWith((new StringBuilder("script")).reverse().toString())?"Probably Dynamic":"Unknown"));
	}
	public static void main(String[] args) {
		System.out.println("example 1");
		System.out.println(name1());
		System.out.println(surname());
		System.out.println(fullname(name1(), " ", surname()));
		System.out.println("example 2");
		System.out.println(cond_repeat("yes", name2()));
		System.out.println(cond_repeat("no", "Jane"));
		System.out.println("example 3");
		System.out.println(findLangType("Java"));
		System.out.println(findLangType("Javascript"));
		System.out.println(findLangType("Typescript"));
	}
}
