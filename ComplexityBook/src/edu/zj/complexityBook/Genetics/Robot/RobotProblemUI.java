package edu.zj.complexityBook.Genetics.Robot;

import edu.zj.utils.UIGadgets;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RobotProblemUI extends Application {
	public static double CELL_SIZE = 30;
	public static double initialTrashPercent = 0.5;
	public static int maxSteps = 300;

	RobotProblem problem = new RobotProblem(20, 20, 0.5, maxSteps);
	RobotSimpleSolution solution = problem.getSimpleSolution();
	RobotProblem.Robot robot = problem.getRobot();
//	RobotSolution solution = problem.resolve(1000, 2, 0.3, 0.03);
	Canvas robotCanvas = new Canvas(CELL_SIZE, CELL_SIZE);
	GraphicsContext robotGc = robotCanvas.getGraphicsContext2D();
	Canvas spaceCanvas = new Canvas(CELL_SIZE * problem.getWidth(), CELL_SIZE * problem.getHeight());
	GraphicsContext spaceGc = spaceCanvas.getGraphicsContext2D();
	Image removedImage = UIGadgets.image("resources/pictures/1498922537_flat-style-circle-delete-trash.png");
	Image robotImage = UIGadgets.image("resources/pictures/robot-icon-30512.png");
	Image trashImage = UIGadgets.image("resources/pictures/Paper-icon.png");
	TextField scoreField = new TextField("0");
	TextField stepField = new TextField("0");
	int step = 0;
	int score = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		problem.restore();
		VBox root = new VBox(commandPane(), spacePane());
		Scene scene = new Scene(root);
		primaryStage.setTitle("扫地机器人");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void moveRobot() {
		robotCanvas.setTranslateX(robot.getiPos() * CELL_SIZE);
		robotCanvas.setTranslateY(robot.getjPos() * CELL_SIZE);
	}

	public StackPane spacePane() {
		for (int i = 0; i < problem.getHeight(); i++) {
			spaceGc.strokeLine(0, CELL_SIZE * i, CELL_SIZE * problem.getWidth(), CELL_SIZE * i);
		}
		for (int i = 0; i < problem.getWidth() - 1; i++) {
			spaceGc.strokeLine(CELL_SIZE * (i + 1), 0, CELL_SIZE * (i + 1), CELL_SIZE * problem.getHeight());
		}

		RobotProblem.CellState cells[][] = problem.getCells();
		for (int i = 0; i < problem.getWidth(); i++) {
			for (int j = 0; j < problem.getHeight(); j++) {
				if (cells[i][j] == RobotProblem.CellState.TRASH) {
					spaceGc.drawImage(trashImage, i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
			}
		}

		// Draw an Image
		// gc.strokeText("垃圾", 2, 20);

		robotGc.drawImage(robotImage, 0, 0, CELL_SIZE - 10, CELL_SIZE - 10);
		StackPane spacePane = new StackPane(spaceCanvas);
		spacePane.setAlignment(Pos.TOP_LEFT);
		spacePane.getChildren().add(robotCanvas);
		moveRobot();
		return spacePane;
	}

//	public static Image image(String path) {
//		try {
//			return new Image(new File(path).toURI().toURL().toString());
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

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
		Label scoreLabel = new Label(" 得分 ");
		scoreField.setPrefColumnCount(4);
		scoreField.setEditable(false);
		commandPane.getChildren().addAll(startButton, stepButton, maxStepLabel, maxStepField, intervalTimeLabel,
				intervalTimeField, stepLabel, stepField, scoreLabel, scoreField);
		startButton.setOnAction((e) -> {
			while (step < Integer.parseInt(maxStepField.getText())) {
				stepRun();
				// try {
				// Thread.sleep(Long.parseLong(intervalTimeField.getText()));
				// } catch (NumberFormatException | InterruptedException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
			}
		});
		stepButton.setOnAction((e) -> {
			stepRun();
		});

		return commandPane;
	}

	public void stepRun() {
		step++;
		RobotProblem.RobotAction action = solution.getRobotAction();
		System.out.println(action);
		int oldi = robot.getiPos();
		int oldj = robot.getjPos();
		System.out.println("Before robot i = " + oldi + " j = " + oldj);
		int clean = robot.takeAction(action);
		System.out.println("After robot i = " + robot.getiPos() + " j = " + robot.getjPos());
		if (clean == 1) {
			spaceGc.drawImage(removedImage, robot.getiPos() * CELL_SIZE, robot.getjPos() * CELL_SIZE, CELL_SIZE / 2,
					CELL_SIZE / 2);
		} else if (clean == 0) {
			spaceGc.strokeLine(oldi * CELL_SIZE + CELL_SIZE / 2, oldj * CELL_SIZE + CELL_SIZE / 2,
					robot.getiPos() * CELL_SIZE + CELL_SIZE / 2, robot.getjPos() * CELL_SIZE + CELL_SIZE / 2);

		}
		score += clean;
		scoreField.setText(Integer.toString(score));
		stepField.setText(Integer.toString(step));
		moveRobot();
	}

	public static Button imageButton(String path) {
		ImageView icon = new ImageView(UIGadgets.image(path));
		icon.setFitHeight(30);
		icon.setFitWidth(30);
		return new Button(null, icon);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
