package edu.zj.complexityBook.MAS.Sugarscape;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;
import javafx.scene.paint.Color;

public class SugarscapeConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("��������")
		private int agentCount = 400;
		@UIDesc("���ִ��")
		private boolean shuffle = true;
		@UIDesc("�������")
		private long randomSeed = 100L;
		@UIDesc("�����ͼ�ļ�")
		private String mapFileName = "sugar-map.txt";
		@UIDesc("���������")
		private int maximum_metabolic_rate = 4;
		@UIDesc("������߾���")
		private int maximum_vision = 6;
		@UIDesc("��С��������")
		private int minimum_death_age = 1000;
		@UIDesc("�����������")
		private int maximum_death_age = 1000;
		@UIDesc("��С�Ƹ�ֵ")
		private int minimum_wealth = 5;
		@UIDesc("���Ƹ�ֵ")
		private int maximum_wealth = 25;
		@UIDesc("����������")
		private int growthRate = 4;
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
		public String getMapFileName() {
			return mapFileName;
		}
		public void setMapFileName(String mapFileName) {
			this.mapFileName = mapFileName;
		}
		public int getMaximum_metabolic_rate() {
			return maximum_metabolic_rate;
		}
		public void setMaximum_metabolic_rate(int maximum_metabolic_rate) {
			this.maximum_metabolic_rate = maximum_metabolic_rate;
		}
		public int getMaximum_vision() {
			return maximum_vision;
		}
		public void setMaximum_vision(int maximum_vision) {
			this.maximum_vision = maximum_vision;
		}
		public int getMinimum_death_age() {
			return minimum_death_age;
		}
		public void setMinimum_death_age(int minimum_death_age) {
			this.minimum_death_age = minimum_death_age;
		}
		public int getMaximum_death_age() {
			return maximum_death_age;
		}
		public void setMaximum_death_age(int maximum_death_age) {
			this.maximum_death_age = maximum_death_age;
		}
		public int getMinimum_wealth() {
			return minimum_wealth;
		}
		public void setMinimum_wealth(int minimum_wealth) {
			this.minimum_wealth = minimum_wealth;
		}
		public int getMaximum_wealth() {
			return maximum_wealth;
		}
		public void setMaximum_wealth(int maximum_wealth) {
			this.maximum_wealth = maximum_wealth;
		}
		public int getGrowthRate() {
			return growthRate;
		}
		public void setGrowthRate(int growthRate) {
			this.growthRate = growthRate;
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
	public SugarscapeConfig() {
		this.setModelParams(new ModelParams());
		this.setViewParams(new ViewParams());
		this.setModelClass(SugarscapeModel.class);
		this.setViewClasses(new Class[] { SugarscapeView.class,WealthDistributionChart.class });
	}

	public static void main(String[] args) {
		start(SugarscapeConfig.class);
	}

}
