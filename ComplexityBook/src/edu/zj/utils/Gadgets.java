package edu.zj.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.InvalidTypeValueException;
import javafx.scene.paint.Color;

public class Gadgets {
	public static <T extends Serializable> T deepClone(T o) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		} catch (IOException e ) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <V> V valueOf(Class<V> cls, String str) {
		if (cls == Integer.class) {
			return (V) (Integer.valueOf(str));
		}
		if (cls == Double.class) {
			return (V) (Double.valueOf(str));
		}
		if (cls == Long.class) {
			return (V) (Long.valueOf(str));
		}
		if (cls == String.class) {
			return (V) str;
		}
		if (cls == Color.class) {
			if (str == null || str.equals(""))
				return null;
			else
				return (V) Color.valueOf(str);
		}
		if (cls == Boolean.class) {
			Boolean.valueOf(str);
		}
		if (cls == Enum.class || cls.getSuperclass() == Enum.class) {
			return (V) Enum.valueOf((Class<? extends Enum>) cls, str);
		}
		throw new InvalidTypeValueException("Type invalide : " + cls);
	}

	public static long getSeed(Random random) {
		byte[] ba0, ba1, bar;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(128);
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(new Random(0));
			ba0 = baos.toByteArray();
			baos = new ByteArrayOutputStream(128);
			oos = new ObjectOutputStream(baos);
			oos.writeObject(new Random(-1));
			ba1 = baos.toByteArray();
			baos = new ByteArrayOutputStream(128);
			oos = new ObjectOutputStream(baos);
			oos.writeObject(random);
			bar = baos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException("IOException: " + e);
		}
		if (ba0.length != ba1.length || ba0.length != bar.length)
			throw new RuntimeException("bad serialized length");
		int i = 0;
		while (i < ba0.length && ba0[i] == ba1[i]) {
			i++;
		}
		int j = ba0.length;
		while (j > 0 && ba0[j - 1] == ba1[j - 1]) {
			j--;
		}
		if (j - i != 6)
			throw new RuntimeException("6 differing bytes not found");
		// The constant 0x5DEECE66DL is from
		// http://download.oracle.com/javase/6/docs/api/java/util/Random.html .
		return ((bar[i] & 255L) << 40 | (bar[i + 1] & 255L) << 32 | (bar[i + 2] & 255L) << 24
				| (bar[i + 3] & 255L) << 16 | (bar[i + 4] & 255L) << 8 | (bar[i + 5] & 255L)) ^ 0x5DEECE66DL;
	}

	public final static void setBeanValue(Object bean, Field field, Object value) {
		String methodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
		try {
			Method m = bean.getClass().getMethod(methodName, value.getClass());
			m.invoke(bean, value);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public final static Object getBeanValue(Object bean, Field field) {
		String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
		try {
			Method m = bean.getClass().getMethod(methodName);
			return m.invoke(bean);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
