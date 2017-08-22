package edu.zj.complexityBook.LogisticMap.Population;

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

public class AnimalPopulationUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = new BorderPane();

		GridPane gp = new GridPane();
		Label initialSizeLabel = new Label("��ʼ����");
		TextField initialSizeField = new TextField("20");
		Label increaseRateLabel = new Label("��������");
		TextField increaseRateField = new TextField("1.6");
		Label capacityLabel = new Label("�������");
		TextField capacityField = new TextField("32");
		Label generationsLabel = new Label("��������");
		TextField generationsField = new TextField("50");
		Button resetButton = new Button("��������");
		resetButton.setOnAction((e) -> {
			initialSizeField.clear();
			increaseRateField.clear();
			capacityField.clear();
			generationsField.clear();
		});

		Button runButton = new Button("����");
		gp.addRow(0, initialSizeLabel, initialSizeField);
		gp.addRow(1, increaseRateLabel, increaseRateField);
		gp.addRow(2, capacityLabel, capacityField);
		gp.addRow(3, generationsLabel, generationsField);
		gp.addRow(4, resetButton, runButton);
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("��");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("������");
		LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle("���������仯����ͼ");
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
			new AnimalPopulation(capacityField.getText(),increaseRateField.getText(),10)
			.iterate(initialSizeField.getText(),Integer.parseInt(generationsField.getText()), (i, x) -> {
				XYChart.Data<Number, Number> d = new XYChart.Data<>(i, x.doubleValue());
				series.getData().add(d);
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
