package a2;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

public class InspectorTest {
    Object classDObj;
    Object classAObj;
    Class testClass;
    
    public InspectorTest()
    {
            classDObj = new ClassD();
            classAObj = new ClassA();
    }
	@Test
	public void testInspectMethods() {
		Object classDObj = new ClassD();
		Object classAObj = new ClassA();
		Method[] methodsD = classDObj.getClass().getDeclaredMethods();
		Method[] methodsA = classAObj.getClass().getDeclaredMethods();
        int resultD = methodsD.length;
        int resultA = methodsA.length;
        assertEquals( resultD, 2); // check to see if we have 2 declared methods in Class D
        assertEquals( resultA, 5); // check to see if we have 5 declared methods in Class A
        String methodName = methodsD[0].getName();
        assertEquals(methodName,"toString"); //check to see if first method in Class D is toString
        resultD = methodsD[0].getModifiers();
        assertEquals(resultD,1); // check to see if method is public
        methodName = methodsD[0].getReturnType().getSimpleName();
        assertEquals(methodName,"String"); // check to see if method returns String
		Class[] parameters = methodsD[0].getParameterTypes();
		String params = "";
		if (parameters.length == 0) //if method has zero params, set to none
			params = "none";
		else
			for (Class x : parameters) {
				params += x.getSimpleName() + " "; // else get the name
			}
        assertEquals(params,"none"); // check to see if method has no parameters
		Class[] exceptions = methodsD[0].getExceptionTypes();
		String exception = "";
		if (exceptions.length == 0)
			exception = "none";
		else
			for (Class aException : exceptions) {
				exception += aException.getSimpleName() + " ";
			}
		assertEquals(exception,"none"); //check to see that we have no exceptions 
	}

}
