package edu.zj.complexityBook.UI;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class FieldInfo {
	private final Field field;
	private final String desc;
	private final Object defaultValue;
	private final Label label;
	private final Control control;

	public FieldInfo(Field field, String desc, Object defaultValue) {
		this.field = field;
		this.desc = desc;
		this.defaultValue = defaultValue;

		label = new Label(desc);
		if (field.getType() == Enum.class || field.getType().getSuperclass() == Enum.class) {
			control = new ChoiceBox<String>();
			Object[] values = null;
			try {
				values = (Object[]) field.getType().getMethod("values").invoke(null);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			((ChoiceBox) control).getItems().addAll(values);
			((ChoiceBox) control).setValue(defaultValue);
			return;
		}
		if (field.getType() == boolean.class) {
			control = new ChoiceBox<Boolean>();
			((ChoiceBox<Boolean>) control).getItems().addAll(true, false);
			((ChoiceBox<Boolean>) control).setValue((Boolean) defaultValue);
			return;
		}
		if (field.getType() == Color.class) {
			control = new ColorPicker((Color) defaultValue);
			return;
		}
		if (defaultValue != null)
			control = new TextField(defaultValue.toString());
		else
			control = new TextField();
		((TextField) control).setPrefColumnCount(4);
	}

	public Control getControl() {
		return control;
	}

	public Field getField() {
		return field;
	}

	public String getDesc() {
		return desc;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	private <T extends AppConfig.Params> void setValue(T params, Class<?> cls, Object o) {
		String methodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
		try {
			Method m = params.getClass().getMethod(methodName, cls);
			m.invoke(params, o);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public <T extends AppConfig.Params> void validation(T params) {
		// field.setAccessible(true);

		if (field.getType() == int.class) {
			setValue(params, int.class, Integer.parseInt(((TextField) control).getText()));
			return;
		}
		if (field.getType() == long.class) {
			setValue(params, long.class, Long.parseLong(((TextField) control).getText()));
			return;
		}
		if (field.getType() == double.class) {
			setValue(params, double.class, Double.parseDouble(((TextField) control).getText()));
			return;
		}
		if (field.getType() == BigDecimal.class) {
			if (((TextField) control).getText() == null || ((TextField) control).getText().equals(""))
				setValue(params, BigDecimal.class, null);
			else
				setValue(params, BigDecimal.class, new BigDecimal(((TextField) control).getText()));
			return;
		}
		if (field.getType() == String.class) {
			setValue(params, String.class, ((TextField) control).getText());
			return;
		}
		if (field.getType() == BigInteger.class) {
			if (((TextField) control).getText() == null || ((TextField) control).getText().equals(""))
				setValue(params, BigInteger.class, null);
			else
				setValue(params, BigInteger.class, new BigInteger(((TextField) control).getText()));
			return;
		}
		if (field.getType() == Color.class) {
			setValue(params, Color.class, ((ColorPicker) control).getValue());
			return;
		}
		if (field.getType() == boolean.class) {
			setValue(params, boolean.class, ((ChoiceBox<Boolean>) control).getValue());
			return;
		}
		if (field.getType() == Enum.class || field.getType().getSuperclass() == Enum.class) {
			setValue(params, field.getType(), ((ChoiceBox) control).getValue());
			return;
		}

		throw new InvalidTypeValueException("Type invalide : " + field.getType());
	}

	public Label getLabel() {
		return label;
	}
}
