package edu.zj.complexityBook.LogisticMap;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;;

public class LogisticMapUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = new BorderPane();

		GridPane gp = new GridPane();
		Label x0Label = new Label("X0=");
		TextField x0Field = new TextField("0.2");
		Label rLabel = new Label("r=");
		TextField rField = new TextField("2");
		Label generationsLabel = new Label("运行期数");
		TextField generationsField = new TextField("50");
		Button resetButton = new Button("重新输入");
		resetButton.setOnAction((e) -> {
			x0Field.clear();
			rField.clear();
			generationsField.clear();
		});

		Button runButton = new Button("运行");
		gp.addRow(0, x0Label, x0Field);
		gp.addRow(1, rLabel, rField);
		gp.addRow(2, generationsLabel, generationsField);
		gp.addRow(3, resetButton, runButton);
		
		VBox vbox=new VBox();
		Label bifucationLabel=new Label("");
//		bifucationLabel.setStyle("-fx-background-color: gray;");
		bifucationLabel.setMaxWidth(Double.MAX_VALUE);
		bifucationLabel.setAlignment(Pos.CENTER);
		
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("t");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("X");
		LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle("x随时间演变的轨迹");
		bp.setLeft(gp);
		vbox.getChildren().addAll(chart,bifucationLabel);
		VBox.setVgrow(chart, Priority.ALWAYS);
		bp.setCenter(vbox);
		Scene s = new Scene(bp);

		ObservableList<XYChart.Series<Number, Number>> data = FXCollections.<XYChart.Series<Number, Number>>observableArrayList();
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		data.add(series);

		chart.setData(data);
		chart.setLegendVisible(false);
		runButton.setOnAction((e) -> {
			series.getData().clear();
			new LogisticMap(rField.getText(),10)
					.iterate(x0Field.getText(),Integer.parseInt(generationsField.getText()), (i, x) -> {
						XYChart.Data<Number, Number> d = new XYChart.Data<>(i, x.doubleValue());
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
