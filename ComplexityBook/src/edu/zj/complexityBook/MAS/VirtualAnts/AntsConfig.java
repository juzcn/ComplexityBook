package edu.zj.complexityBook.MAS.VirtualAnts;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;
import javafx.scene.paint.Color;

public class AntsConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("����")
		private int rowCount = 50;
		@UIDesc("����")
		private int columnCount = 50;
		@UIDesc("��������")
		private int agentCount = 1;
		@UIDesc("���ִ��")
		private boolean shuffle = true;
		@UIDesc("�����ʼ״̬")
		private int initial = 0;
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
		public boolean isShuffle() {
			return shuffle;
		}
		public void setShuffle(boolean shuffle) {
			this.shuffle = shuffle;
		}
		public int getInitial() {
			return initial;
		}
		public void setInitial(int initial) {
			this.initial = initial;
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
	public AntsConfig() {
		this.setModelParams(new ModelParams());
		this.setViewParams(new ViewParams());
		this.setModelClass(AntWorld.class);
		this.setViewClasses(new Class[] { AntsView.class });
	}

	public static void main(String[] args) {
		start(AntsConfig.class);
	}

}
