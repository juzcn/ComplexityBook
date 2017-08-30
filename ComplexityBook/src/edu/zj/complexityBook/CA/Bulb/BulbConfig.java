package edu.zj.complexityBook.CA.Bulb;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;
import javafx.scene.paint.Color;

public class BulbConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("���������")
		private long randomSeed = 3L;
		@UIDesc("����")
		private int rowCount = 20;
		@UIDesc("����")
		private int columnCount = 20;
		@UIDesc("������")
		private int R = 4;
		public long getRandomSeed() {
			return randomSeed;
		}
		public void setRandomSeed(long randomSeed) {
			this.randomSeed = randomSeed;
		}
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
		public int getR() {
			return R;
		}
		public void setR(int r) {
			R = r;
		}
	}

	public static class ViewParams extends Params {
		@UIDesc("�����С")
		private double cellSize = 20;
		@UIDesc("�߿���ɫ")
		private Color borderColor = Color.BLACK;

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
	public BulbConfig() {
		this.setModelParams(new ModelParams());
		this.setViewParams(new ViewParams());
		this.setModelClass(BulbModel.class);
		this.setViewClasses(new Class[] { BulbView.class });
		this.setMaxSteps(5000);
	}

	public static void main(String[] args) {
		start(BulbConfig.class);
	}

}
