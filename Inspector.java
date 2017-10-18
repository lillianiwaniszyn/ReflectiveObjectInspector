package a2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
		System.out.print("The interfaces implemented by this class are: ");
		for (Class j : interfaces) {
			System.out.print(j.getName() + ",");
		}
		inspectMethod(obj);
		inspectConstructors(obj);

		
	}
	public void inspectMethod(Object obj) {
		Class classObj = obj.getClass();
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
	public void inspectConstructors(Object obj) {
		Class classObj = obj.getClass();
		System.out.println("\n\nThe constructors in this class are:");
		Constructor[] constructors = classObj.getConstructors();
		for (Constructor x : constructors) {
			System.out.println();
			System.out.println(x.getName());
			//get parameter types
			Class [] parameterTypes = x.getParameterTypes();
			//get modifiers
			int modifiers = x.getModifiers();
			String sModifiers = Modifier.toString(modifiers);
			System.out.print("Parameter types: ");
			for (Class c : parameterTypes) {
				System.out.print(c.getName());
			}
			System.out.print(" Modifiers: " +  sModifiers);
			
		}
		
	}

}
