package edu.zj.complexityBook.LogisticMap.Multiple;

import edu.zj.complexityBook.UI.View;
import edu.zj.utils.UIGadgets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class LMView extends View {
	XYChart.Series<Number, Number> series[];
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
		
		series = new XYChart.Series[model.getLms().size()];
		for (int i = 0; i <model.getLms().size() ; i++) {
			series[i] = new XYChart.Series<>();
			series[i].setName("r="+model.getLms().get(i).getR());
			series[i].getData().add(new Data<>(model.getStep(), model.getCurrentValue().get(i).doubleValue()));
			
		}

		ObservableList<XYChart.Series<Number, Number>> chartData = FXCollections.<XYChart.Series<Number, Number>>observableArrayList(
				series);
		// series.getData().add(new Data<>(0,model.getX0()));
		chart.setCreateSymbols(false);
		chart.setData(chartData);
	}

	@Override
	public void show() {
		// not display symbol when dataset is empty
	}

	public void redraw() {
		for (int i = 0; i < series.length; i++) {
			series[i].getData().add(new Data<>(model.getStep(), model.getCurrentValue().get(i).doubleValue()));
		}
	}
}
