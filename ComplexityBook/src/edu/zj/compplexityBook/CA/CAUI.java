package edu.zj.compplexityBook.CA;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CAUI extends Application {
	public static double CELL_SIZE = 30;
	public static int maxSteps = 300;
	private TextField stepField = new TextField("0");;
	private int step = 0;
	private String beginRow, beginColumn;
	BorderPane center = new BorderPane();

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
		blockItem.setOnAction(e -> {
			System.out.println("Block clicked");
			CAGridView<GolCellState, GolData> view=new CAGridView<GolCellState, GolData>(5, 5, 30);
			GolData data = new GolData();
			data.setData(GolCellState.alive, 2, 1);
			data.setData(GolCellState.alive, 2, 2);
			data.setData(GolCellState.alive, 2, 3);
			beginRow="0";
			beginColumn="0";
			view.loadData(data,beginRow,beginColumn);
			center.setCenter(view);
			
		});
		stillMenu.getItems().add(blockItem);
		Menu oscillatorsMenu = new Menu("Oscillators");
		menuBar.getMenus().addAll(stillMenu, oscillatorsMenu);
		return menuBar;
	}

	public FlowPane commandPane() {
		FlowPane commandPane = new FlowPane();
		Button startButton = imageButton("resources/pictures/1498914398_Play1Normal.png");
		Button stepButton = imageButton("resources/pictures/1498915380_StepForwardPressed.png");
		Label maxStepLabel = new Label("总步数 ");
		TextField maxStepField = new TextField("300");
		maxStepField.setPrefColumnCount(3);

		maxStepField.setEditable(false);
		Label intervalTimeLabel = new Label("时间间隔 ");
		TextField intervalTimeField = new TextField("500");
		intervalTimeField.setPrefColumnCount(3);
		Label stepLabel = new Label(" 步 ");

		stepField.setPrefColumnCount(3);
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
		icon.setFitHeight(10);
		icon.setFitWidth(10);
		return new Button(null, icon);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
