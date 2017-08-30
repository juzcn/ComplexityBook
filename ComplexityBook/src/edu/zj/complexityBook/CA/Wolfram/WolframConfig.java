package edu.zj.complexityBook.CA.Wolfram;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;
import javafx.scene.paint.Color;

public class WolframConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("����")
		private int rowCount = 1;
		@UIDesc("����")
		private int columnCount = 150;
		@UIDesc("����")
		private int ruleNumber = 110;
		@UIDesc("���")
		private boolean rand = true;
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
		public int getRuleNumber() {
			return ruleNumber;
		}
		public void setRuleNumber(int ruleNumber) {
			this.ruleNumber = ruleNumber;
		}
		public boolean isRand() {
			return rand;
		}
		public void setRand(boolean rand) {
			this.rand = rand;
		}
	}

	public static class ViewParams extends Params {
		@UIDesc("�����С")
		private double cellSize = 5;
		@UIDesc("�߿���ɫ")
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
	public WolframConfig() {
		this.setModelParams(new ModelParams());
		this.setViewParams(new ViewParams());
		this.setModelClass(WolframModel.class);
		this.setViewClasses(new Class[] { WolframView.class });
	}

	public static void main(String[] args) {
		start(WolframConfig.class);
	}

}
