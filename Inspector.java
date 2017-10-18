package a2;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Inspector {
	public void inspect(Object obj, boolean recursive) {
		//name of declaring class
		Class classObj = obj.getClass();
		String declaringClass = obj.getClass().getSimpleName();
		System.out.print("The name of the declaring class is: ");
		System.out.println(declaringClass);
		//name of immediate superclass
		String immediateSuperClass = obj.getClass().getSuperclass().getSimpleName();
		System.out.print("The name of the immediate superclass is: ");
		System.out.println(immediateSuperClass);
		//name of interfaces implemented
		Class[] interfaces = classObj.getInterfaces();
		System.out.println("Interfaces = " + Arrays.asList(interfaces));
		//name of methods implemented
		Method[] methods = classObj.getMethods();
		for (Method i : methods) {
			System.out.println();
			//get exception types
			Class [] exception = i.getExceptionTypes();	
			//get parameter types
			Class[] parameterTypes = i.getParameterTypes();
			//get return type
			Class returnType = i.getReturnType();
			//get modifiers
			int modifiers = i.getModifiers();
			String sModifiers = Modifier.toString(modifiers);
			System.out.print("Method: ");
			System.out.print(i.getName());
			//loop through exceptions
			for (Class j : exception) {
				System.out.print(", Exception Name: ");
				System.out.print(j.getName());
			}
			System.out.print(", Paramter Types: ");
			for (Class j : parameterTypes) {
				System.out.print(j.getName() + ",");
			}
			System.out.print(" Return Type: " +  returnType.getName());
			System.out.print(" Modifiers: " +  sModifiers);
			

		}
		
	}

}
