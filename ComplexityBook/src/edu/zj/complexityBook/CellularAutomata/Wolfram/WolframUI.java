package edu.zj.complexityBook.CellularAutomata.Wolfram;

import java.io.File;
import java.net.MalformedURLException;

import edu.zj.complexityBook.utils.ObjectWrapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WolframUI extends Application {
	private TextField stepField = new TextField("0");;
	private int step = 0;
	BorderPane center = new BorderPane();
	private WolframData data;
	WolframView view;
	TextField intervalTimeField = new TextField("500");
	TextField sizeField = new TextField("200");
	TextField ruleField = new TextField("110");
	TextField cellSizeField = new TextField("10");

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane root = center;
		center.setTop(commandPane());
		// root.setCenter(center);

		Scene scene = new Scene(root, 800, 600);
		primaryStage.setTitle("Wolfram 一维元胞自动机 ");
		primaryStage.setScene(scene);
		primaryStage.show();
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

	VBox vbox;

	public FlowPane commandPane() {
		FlowPane commandPane = new FlowPane();
		Button startButton = imageButton("resources/pictures/1498914398_Play1Normal.png");
		Button stopButton = imageButton("resources/pictures/1498914718_Stop1NormalRed.png");
		Button stepButton = imageButton("resources/pictures/1498915380_StepForwardPressed.png");
		Button setupButton = imageButton("resources/pictures/setup-folder-icon.png");

		stopButton.setDisable(true);
		Label maxStepLabel = new Label("总步数 ");
		TextField maxStepField = new TextField("300");
		maxStepField.setPrefColumnCount(3);

		maxStepField.setEditable(false);
		Label intervalTimeLabel = new Label("时间间隔 ");
		intervalTimeField.setPrefColumnCount(3);
		Label stepLabel = new Label(" 步 ");
		stepField.setPrefColumnCount(3);
		sizeField.setPrefColumnCount(3);
		ruleField.setPrefColumnCount(3);
		cellSizeField.setPrefColumnCount(3);

		Label cellSizeLabel = new Label(" 网格大小 ");
		
		Label sizeLabel = new Label(" 长度 ");
		Label ruleLabel = new Label(" 规则 ");

		stepField.setPrefColumnCount(3);

		commandPane.getChildren().addAll(startButton, stopButton, stepButton, maxStepLabel, maxStepField,
				intervalTimeLabel, intervalTimeField, stepLabel, stepField, setupButton, sizeLabel, sizeField,
				ruleLabel, ruleField,cellSizeLabel,cellSizeField);

		ObjectWrapper<Thread> threadWrapper = new ObjectWrapper<>();
		setupButton.setOnAction(e -> {
			vbox=new VBox();
			
			view = new WolframView(Integer.parseInt(sizeField.getText()), Integer.parseInt(cellSizeField.getText()));
			vbox.getChildren().add(view);
			data = new WolframData(Integer.parseInt(sizeField.getText()), Integer.parseInt(ruleField.getText()));
			view.loadData(data);
			view.show();
			center.setCenter(new ScrollPane(vbox));

		});
		// task and thread are ONE SHOT
		startButton.setOnAction((e) -> {
			stopButton.setDisable(false);
			if (threadWrapper.getValue() != null) {
				threadWrapper.getValue().interrupt();
				try {
					threadWrapper.getValue().join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					System.out.println("Thread started current step = " + step+" maxSteps =" +Integer.parseInt(maxStepField.getText()));
					while (step < Integer.parseInt(maxStepField.getText())) {
						System.out.println("Step =" + step);
						stepRun();
						Thread.sleep(Long.parseLong(intervalTimeField.getText()));
					}
					stopButton.setDisable(true);
					return null;
				}

			};
			Thread thread = new Thread(task);
			threadWrapper.setValue(thread);
			thread.setDaemon(true);
			System.out.println("Started thread " + thread);
			thread.start();
		});

		stepButton.setOnAction((e) -> {
			stepRun();
		});

		stopButton.setOnAction((e) -> {
			if (threadWrapper.getValue() != null)
				threadWrapper.getValue().interrupt();
			threadWrapper.setValue(null);
		});

		return commandPane;
	}

	public void stepRun() {
		System.out.println("Step run");
		step++;
		data.evaluate(false);
		data.update();
		Platform.runLater(() -> {
			stepField.setText(Integer.toString(step));
			view = new WolframView(Integer.parseInt(sizeField.getText()),Integer.parseInt(cellSizeField.getText()));
			view.loadData(data);
			vbox.getChildren().add(view);
			view.show();
		});		
	}

	public static Button imageButton(String path) {
		ImageView icon = new ImageView(image(path));
		icon.setFitHeight(10);
		icon.setFitWidth(10);
		return new Button(null, icon);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
