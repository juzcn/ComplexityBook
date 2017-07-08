package edu.zj.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericClass<T,S>
{
    private Class<T> realType;

    public GenericClass() {
        findTypeArguments(getClass());
    }

    private void findTypeArguments(Type t) {
    	if (t==null) return;
        if (t instanceof ParameterizedType) {
            Type[] typeArgs = ((ParameterizedType) t).getActualTypeArguments();
            realType = (Class<T>) typeArgs[0];
        	System.out.println("Type = "+typeArgs[1]);
        } else {
            Class c = (Class) t;
            findTypeArguments(c.getGenericSuperclass());
        }
    }

    public Type getMyType()
    {
        // How do I return the type of T? (your question)
        return realType;
    }
    public static class MyClass extends GenericClass<String,Integer> {
    	
    }
    public static void main(String[] args) {
    	MyClass test=new MyClass();
    	System.out.println("Type = "+test.getMyType());
    }
}