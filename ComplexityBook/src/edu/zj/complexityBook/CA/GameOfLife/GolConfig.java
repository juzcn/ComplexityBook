package edu.zj.complexityBook.CA.GameOfLife;

import java.math.BigInteger;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;
import edu.zj.utils.Grid.Model.Grid.NeighbType;
import javafx.scene.paint.Color;

public class GolConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("����XML�ļ�")
		private String fileName;
		@UIDesc("����")
		private int rowCount = 100;
		@UIDesc("����")
		private int columnCount = 100;
		@UIDesc("�ھ�����")
		private NeighbType neighbType = NeighbType.���ھ�;
		@UIDesc("����")
		private BigInteger ruleNumber = null;
		@UIDesc("���������")
		private long randomSeed = 2l;
		@UIDesc("״̬Ϊ1��������")
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
		@UIDesc("�����С")
		private double cellSize = 7;
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
