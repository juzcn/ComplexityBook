package edu.zj.complexityBook.UI;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;

public abstract class AppConfig {
	private Params modelParams;
	private Params viewParams;
	private Class<? extends Model> modelClass;
	private Class<View>[] viewClasses;
	private Model model;
	private Object[] views;
	private int maxSteps = 100;
	private long intervalTime = 50L;

	public static class Params {
		private FieldInfo[] fieldsInfo = null;

		public Params() {
		}

		public void validation() {
			for (int i = 0; i < fieldsInfo.length; i++) {
				fieldsInfo[i].validation(Params.this);
			}
		}

		public FieldInfo[] getFieldsInfo() {
			if (fieldsInfo == null) {
				Field[] fields = this.getClass().getDeclaredFields();
				fieldsInfo = new FieldInfo[fields.length];
				UIDesc desc;
				Object value = null;
				for (int i = 0; i < fields.length; i++) {
					fields[i].setAccessible(true);
					try {
						value = fields[i].get(this);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					desc = fields[i].getAnnotation(UIDesc.class);
					fieldsInfo[i] = (desc == null) ? new FieldInfo(fields[i], fields[i].getName(), value)
							: new FieldInfo(fields[i], desc.value(), value);
				}
			}
			return fieldsInfo;
		}
	}

	public Params getViewParams() {
		return viewParams;
	}

	public Params getModelParams() {
		return modelParams;
	}

	public Model getModel() {
		return model;
	}

	public int getViewCount() {
		return views.length;
	}

	public View getView(int i) {
		return (View) views[i];
	}

	public AppConfig load() {
		try {
			model = (Model) modelClass.getConstructor(modelParams.getClass()).newInstance(modelParams);
			views = new Object[viewClasses.length];
			for (int i = 0; i < views.length; i++) {
				views[i] = viewClasses[i].getConstructor(modelClass, viewParams.getClass()).newInstance(model,
						viewParams);
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return this;
	}

	public static void start(Class<?> cls) {
		String[] args = new String[] { cls.getName() };
		Application.launch(UIApplication.class, args);
	}

	public Class<? extends Model> getModelClass() {
		return modelClass;
	}

	public void setModelClass(Class<? extends Model> modelClass) {
		this.modelClass = modelClass;
	}

	public Class<View>[] getViewClasses() {
		return viewClasses;
	}

	public void setViewClasses(Class<View>[] viewClasses) {
		this.viewClasses = viewClasses;
	}

	public Object[] getViews() {
		return views;
	}

	public void setViews(Object[] views) {
		this.views = views;
	}

	public void setModelParams(Params modelParams) {
		this.modelParams = modelParams;
	}

	public void setViewParams(Params viewParams) {
		this.viewParams = viewParams;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getMaxSteps() {
		return maxSteps;
	}

	public long getIntervalTime() {
		return intervalTime;
	}

	public void setMaxSteps(int maxSteps) {
		this.maxSteps = maxSteps;
	}

	public void setIntervalTime(long intervalTime) {
		this.intervalTime = intervalTime;
	}

}
