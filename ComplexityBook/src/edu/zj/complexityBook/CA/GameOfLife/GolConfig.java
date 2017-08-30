package edu.zj.complexityBook.CA.GameOfLife;

import java.math.BigInteger;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;
import edu.zj.utils.Grid.Model.Grid.NeighbType;
import javafx.scene.paint.Color;

public class GolConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("网格XML文件")
		private String fileName;
		@UIDesc("行数")
		private int rowCount = 100;
		@UIDesc("列数")
		private int columnCount = 100;
		@UIDesc("邻居特性")
		private NeighbType neighbType = NeighbType.八邻居;
		@UIDesc("规则")
		private BigInteger ruleNumber = null;
		@UIDesc("随机数种子")
		private long randomSeed = 2l;
		@UIDesc("状态为1的网格数")
		private int liveCellAccount = (int) (rowCount*columnCount*0.05);
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
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
		public int getLiveCellAccount() {
			return liveCellAccount;
		}
		public void setLiveCellAccount(int liveCellAccount) {
			this.liveCellAccount = liveCellAccount;
		}
		public NeighbType getNeighbType() {
			return neighbType;
		}
		public void setNeighbType(NeighbType neighbType) {
			this.neighbType = neighbType;
		}
		public BigInteger getRuleNumber() {
			return ruleNumber;
		}
		public void setRuleNumber(BigInteger ruleNumber) {
			this.ruleNumber = ruleNumber;
		}
		public long getRandomSeed() {
			return randomSeed;
		}
		public void setRandomSeed(long randomSeed) {
			this.randomSeed = randomSeed;
		}
	}

	public static class ViewParams extends Params {
		@UIDesc("网格大小")
		private double cellSize = 7;
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
	public GolConfig() {
		this.setModelParams(new ModelParams());
		this.setViewParams(new ViewParams());
		this.setModelClass(GolModel.class);
		this.setViewClasses(new Class[] { GolView.class });
		
	}

	public static void main(String[] args) {
		start(GolConfig.class);
	}

}
