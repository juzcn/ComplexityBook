package edu.zj.complexityBook.LogisticMap.Precision;

import java.math.BigDecimal;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;

public class LMConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("r")
		private BigDecimal r  = new BigDecimal("4");
		@UIDesc("x0")
		private BigDecimal x0 = new BigDecimal("0.20");
		@UIDesc("Scale 1")
		private int scale1 = 10;
		@UIDesc("Scale 2")
		private int scale2 = 12;
		@UIDesc("Scale 3")
		private int scale3 = 30;
		public BigDecimal getR() {
			return r;
		}
		public void setR(BigDecimal r) {
			this.r = r;
		}
		public BigDecimal getX0() {
			return x0;
		}
		public void setX0(BigDecimal x0) {
			this.x0 = x0;
		}
		public int getScale1() {
			return scale1;
		}
		public void setScale1(int scale1) {
			this.scale1 = scale1;
		}
		public int getScale2() {
			return scale2;
		}
		public void setScale2(int scale2) {
			this.scale2 = scale2;
		}
		public int getScale3() {
			return scale3;
		}
		public void setScale3(int scale3) {
			this.scale3 = scale3;
		}
	}

	public static class ViewParams extends Params {
	}

	@SuppressWarnings({ "unchecked" })
	public LMConfig() {
		this.setModelParams(new ModelParams());
		this.setViewParams(new ViewParams());
		this.setModelClass(LMModel.class);
		this.setViewClasses(new Class[] { LMView.class});
		this.setMaxSteps(30);
	}

	public static void main(String[] args) {
		start(LMConfig.class);
	}

}
