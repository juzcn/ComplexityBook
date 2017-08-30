package edu.zj.complexityBook.CA.ForestFire;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;
import javafx.scene.paint.Color;

public class ForestFireConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("行数")
		private int rowCount = 75;
		@UIDesc("列数")
		private int columnCount = 75;
		@UIDesc("初始树密度")
		double initialDensityField = 0.7;
		@UIDesc("初始着火点")
		double initialOnFireField = 0.01;
		@UIDesc("新长树")
		double growthField = 0.0;
		@UIDesc("焦地新长树")
		double growthOnBurnedField = 0.0;
		@UIDesc("着火")
		double onFireField = 0.0;

		public int getRowCount() {
			return rowCount;
		}

		public void setRowCount(int rowCount) {
			this.rowCount = rowCount;
		}

		public int getColumnCount() {
			return columnCount;
		}

		public void setColumnCount(int columnCount) {
			this.columnCount = columnCount;
		}

		public double getInitialDensityField() {
			return initialDensityField;
		}

		public void setInitialDensityField(double initialDensityField) {
			this.initialDensityField = initialDensityField;
		}

		public double getInitialOnFireField() {
			return initialOnFireField;
		}

		public void setInitialOnFireField(double initialOnFireField) {
			this.initialOnFireField = initialOnFireField;
		}

		public double getGrowthField() {
			return growthField;
		}

		public void setGrowthField(double growthField) {
			this.growthField = growthField;
		}

		public double getGrowthOnBurnedField() {
			return growthOnBurnedField;
		}

		public void setGrowthOnBurnedField(double growthOnBurnedField) {
			this.growthOnBurnedField = growthOnBurnedField;
		}

		public double getOnFireField() {
			return onFireField;
		}

		public void setOnFireField(double onFireField) {
			this.onFireField = onFireField;
		}
	}

	public static class ViewParams extends Params {
		@UIDesc("网格大小")
		private double cellSize = 15;
		@UIDesc("边框颜色")
		private Color borderColor = null;
		public double getCellSize() {
			return cellSize;
		}
		public void setCellSize(double cellSize) {
			this.cellSize = cellSize;
		}
		public Color getBorderColor() {
			return borderColor;
		}
		public void setBorderColor(Color borderColor) {
			this.borderColor = borderColor;
		}
	}

	@SuppressWarnings({ "unchecked" })
	public ForestFireConfig() {
		this.setModelParams(new ModelParams());
		this.setViewParams(new ViewParams());
		this.setModelClass(ForestFireModel.class);
		this.setViewClasses(new Class[] { ForestFireView.class });
	}

	public static void main(String[] args) {
		start(ForestFireConfig.class);
	}

}
