package a2;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Vector;

public class Inspector {
	public void inspect(Object obj, boolean recursive) throws IllegalArgumentException, IllegalAccessException {
		//name of declaring class
		Class classObj = obj.getClass();
		Vector objects = new Vector();
		String declaringClass = obj.getClass().getSimpleName();
		System.out.print("The name of the declaring class is: ");
		System.out.println(declaringClass);
		//name of immediate superclass
		String immediateSuperClass = obj.getClass().getSuperclass().getSimpleName();
		System.out.print("The name of the immediate superclass is: ");
		System.out.println(immediateSuperClass);
		//name of interfaces implemented

		inspectInterfaces(obj, classObj);
		inspectMethods(obj, classObj);
		inspectConstructors(obj, classObj);
		if(recursive == false) {
			inspectFields(obj, false);
		}
		else if(recursive == true) {
			inspectFields(obj, true);
		}


		
	}
	public void inspectInterfaces(Object obj, Class classObj) {
		Class[] interfaces = classObj.getInterfaces();
		if (interfaces.length > 0) {
			System.out.print("The interfaces implemented by this class are: ");
			for (Class j : interfaces) {
				System.out.print(j.getName() + ",");
				//now we need to inspect the interfaces
				System.out.println("Inspecting " + j.getSimpleName());
				inspectMethods(obj, j);
				inspectConstructors(obj, j);
			}
		}
		else {
			System.out.println("No interfaces found");
		}
		
	}

	public void inspectMethods(Object obj, Class classObj) {
		//name of methods implemented
		Method[] methods = classObj.getDeclaredMethods();
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
	public void inspectConstructors(Object obj, Class classObj) {
		System.out.println("\n\nThe constructors in this class are:");
		Constructor[] constructors = classObj.getDeclaredConstructors();
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
	public void inspectFields(Object obj, Boolean recursive) throws IllegalArgumentException, IllegalAccessException {
		Class classObj = obj.getClass();
		System.out.println("\n\nThe fields in this class are:");
		System.out.println();
		Field[] fields = classObj.getDeclaredFields();
		for (Field x : fields) {
			System.out.println();
			System.out.print(x.getName());
			int modifiers = x.getModifiers();
			Type type = x.getGenericType();
			String sModifiers = Modifier.toString(modifiers);
			System.out.print(", " + type.getTypeName());
			System.out.print(", " + sModifiers); // were good up to here
			if (x.getType().isPrimitive()){
				System.out.println(", " + x.getType());
			}
			else if(recursive = true) {
				System.out.println("________________________________");
				System.out.println(x.getType()); // now we need to inspect this class
				
			}
			else {
				fieldNonPrimitive(x, obj);
			}
			
		
		}
		
		
		
	}
	public void fieldNonPrimitive(Field fields, Object obj) throws IllegalArgumentException, IllegalAccessException {
		if (fields.getType().isArray()) {
			fields.setAccessible(true);
			System.out.println(" ...This field is an array");
			System.out.println("Array length is:" + Array.getLength(fields.get(obj)));
			//to do
			// iterate through the array and print
		}
		
		
	}
	private void inspectFieldsRecursive(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Class classObj = obj.getClass();
		System.out.println("\n\nThe fields in this class are:");
		System.out.println();
		Field[] fields = classObj.getDeclaredFields();
		for (Field x : fields) {
			System.out.println();
			System.out.print(x.getName());
			int modifiers = x.getModifiers();
			Type type = x.getGenericType();
			String sModifiers = Modifier.toString(modifiers);
			System.out.print(", " + type.getTypeName());
			System.out.print(", " + sModifiers);
			if (x.getType().isPrimitive()){
				System.out.println(", " + x.getType());
			}
			else {
				inspectFieldsRecursive(x);
			}
			
		
		}
		
	}

}
