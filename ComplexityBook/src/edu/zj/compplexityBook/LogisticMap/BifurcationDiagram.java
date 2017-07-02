package edu.zj.compplexityBook.LogisticMap;

import java.util.concurrent.CountDownLatch;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;;

public class BifurcationDiagram extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = new BorderPane();

		GridPane gp = new GridPane();
		Label r1Label = new Label("r的范围，从  ");
		TextField r1Field = new TextField("2.4");
		Label r2Label = new Label("到 ");
		TextField r2Field = new TextField("4");
		Label x0Label = new Label("X0 ");
		TextField x0Field = new TextField("0.2");
		Label samplesLabel = new Label("取样数");
		TextField samplesField = new TextField("800");

		Button resetButton = new Button("重新输入");
		resetButton.setOnAction((e) -> {
			r1Field.clear();
			r2Field.clear();
			x0Field.clear();
			samplesField.clear();
		});

		Button runButton = new Button("运行");
		gp.addRow(0, r1Label, r1Field);
		gp.addRow(1, r2Label, r2Field);
		gp.addRow(2, samplesLabel, samplesField);
		gp.addRow(3, x0Label, x0Field);
		gp.addRow(4, resetButton, runButton);

		VBox vbox = new VBox();
		Label bifucationLabel = new Label("");
		// bifucationLabel.setStyle("-fx-background-color: gray;");
		bifucationLabel.setMaxWidth(Double.MAX_VALUE);
		bifucationLabel.setAlignment(Pos.CENTER);

		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("r");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("X");
		xAxis.setAutoRanging(false);
		xAxis.setLowerBound(Double.parseDouble(r1Field.getText()));
		xAxis.setUpperBound(Double.parseDouble(r2Field.getText()));
		xAxis.setTickUnit(0.1);
		yAxis.setAutoRanging(false);
		yAxis.setLowerBound(0);
		yAxis.setUpperBound(1);
		yAxis.setTickUnit(0.1);
		ScatterChart<Number, Number> chart = new ScatterChart<>(xAxis, yAxis);

		chart.setTitle("Logistic公式分叉图");
		bp.setLeft(gp);
		vbox.getChildren().addAll(chart, bifucationLabel);
		VBox.setVgrow(chart, Priority.ALWAYS);
		bp.setCenter(vbox);
		Scene s = new Scene(bp);

		ObservableList<XYChart.Series<Number, Number>> data = FXCollections.<XYChart.Series<Number, Number>>observableArrayList();
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		data.add(series);

		chart.setData(data);
		chart.setLegendVisible(false);
		chart.setId("bifurcation-diagram");
		chart.getStylesheets().add("resources/chart.css");

		runButton.setOnAction((e) -> {
			xAxis.setLowerBound(Double.parseDouble(r1Field.getText()));
			xAxis.setUpperBound(Double.parseDouble(r2Field.getText()));
			series.getData().clear();

			Task<Void> task = new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					LogisticMap.bifurcationDiagram(r1Field.getText(), r2Field.getText(), x0Field.getText(),
							samplesField.getText(), (i, x) -> {

								final CountDownLatch doneLatch = new CountDownLatch(1);
								Platform.runLater(() -> {
									XYChart.Data<Number, Number> d = new XYChart.Data<>(i.doubleValue(),
											x.doubleValue());
									series.getData().add(d);
									Tooltip.install(d.getNode(),
											new Tooltip("(" + d.getXValue() + "," + d.getYValue() + ")"));
									doneLatch.countDown();
								});
								try {
									doneLatch.await();
								} catch (InterruptedException e) {
									// ignore exception
								}
							},10);
					return null;
				}
			};

			Thread backgroundThread = new Thread(task);
			backgroundThread.setDaemon(true);
			backgroundThread.start();

		});

		primaryStage.setTitle("Logistic公式的行为");
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
