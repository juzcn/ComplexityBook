package edu.zj.complexityBook.MAS.ElFarol;

import edu.zj.complexityBook.CA.Sandpile.SandpileModel;
import edu.zj.complexityBook.UI.View;
import edu.zj.utils.Grid.View.GridDisplayCanvas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.paint.Color;

public class BarView extends View {
	private BarWorld model;
	private XYChart.Series<Number, Number> series;

	public BarView(BarWorld world, BarConfig.ViewParams params) {
		model = world;
		setTitle("酒吧每天人数统计");
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("时间");
		xAxis.setAutoRanging(true);
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("人数");
		yAxis.setAutoRanging(true);
		ScatterChart<Number, Number> chart = new ScatterChart<>(xAxis, yAxis);
		chart.setTitle(getTitle());
		chart.setId("small-symbol");
		setNode(chart);
		// Set the data for the chart
		series = new XYChart.Series<>();
		// series.setName("时长");
		ObservableList<XYChart.Series<Number, Number>> chartData = FXCollections.<XYChart.Series<Number, Number>>observableArrayList(
				series);
		chart.setData(chartData);

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redraw() {
		series.getData().add(new Data<>(model.getStep(), model.getBarAgentCount()));
	}

}
