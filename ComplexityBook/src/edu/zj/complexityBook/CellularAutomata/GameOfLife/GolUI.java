package edu.zj.complexityBook.CellularAutomata.GameOfLife;

import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;

import edu.zj.complexityBook.CellularAutomata.GameOfLife.GolData.State;
import edu.zj.complexityBook.utils.ObjectWrapper;
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
import javafx.stage.Stage;

public class GolUI extends Application {
	public static double CELL_SIZE = 30;
	public static int maxSteps = 300;
	private TextField stepField = new TextField("0");;
	private int step = 0;
	private String beginRow, beginColumn;
	BorderPane center = new BorderPane();
	private GolData data;
	GolView view;
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
			System.out.println("Block clicked");
			view = new GolView(4, 4, 30);
			data = new GolData(State.dead);
			data.setData(new BigInteger("1"), new BigInteger("1"),State.alive);
			data.setData(new BigInteger("1"), new BigInteger("2"),State.alive);
			data.setData(new BigInteger("2"), new BigInteger("1"),State.alive);
			data.setData( new BigInteger("2"), new BigInteger("2"),State.alive);
			view.loadData(data);
			view.show();
			center.setCenter(view);

		});
		blinkerItem.setOnAction(e -> {
			System.out.println("Block clicked");
			view = new GolView(5, 5, 30);
			data = new GolData(State.dead);
			data.setData(new BigInteger("2"), new BigInteger("1"),State.alive);
			data.setData(new BigInteger("2"), new BigInteger("2"),State.alive);
			data.setData(new BigInteger("2"), new BigInteger("3"),State.alive);
			beginRow = "0";
			beginColumn = "0";
			view.loadData(data);
			view.show();

			center.setCenter(view);

		});
		gliderItem.setOnAction(e -> {
			System.out.println("Block clicked");
			view = new GolView(6, 6, 30);
			data = new GolData(State.dead);
			data.setData(new BigInteger("1"), new BigInteger("3"),State.alive);
			data.setData(new BigInteger("2"), new BigInteger("1"),State.alive);
			data.setData(new BigInteger("2"), new BigInteger("3"),State.alive);
			data.setData(new BigInteger("3"), new BigInteger("2"),State.alive);
			data.setData(new BigInteger("3"), new BigInteger("3"),State.alive);
			beginRow = "0";
			beginColumn = "0";
			view.loadData(data);
			view.show();

			center.setCenter(view);

		});
		stillMenu.getItems().add(blockItem);

		Menu oscillatorsMenu = new Menu("Oscillators");
		Menu spaceshipsMenu = new Menu("Spaceships");
		oscillatorsMenu.getItems().add(blinkerItem);
		spaceshipsMenu.getItems().add(gliderItem);
		menuBar.getMenus().addAll(stillMenu, oscillatorsMenu,spaceshipsMenu);
		return menuBar;
	}

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
		commandPane.getChildren().addAll(startButton, stopButton,stepButton, maxStepLabel, maxStepField, intervalTimeLabel,
				intervalTimeField, stepLabel, stepField);
		
		ObjectWrapper<Thread> threadWrapper=new ObjectWrapper<>(); 
		// task and thread are ONE SHOT
		startButton.setOnAction((e) -> {
			stopButton.setDisable(false);
			if (threadWrapper.getValue()!=null) { 
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
					System.out.println("Thread started cuurent step = "+step);
					while (step < Integer.parseInt(maxStepField.getText())) {
						stepRun();
						Thread.sleep(Long.parseLong(intervalTimeField.getText()));
					}
					stopButton.setDisable(true);
					return null;
				}

			};
			Thread thread=new Thread(task);
			threadWrapper.setValue(thread);
//			thread.setDaemon(true);
			System.out.println("Started thread "+thread);
			thread.start();
		});

		stepButton.setOnAction((e) -> {
			stepRun();
		});

		stopButton.setOnAction((e) -> {
			if (threadWrapper.getValue()!=null) 
				threadWrapper.getValue().interrupt();
			threadWrapper.setValue(null);
		});

		return commandPane;
	}

	public void stepRun() {
		step++;
		stepField.setText(Integer.toString(step));
		data.evaluate();
		data.update();
		view.show();
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
