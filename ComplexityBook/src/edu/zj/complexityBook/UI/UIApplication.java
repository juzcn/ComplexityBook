package edu.zj.complexityBook.UI;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import edu.zj.utils.UIGadgets;
import edu.zj.utils.Wrapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UIApplication extends Application {
	private AppConfig config;
	private Button startButton = UIGadgets.imageButton(null, "resources/pictures/1498914398_Play1Normal.png", 15, 15);
	private Button stopButton = UIGadgets.imageButton(null, "resources/pictures/1498914718_Stop1NormalRed.png", 15, 15);
	private Button stepButton = UIGadgets.imageButton(null, "resources/pictures/1498915380_StepForwardPressed.png", 15,
			15);
	private Button setupButton = UIGadgets.imageButton(null, "resources/pictures/setup-folder-icon.png", 15, 15);
	private TextField maxStepsField;
	private TextField currentStepField = new TextField();
	private TextField intervalTimeField;
	private TabPane viewTabs;
	private Wrapper<Boolean> stop = new Wrapper<>(false);
	private Stage primaryStage;
	private BorderPane mainPane = new BorderPane();

	@Override
	public void start(Stage primaryStage) throws Exception {
		// config begin
		this.primaryStage = primaryStage;
		List<String> args = getParameters().getRaw();
		config = (AppConfig) Class.forName(args.get(0)).getConstructor().newInstance();
		maxStepsField = new TextField(Integer.toString(config.getMaxSteps()));
		intervalTimeField = new TextField(Long.toString(config.getIntervalTime()));

		System.out.println(config);
		// config end
		// layout begin
		mainPane.setTop(top());
		mainPane.setLeft(left());
		mainPane.setBottom(bottom());
		mainPane.setCenter(center());
		Scene scene = new Scene(mainPane);
		// chart.setId("bifurcation-diagram");
		scene.getStylesheets().add("resources/chart.css");

		primaryStage.setTitle(config.getModel().getTitle());
		primaryStage.setScene(scene);
		primaryStage.show();
		// layout end
	}

	protected Node top() {
		HBox hbox = new HBox(8);
		setupButton.setTooltip(new Tooltip("加载参数"));
		startButton.setTooltip(new Tooltip("运行"));
		stopButton.setTooltip(new Tooltip("停止"));
		stepButton.setTooltip(new Tooltip("步进"));
		hbox.setFillHeight(true);
		hbox.setAlignment(Pos.CENTER_LEFT);
		maxStepsField.setPrefColumnCount(4);
		currentStepField.setPrefColumnCount(4);
		intervalTimeField.setPrefColumnCount(4);
		currentStepField.setDisable(true);
		hbox.getChildren().addAll(setupButton, startButton, stopButton, stepButton, new Label("间隔时间"),
				intervalTimeField, new Label("当前步"), currentStepField, new Label("运行步数"), maxStepsField);
		for (FieldInfo fi:config.getViewParams().getFieldsInfo()) {
			hbox.getChildren().addAll(fi.getLabel(),fi.getControl());
		}

		setupButton.setOnAction((e) -> {
			stop.setValue(true);
			config.getViewParams().validation();
			config.getModelParams().validation();
			mainPane.setCenter(center());
			primaryStage.sizeToScene();
			System.out.println("Setup completed");
		});
		stepButton.setOnAction((e) -> {
			config.getModel().stepRun();
			// final CountDownLatch doneLatch = new CountDownLatch(1);
			Platform.runLater(() -> {
				currentStepField.setText(Integer.toString(config.getModel().getStep()));
				for (int i = 0; i < config.getViewCount(); i++) {
					config.getView(i).redraw();
				}
			});

		});
		startButton.setOnAction((e) -> {
			// if started, stop it
			stop.setValue(true);
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					while (config.getModel().getStep() < Integer.parseInt(maxStepsField.getText())
							&& !stop.getValue()) {
						config.getModel().stepRun();
						final CountDownLatch doneLatch = new CountDownLatch(1);
						Platform.runLater(() -> {
							currentStepField.setText(Integer.toString(config.getModel().getStep()));
							for (int i = 0; i < config.getViewCount(); i++) {
								config.getView(i).redraw();
							}
							doneLatch.countDown();
						});
						try {
							doneLatch.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Thread.sleep(Long.parseLong(intervalTimeField.getText()));
					}
					return null;
				}
			};
			Thread thread = new Thread(task);
			thread.setDaemon(true);
			stop.setValue(false);
			thread.start();
		});
		stopButton.setOnAction((e) -> {
			stop.setValue(true);
		});
		return hbox;
	}

	protected Node left() {
		GridPane gp = new GridPane();
		gp.setHgap(8);
		for (int i=0;i<config.getModelParams().getFieldsInfo().length;i++) {
			gp.addRow(i, config.getModelParams().getFieldsInfo()[i].getLabel(), config.getModelParams().getFieldsInfo()[i].getControl());
		}
		return gp;

	}

	protected Node bottom() {
		FlowPane fp = new FlowPane();
		fp.setAlignment(Pos.CENTER);
		fp.getChildren().add(new Label("首都经济贸易大学 信息学院 张军 zhangjun@cueb.edu.cn(C)2017"));
		return fp;
	}

	protected Node center() {
		viewTabs = new TabPane();
		Tab t;
		config.load();
		currentStepField.setText(Integer.toString(config.getModel().getStep()));
		for (int i = 0; i < config.getViewCount(); i++) {
			t = new Tab(config.getView(i).getTitle());
			t.setClosable(false);
			t.setContent(config.getView(i).getNode());
			viewTabs.getTabs().add(t);
			config.getView(i).show();
		}
		return viewTabs;
	}

}
