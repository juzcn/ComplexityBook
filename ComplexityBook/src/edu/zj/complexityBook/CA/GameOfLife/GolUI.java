package edu.zj.complexityBook.CA.GameOfLife;

import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;

import edu.zj.complexityBook.CA.GameOfLife.GolCell.State;
import edu.zj.complexityBook.utils.Wrapper;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GolUI extends Application {
	public static double CELL_SIZE = 30;
	public static int maxSteps = 300;
	private TextField stepField = new TextField("0");;
	private int step = 0;
	private String beginRow, beginColumn;
	BorderPane center = new BorderPane();
	private GolMain caMain;
	GolView display;
	TextField intervalTimeField = new TextField("500");

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane root = new BorderPane();
		root.setTop(menuBar());
		center.setTop(commandPane());

		// hbox.getChildren().addAll(new
		// CACanvas<LifeState>(data.clone(),30d),new
		// CACanvas<LifeState>(data,30d));
		root.setCenter(center);

		Scene scene = new Scene(root, 800, 600);
		primaryStage.setTitle("生命游戏 Life of Game ");
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

	public MenuBar menuBar() {
		MenuBar menuBar = new MenuBar();
		Menu stillMenu = new Menu("Still Lifes");
		MenuItem blockItem = new MenuItem("Block");
		MenuItem blinkerItem = new MenuItem("Blinker");
		MenuItem gliderItem = new MenuItem("Glider");
		blockItem.setOnAction(e -> {
			caMain = new GolMain(Integer.parseInt(this.maxStepField.getText()));
			caMain.getCaGrid().set(1, 1, State.alive);
			caMain.getCaGrid().set(1, 2, State.alive);
			caMain.getCaGrid().set(2, 1, State.alive);
			caMain.getCaGrid().set(2, 2, State.alive);
			display = new GolView(4, 4, 30, Color.BLACK, caMain.getCaGrid());
			display.show();
			center.setCenter(display.getView());

		});
		blinkerItem.setOnAction(e -> {
			caMain = new GolMain(Integer.parseInt(this.maxStepField.getText()));
			caMain.getCaGrid().set(2, 1, State.alive);
			caMain.getCaGrid().set(2, 2, State.alive);
			caMain.getCaGrid().set(2, 3, State.alive);
			display = new GolView(5, 5, 30, Color.BLACK, caMain.getCaGrid());
			display.show();
			center.setCenter(display.getView());
		});
		gliderItem.setOnAction(e -> {
			caMain = new GolMain(Integer.parseInt(this.maxStepField.getText()));
			caMain.getCaGrid().set(1, 3, State.alive);
			caMain.getCaGrid().set(2, 1, State.alive);
			caMain.getCaGrid().set(2, 3, State.alive);
			caMain.getCaGrid().set(3, 2, State.alive);
			caMain.getCaGrid().set(3, 3, State.alive);
			display = new GolView(6, 6, 30, Color.BLACK, caMain.getCaGrid());
			display.show();
			center.setCenter(display.getView());
		});
		stillMenu.getItems().add(blockItem);

		Menu oscillatorsMenu = new Menu("Oscillators");
		Menu spaceshipsMenu = new Menu("Spaceships");
		oscillatorsMenu.getItems().add(blinkerItem);
		spaceshipsMenu.getItems().add(gliderItem);
		menuBar.getMenus().addAll(stillMenu, oscillatorsMenu, spaceshipsMenu);
		return menuBar;
	}

	TextField maxStepField = new TextField("300");

	public FlowPane commandPane() {
		FlowPane commandPane = new FlowPane();
		Button startButton = imageButton("resources/pictures/1498914398_Play1Normal.png");
		Button stopButton = imageButton("resources/pictures/1498914718_Stop1NormalRed.png");
		Button stepButton = imageButton("resources/pictures/1498915380_StepForwardPressed.png");
		stopButton.setDisable(true);
		Label maxStepLabel = new Label("总步数 ");
		TextField maxStepField = new TextField("300");
		maxStepField.setPrefColumnCount(3);

		maxStepField.setEditable(false);
		Label intervalTimeLabel = new Label("时间间隔 ");
		intervalTimeField.setPrefColumnCount(3);
		Label stepLabel = new Label(" 步 ");

		stepField.setPrefColumnCount(3);
		commandPane.getChildren().addAll(startButton, stopButton, stepButton, maxStepLabel, maxStepField,
				intervalTimeLabel, intervalTimeField, stepLabel, stepField);

		Wrapper<Thread> threadWrapper = new Wrapper<>();
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
					System.out.println("Thread started cuurent step = " + step);
					while (step < Integer.parseInt(maxStepField.getText())) {
						stepRun();
						Thread.sleep(Long.parseLong(intervalTimeField.getText()));
					}
					stopButton.setDisable(true);
					return null;
				}

			};
			Thread thread = new Thread(task);
			threadWrapper.setValue(thread);
			// thread.setDaemon(true);
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
		step++;
		stepField.setText(Integer.toString(step));
		caMain.stepRun();
		display.redraw();
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
