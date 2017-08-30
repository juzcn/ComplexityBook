package edu.zj.complexityBook.LogisticMap.Multiple;

import java.math.BigDecimal;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;

public class LMConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("r1")
		private BigDecimal r1  = new BigDecimal("0.5");
		@UIDesc("r2")
		private BigDecimal r2  = new BigDecimal("2.0");
		@UIDesc("r3")
		private BigDecimal r3  = new BigDecimal("2.7");
		@UIDesc("r4")
		private BigDecimal r4  = new BigDecimal("3.2");
		@UIDesc("r5")
		private BigDecimal r5  = new BigDecimal("3.5");
		@UIDesc("r6")
		private BigDecimal r6  = new BigDecimal("3.8");
		@UIDesc("x0")
		private BigDecimal x0 = new BigDecimal("0.1");
		@UIDesc("Scale")
		private int scale = 10;
		public BigDecimal getR1() {
			return r1;
		}
		public void setR1(BigDecimal r1) {
			this.r1 = r1;
		}
		public BigDecimal getR2() {
			return r2;
		}
		public void setR2(BigDecimal r2) {
			this.r2 = r2;
		}
		public BigDecimal getR3() {
			return r3;
		}
		public void setR3(BigDecimal r3) {
			this.r3 = r3;
		}
		public BigDecimal getR4() {
			return r4;
		}
		public void setR4(BigDecimal r4) {
			this.r4 = r4;
		}
		public BigDecimal getR5() {
			return r5;
		}
		public void setR5(BigDecimal r5) {
			this.r5 = r5;
		}
		public BigDecimal getX0() {
			return x0;
		}
		public void setX0(BigDecimal x0) {
			this.x0 = x0;
		}
		public int getScale() {
			return scale;
		}
		public void setScale(int scale) {
			this.scale = scale;
		}
		public BigDecimal getR6() {
			return r6;
		}
		public void setR6(BigDecimal r6) {
			this.r6 = r6;
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
