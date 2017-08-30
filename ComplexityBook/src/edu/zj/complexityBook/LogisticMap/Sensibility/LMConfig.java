package edu.zj.complexityBook.LogisticMap.Sensibility;

import java.math.BigDecimal;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;

public class LMConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("r")
		private BigDecimal r  = new BigDecimal("4");
		@UIDesc("x01")
		private BigDecimal x01 = new BigDecimal("0.20");
		@UIDesc("x01")
		private BigDecimal x02 = new BigDecimal("0.2000000001");
		@UIDesc("Scale")
		private int scale = 10;
		public BigDecimal getR() {
			return r;
		}
		public void setR(BigDecimal r) {
			this.r = r;
		}
		public BigDecimal getX01() {
			return x01;
		}
		public void setX01(BigDecimal x01) {
			this.x01 = x01;
		}
		public BigDecimal getX02() {
			return x02;
		}
		public void setX02(BigDecimal x02) {
			this.x02 = x02;
		}
		public int getScale() {
			return scale;
		}
		public void setScale(int scale) {
			this.scale = scale;
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
