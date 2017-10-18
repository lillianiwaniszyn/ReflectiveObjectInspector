package a2;

public class Inspector {
	public void inspect(Object obj, boolean recursive) {
		//name of declaring class
		String declaringClass = obj.getClass().getSimpleName();
		System.out.print("The name of the declaring class is: ");
		System.out.println(declaringClass);
		
	}

}
