package edu.zj.complexityBook.CA.Sandpile;

import edu.zj.complexityBook.UI.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class SandStateChart extends View {
	SandpileModel model;
	XYChart.Series<Number, Number>[] series = new XYChart.Series[5];

	public SandStateChart(SandpileModel model, SandpileConfig.ViewParams params) {
		this.model = model;
		setTitle("网格状态按值分布");
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("步");
		xAxis.setAutoRanging(true);
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("网格数");
		yAxis.setAutoRanging(true);
		ScatterChart<Number, Number> chart = new ScatterChart<>(xAxis, yAxis);
		chart.setTitle(getTitle());
		chart.setId("small-symbol");
		setNode(chart);
		// Set the data for the chart
		for (int i = 0; i < 5; i++) {
			series[i] = new XYChart.Series<>();
			series[i].setName("状态" + i);
			series[i].getData().add(new Data<>(model.getStep(), model.getGrid().count(i)));
		}
		ObservableList<XYChart.Series<Number, Number>> chartData = FXCollections.<XYChart.Series<Number, Number>>observableArrayList(
				series);
		chart.setData(chartData);
	}

	@Override
	public void show() {
		// not display symbol when dataset is empty
	}

	@Override
	public void redraw() {
		for (int i = 0; i < 5; i++) {
			series[i].getData().add(new Data<>(model.getStep(), model.getGrid().count(i)));
		}
	}
}
