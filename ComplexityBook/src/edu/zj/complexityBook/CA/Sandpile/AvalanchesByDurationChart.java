package edu.zj.complexityBook.CA.Sandpile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.zj.complexityBook.CA.Sandpile.SandpileModel.Avalanche;
import edu.zj.complexityBook.UI.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class AvalanchesByDurationChart extends View {
	SandpileModel model;
	XYChart.Series<Number, Number> series;

	public AvalanchesByDurationChart(SandpileModel model, SandpileConfig.ViewParams params) {
		this.model = model;
		setTitle("��ʱ��ͳ�Ƶı�����");
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("����ʱ��");
		xAxis.setAutoRanging(true);
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("������");
		yAxis.setAutoRanging(true);
		ScatterChart<Number, Number> chart = new ScatterChart<>(xAxis, yAxis);
		chart.setTitle(getTitle());
		chart.setId("small-symbol");
		setNode(chart);
		avalanches = model.getAvalanches();
		// Set the data for the chart
		series = new XYChart.Series<>();
		series.setName("ʱ��");
		series.getData().add(new Data<>(avalanches.size(), 0));

		ObservableList<XYChart.Series<Number, Number>> chartData = FXCollections.<XYChart.Series<Number, Number>>observableArrayList(
				series);
		chart.setData(chartData);
	}

	@Override
	public void show() {
		// not display symbol when dataset is empty
	}

	List<Avalanche> avalanches;
	int size = 0;

	@Override
	public void redraw() {
		if (avalanches.size() > size) {
			// new added, processing newly added
			size = avalanches.size();
			int i;
			Avalanche a = avalanches.get(size - 1);
			for (i = 0; i < series.getData().size(); i++) {
				if ((Integer) series.getData().get(i).getXValue() == a.getDuration()) {
					series.getData().get(i).setYValue((Integer) series.getData().get(i).getYValue() + 1);
					break;
				}
			}
			if (i==series.getData().size()) {
				series.getData().add(new Data<Number,Number>(a.getDuration(),1));
			}
		}
	}

}
