package edu.zj.test;

import java.lang.reflect.Field;

public class Test<T extends Enum<T>> {
	private T t;

	public static enum kk {
		white, black
	};

	kk a;

	public static void main(String[] args) {
		kk a = kk.white;
		if (a instanceof Enum) {
			System.out.println(a.getClass());
			System.out.println(a.getClass().getSuperclass());
			System.out.println(Enum.valueOf((Class<kk>) a.getClass(), "white"));

		}
		Test<kk> t = new Test<>();
		try {
			Field d = t.getClass().getDeclaredField("t");
			System.out.println(d.getType());
			System.out.println(d.getType().getSuperclass());
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Field d = t.getClass().getDeclaredField("a");
			System.out.println(d.getType());
			System.out.println(d.getType().getSuperclass());
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}