package edu.zj.compplexityBook.CA;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CAUI extends Application {
	public static double CELL_SIZE = 30;
	public static int maxSteps = 300;
	private TextField stepField;
	private int step=0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox(commandPane(), spacePane());
		Scene scene = new Scene(root);
		primaryStage.setTitle("扫地机器人");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public StackPane spacePane() {
		return null;
	}

	public static Image image(String path) {
		try {
			return new Image(new File(path).toURI().toURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public FlowPane commandPane() {
		FlowPane commandPane = new FlowPane();
		Button startButton = imageButton("resources/pictures/1498914398_Play1Normal.png");
		Button stepButton = imageButton("resources/pictures/1498915380_StepForwardPressed.png");
		Label maxStepLabel = new Label("总步数 ");
		TextField maxStepField = new TextField("300");
		maxStepField.setPrefColumnCount(5);

		maxStepField.setEditable(false);
		Label intervalTimeLabel = new Label("时间间隔 ");
		TextField intervalTimeField = new TextField("500");
		intervalTimeField.setPrefColumnCount(5);
		Label stepLabel = new Label(" 步 ");
		stepField.setPrefColumnCount(4);
		commandPane.getChildren().addAll(startButton, stepButton, maxStepLabel, maxStepField, intervalTimeLabel,
				intervalTimeField, stepLabel, stepField);
		startButton.setOnAction((e) -> {
			while (step < Integer.parseInt(maxStepField.getText())) {
				stepRun();
			}
		});
		stepButton.setOnAction((e) -> {
			stepRun();
		});

		return commandPane;
	}

	public void stepRun() {
		step++;
	}

	public static Button imageButton(String path) {
		ImageView icon = new ImageView(image(path));
		icon.setFitHeight(30);
		icon.setFitWidth(30);
		return new Button(null, icon);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
