package edu.zj.complexityBook.MAS.Termites;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;
import javafx.scene.paint.Color;

public class TermitesConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("����")
		private int rowCount = 100;
		@UIDesc("����")
		private int columnCount = 150;
		@UIDesc("��������")
		private int agentCount = 100;
		@UIDesc("ľм����")
		private double chipPercent = 0.5;
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
		public int getAgentCount() {
			return agentCount;
		}
		public void setAgentCount(int agentCount) {
			this.agentCount = agentCount;
		}
		public double getChipPercent() {
			return chipPercent;
		}
		public void setChipPercent(double chipPercent) {
			this.chipPercent = chipPercent;
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
	public TermitesConfig() {
		this.setModelParams(new ModelParams());
		this.setViewParams(new ViewParams());
		this.setModelClass(TermitesWorld.class);
		this.setViewClasses(new Class[] { TermitesView.class });
	}

	public static void main(String[] args) {
		start(TermitesConfig.class);
	}

}
