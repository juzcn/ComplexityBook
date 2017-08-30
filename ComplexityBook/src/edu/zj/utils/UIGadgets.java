package edu.zj.utils;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

public class UIGadgets {
	public static class IntegerStringConverter extends StringConverter<Number>{

	    public IntegerStringConverter() {
	    }

	    @Override
	    public String toString(Number object) {
	        if(object.intValue()!=object.doubleValue())
	            return "";
	        return ""+(object.intValue());
	    }

	    @Override
	    public Number fromString(String string) {
	        Number val = Double.parseDouble(string);
	        return val.intValue();
	    }
	}  

	public static Image image(String path) {
		try {
			return new Image(new File(path).toURI().toURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Button imageButton(String text,String imgaePath,double fitWitdth,double fitHeight) {
		ImageView icon = new ImageView(image(imgaePath));
		icon.setFitHeight(fitHeight);
		icon.setFitWidth(fitWitdth);
		return new Button(text, icon);
	}

}
