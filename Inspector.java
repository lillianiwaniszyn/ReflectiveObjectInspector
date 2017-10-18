package a2;

import java.util.Arrays;

public class Inspector {
	public void inspect(Object obj, boolean recursive) {
		//name of declaring class
		Class aClass = obj.getClass();
		String declaringClass = obj.getClass().getSimpleName();
		System.out.print("The name of the declaring class is: ");
		System.out.println(declaringClass);
		//name of immediate superclass
		String immediateSuperClass = obj.getClass().getSuperclass().getSimpleName();
		System.out.print("The name of the immediate superclass is: ");
		System.out.println(immediateSuperClass);
		//name of interfaces implemented
		Class[] interfaces = aClass.getInterfaces();
		System.out.println("Interfaces = " + Arrays.asList(interfaces));
	}

}
