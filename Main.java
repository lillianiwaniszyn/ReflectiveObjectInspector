package a2;

public class Main {

	public static void main(String[] args) throws Exception {
		Inspector inspect = new Inspector(); 
		ClassA a = new ClassA();
		inspect.inspect(a, true);

	}

}