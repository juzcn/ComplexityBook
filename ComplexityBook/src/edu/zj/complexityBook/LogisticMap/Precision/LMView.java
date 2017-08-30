package edu.zj.complexityBook.LogisticMap.Precision;

import edu.zj.complexityBook.UI.View;
import edu.zj.utils.UIGadgets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class LMView extends View {
	XYChart.Series<Number, Number> series10;
	XYChart.Series<Number, Number> series12;
	XYChart.Series<Number, Number> series;
	LMModel model;

	public LMView(LMModel model, LMConfig.ViewParams params) {
		setTitle(" Âß¼­Ë¹ÚÐÓ³Éä");
		this.model = model;
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("n");
		xAxis.setTickLabelFormatter(new UIGadgets.IntegerStringConverter());
		
		xAxis.setAutoRanging(true);
		xAxis.setMinorTickVisible(false);
		xAxis.setTickUnit(2);
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("x");
		yAxis.setMinorTickVisible(false);
		yAxis.setAutoRanging(true);
		// ScatterChart<Number, Number> chart = new ScatterChart<Number,
		// Number>(xAxis, yAxis);
		LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis, yAxis);
		chart.setTitle(getTitle());
		chart.setId("small-symbol");
		setNode(chart);
		
		series10 = new XYChart.Series<>();
		series10.setName("scale="+model.getLm10().getScale());
		series10.getData().add(new Data<>(model.getStep(), model.getCurrentValue10().doubleValue()));
		series12 = new XYChart.Series<>();
		series12.setName("scale="+model.getLm12().getScale());
		series12.getData().add(new Data<>(model.getStep(), model.getCurrentValue12().doubleValue()));
		series = new XYChart.Series<>();
		series.setName("scale="+model.getLm().getScale());
		series.getData().add(new Data<>(model.getStep(), model.getCurrentValue().doubleValue()));

		ObservableList<XYChart.Series<Number, Number>> chartData = FXCollections.<XYChart.Series<Number, Number>>observableArrayList(
				series10,series12,series);
		// series.getData().add(new Data<>(0,model.getX0()));
		chart.setCreateSymbols(false);
		chart.setData(chartData);
	}

	@Override
	public void show() {
		// not display symbol when dataset is empty
	}

	public void redraw() {
		series10.getData().add(new Data<>(model.getStep(), model.getCurrentValue10().doubleValue()));
		series12.getData().add(new Data<>(model.getStep(), model.getCurrentValue12().doubleValue()));
		series.getData().add(new Data<>(model.getStep(), model.getCurrentValue().doubleValue()));
	}
}
