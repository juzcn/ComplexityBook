package edu.zj.complexityBook.MAS.Sugarscape;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.zj.complexityBook.UI.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class WealthDistributionChart extends View {
	SugarscapeModel model;
	XYChart.Series<String, Number> series;
	BarChart<String, Number> chart;
	ObservableList<XYChart.Series<String, Number>> chartData;
	public WealthDistributionChart(SugarscapeModel model, SugarscapeConfig.ViewParams params) {
		this.model = model;
		setTitle("财富分布");
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("财富");
//		xAxis.setAutoRanging(true);
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("主体数");
		yAxis.setAutoRanging(true);
		chart = new BarChart<>(xAxis, yAxis);
		chart.setTitle(getTitle());
		// chart.setId("small-symbol");
		setNode(chart);
		// Set the data for the chart
		series = new XYChart.Series<>();
		// series.getData().add(new Data<>(0, 0));

		chartData = FXCollections.<XYChart.Series<String, Number>>observableArrayList(
				series);
//		for (int i=0;i<10;i++) {
//			series.getData().add(new Data<>());
//		}
		chart.setData(chartData);
	}

	@Override
	public void show() {
		List<SugarscapeAgent> list = this.model.getAgentList();
		int maxWealth = 0;
		for (SugarscapeAgent sa : list) {
			if (sa.getWealth() > maxWealth)
				maxWealth = sa.getWealth();
		}
		int part = maxWealth / 10+1;
		int partAgents[] = new int[10];
		for (int i = 0; i < 10; i++) {
			partAgents[i] = 0;
		}
		int index;
		for (SugarscapeAgent sa : list) {
			index = sa.getWealth() / part;
			partAgents[index]=partAgents[index]+1;
		}
		for (int i=0;i<10;i++) {
			series.getData().add(new Data(Integer.toString((i+1)*part),partAgents[i]));
//			series.getData().get(i).setXValue(Integer.toString((i+1)*part));
//			series.getData().get(i).setYValue(partAgents[i]);
		}
		
	}

	@Override
	public void redraw() {
		series.getData().clear();
//		chartData.set(0, series);
//		chartData = FXCollections.<XYChart.Series<String, Number>>observableArrayList(
//				series);
//		chart.setData(chartData);
//		series.getData().clear();
		show();
	}
}
