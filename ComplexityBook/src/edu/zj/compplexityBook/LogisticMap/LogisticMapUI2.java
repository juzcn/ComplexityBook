package edu.zj.compplexityBook.LogisticMap;

import javafx.application.*;
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

public class LogisticMapUI2 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = new BorderPane();

		GridPane gp = new GridPane();
		Label x01Label = new Label("1 X0=");
		TextField x01Field = new TextField("0.2");
		Label x02Label = new Label("2 X0=");
		TextField x02Field = new TextField("0.2000000001");
		Label rLabel = new Label("r=");
		TextField rField = new TextField("4");
		Label generationsLabel = new Label("运行期数");
		TextField generationsField = new TextField("80");
		Button resetButton = new Button("重新输入");
		resetButton.setOnAction((e) -> {
			x01Field.clear();
			x02Field.clear();
			rField.clear();
			generationsField.clear();
		});

		Button runButton = new Button("运行");
		gp.addRow(0, x01Label, x01Field);
		gp.addRow(1, x02Label, x02Field);
		gp.addRow(2, rLabel, rField);
		gp.addRow(3, generationsLabel, generationsField);
		gp.addRow(4, resetButton, runButton);
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("t");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("X");
		LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle("Logistic公式的行为");
		bp.setLeft(gp);
		bp.setCenter(chart);
		Scene s = new Scene(bp);

		ObservableList<XYChart.Series<Number, Number>> data = FXCollections.<XYChart.Series<Number, Number>>observableArrayList();
		XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
		data.add(series1);
		XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
		data.add(series2);

		chart.setData(data);
		runButton.setOnAction((e) -> {
			series1.getData().clear();
			series2.getData().clear();
			series1.setName("X0="+x01Field.getText());
			series2.setName("X0="+x02Field.getText());
			new LogisticMap(rField.getText(),10)
			.iterate(x01Field.getText(),Integer.parseInt(generationsField.getText()), (i, x) -> {
				XYChart.Data<Number, Number> d = new XYChart.Data<>(i, x.doubleValue());
				series1.getData().add(d);
				Tooltip.install(d.getNode(), new Tooltip("(" + d.getXValue() + "," + d.getYValue() + ")"));
			});
			new LogisticMap(rField.getText(),10)
			.iterate(x02Field.getText(),Integer.parseInt(generationsField.getText()), (i, x) -> {
				XYChart.Data<Number, Number> d = new XYChart.Data<>(i, x.doubleValue());
				series2.getData().add(d);
				Tooltip.install(d.getNode(), new Tooltip("(" + d.getXValue() + "," + d.getYValue() + ")"));
			});
		});

		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
