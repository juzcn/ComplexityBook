package edu.zj.complexityBook.MAS.ElFarol;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;

public class BarConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("������")
		private int agentCount = 100;
		@UIDesc("��ֵ")
		private int  threshold  = 60;
		@UIDesc("��ʷ��")
		private int historyCount = 4;
		@UIDesc("����ʧЧ��")
		private int ruleExpired = 10;
		@UIDesc("���������")
		private long seed = 100L;
		public int getAgentCount() {
			return agentCount;
		}
		public void setAgentCount(int agentCount) {
			this.agentCount = agentCount;
		}
		public int getThreshold() {
			return threshold;
		}
		public void setThreshold(int threshold) {
			this.threshold = threshold;
		}
		public int getHistoryCount() {
			return historyCount;
		}
		public void setHistoryCount(int historyCount) {
			this.historyCount = historyCount;
		}
		public long getSeed() {
			return seed;
		}
		public void setSeed(long seed) {
			this.seed = seed;
		}
		public int getRuleExpired() {
			return ruleExpired;
		}
		public void setRuleExpired(int ruleExpired) {
			this.ruleExpired = ruleExpired;
		}
	}

	public static class ViewParams extends Params {
	}

	@SuppressWarnings({ "unchecked" })
	public BarConfig() {
		this.setModelParams(new ModelParams());
		this.setViewParams(new ViewParams());
		this.setModelClass(BarWorld.class);
		this.setViewClasses(new Class[] { BarView.class });
		this.setMaxSteps(100);
		
	}

	public static void main(String[] args) {
		start(BarConfig.class);
	}

}
