package edu.zj.complexityBook.MAS.Schelling;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;
import javafx.scene.paint.Color;

public class SchellingConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("����")
		private int rowCount = 50;
		@UIDesc("����")
		private int columnCount = 50;
		@UIDesc("��ɫ��������")
		private int redAgentCount = 1000;
		@UIDesc("��ɫ��������")
		private int blueAgentCount = 1000;
		@UIDesc("��ɫƫ�ñ�")
		private double percent = 0.5;
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
		public int getRedAgentCount() {
			return redAgentCount;
		}
		public void setRedAgentCount(int redAgentCount) {
			this.redAgentCount = redAgentCount;
		}
		public int getBlueAgentCount() {
			return blueAgentCount;
		}
		public void setBlueAgentCount(int blueAgentCount) {
			this.blueAgentCount = blueAgentCount;
		}
		public double getPercent() {
			return percent;
		}
		public void setPercent(double percent) {
			this.percent = percent;
		}

	}

	public static class ViewParams extends Params {
		@UIDesc("�����С")
		private double cellSize = 10;
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
	public SchellingConfig() {
		this.setModelParams(new ModelParams());
		this.setViewParams(new ViewParams());
		this.setModelClass(SchellingWorld.class);
		this.setViewClasses(new Class[] { SchellingView.class });
	}

	public static void main(String[] args) {
		start(SchellingConfig.class);
	}

}
