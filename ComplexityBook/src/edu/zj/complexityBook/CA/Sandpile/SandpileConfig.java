package edu.zj.complexityBook.CA.Sandpile;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;
import javafx.scene.paint.Color;

public class SandpileConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("随机数种子")
		private long randomSeed = 3L;
		@UIDesc("行数")
		private int rowCount = 20;
		@UIDesc("列数")
		private int columnCount = 20;
		@UIDesc("同步")
		private boolean synchro = true;

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

		public long getRandomSeed() {
			return randomSeed;
		}

		public void setRandomSeed(long randomSeed) {
			this.randomSeed = randomSeed;
		}

		public boolean isSynchro() {
			return synchro;
		}

		public void setSynchro(boolean synchro) {
			this.synchro = synchro;
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
	public SandpileConfig() {
		this.setModelParams(new ModelParams());
		this.setViewParams(new ViewParams());
		this.setModelClass(SandpileModel.class);
		this.setViewClasses(new Class[] { SandPileView.class, SandStateChart.class, AvalanchesByDurationChart.class,
				AvalanchesByAgentCountChart.class });
		this.setMaxSteps(5000);
	}

	public static void main(String[] args) {
		start(SandpileConfig.class);
	}

}
