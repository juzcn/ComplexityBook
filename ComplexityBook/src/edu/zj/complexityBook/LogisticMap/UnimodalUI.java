package edu.zj.complexityBook.LogisticMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;;

public class UnimodalUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = new BorderPane();

		GridPane gp = new GridPane();
		Label rLabel = new Label("r =");
		TextField rField = new TextField("4");
		Label stepLabel = new Label("步长");
		TextField stepField = new TextField("0.05");
		Button resetButton = new Button("重新输入");
		resetButton.setOnAction((e) -> {
			rField.clear();
			stepField.clear();
		});

		Button runButton = new Button("运行");
		gp.addRow(0, rLabel, rField);
		gp.addRow(1, stepLabel, stepField);
		gp.addRow(2, resetButton, runButton);

		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Xt");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Xt+1");
		xAxis.setAutoRanging(false);
		xAxis.setLowerBound(0);
		xAxis.setUpperBound(1);
		xAxis.setTickUnit(0.1);
		yAxis.setAutoRanging(false);
		yAxis.setLowerBound(0);
		yAxis.setUpperBound(1);
		yAxis.setTickUnit(0.1);

		LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle("Xt+1与Xt的关系曲线");
		bp.setLeft(gp);
		bp.setCenter(chart);
		Scene s = new Scene(bp);

		ObservableList<XYChart.Series<Number, Number>> data = FXCollections.<XYChart.Series<Number, Number>>observableArrayList();
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		data.add(series);

		chart.setData(data);
		chart.setLegendVisible(false);
		runButton.setOnAction((e) -> {
			series.getData().clear();
			new LogisticMap(rField.getText(),10)
			.variations(stepField.getText(), (x, x1) -> {
				XYChart.Data<Number, Number> d = new XYChart.Data<>(x.doubleValue(), x1.doubleValue());
				series.getData().add(d);
				Tooltip.install(d.getNode(), new Tooltip("(" + d.getXValue() + "," + d.getYValue() + ")"));
			});
		});

		primaryStage.setTitle("Logistic公式的行为");
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
