package edu.zj.complexityBook.LogisticMap.S_Curve;

import java.math.BigDecimal;

import edu.zj.complexityBook.UI.AppConfig;
import edu.zj.complexityBook.UI.UIDesc;

public class S_CurveConfig extends AppConfig {
	public static class ModelParams extends Params {
		@UIDesc("r")
		private BigDecimal r  = new BigDecimal("1.5");
		@UIDesc("x0")
		private BigDecimal x0 = new BigDecimal("0.0001");
		@UIDesc("dt")
		private BigDecimal dt = new BigDecimal("0.1");
		@UIDesc("Scale")
		private int scale = 10;
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
		public BigDecimal getR() {
			return r;
		}
		public void setR(BigDecimal r) {
			this.r = r;
		}
		public BigDecimal getDt() {
			return dt;
		}
		public void setDt(BigDecimal dt) {
			this.dt = dt;
		}
	}

	public static class ViewParams extends Params {
	}

	@SuppressWarnings({ "unchecked" })
	public S_CurveConfig() {
		this.setModelParams(new ModelParams());
		this.setViewParams(new ViewParams());
		this.setModelClass(S_CurveModel.class);
		this.setViewClasses(new Class[] { S_CurveView.class});
		this.setMaxSteps(100);
	}

	public static void main(String[] args) {
		start(S_CurveConfig.class);
	}

}
