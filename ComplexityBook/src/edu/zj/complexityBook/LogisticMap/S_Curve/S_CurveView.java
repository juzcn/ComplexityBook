package edu.zj.complexityBook.LogisticMap.S_Curve;

import edu.zj.complexityBook.UI.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class S_CurveView extends View {
	XYChart.Series<Number, Number> series;
	S_CurveModel model;

	public S_CurveView(S_CurveModel model, S_CurveConfig.ViewParams params) {
		setTitle(" 逻辑斯谛方程S型曲线");
		this.model = model;
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("t");
		xAxis.setAutoRanging(true);
//		xAxis.setTickUnit(1);		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("x");
		yAxis.setAutoRanging(true);
//		ScatterChart<Number, Number> chart = new ScatterChart<Number, Number>(xAxis, yAxis);
		LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis, yAxis);
		chart.setTitle(getTitle());
		chart.setId("small-symbol");
		setNode(chart);
		series = new XYChart.Series<>();
		series.setName("S型曲线");
		series.getData().add(new Data<>(0, model.getCurrentValue()));
		ObservableList<XYChart.Series<Number, Number>> chartData = FXCollections.<XYChart.Series<Number, Number>>observableArrayList(
				series);
//		series.getData().add(new Data<>(0,model.getX0()));
		chart.setCreateSymbols(false);
		chart.setData(chartData);
	}

	@Override
	public void show() {
		// not display symbol when dataset is empty
	}

	public void redraw() {
		series.getData().add(new Data<>(model.getCurrentT(),model.getCurrentValue()));
	}
}
