package edu.zj.test;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ResizeTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		Button changeButton = new Button("Change");
		Button createButton = new Button("Create");
		Button create1Button = new Button("Create1");
		pane.setCenter(center());
		pane.setTop(new HBox(changeButton, createButton,create1Button));

		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
		changeButton.setOnAction((e) -> {
			System.out.println("Size=" + tp.getTabs().size());

			tp.getTabs().get(0).setContent(new Canvas(100, 100));
			tp.getTabs().get(1).setContent(new Canvas(30, 30));
			primaryStage.sizeToScene();
			primaryStage.show();
			// primaryStage.getScene()
		});
		createButton.setOnAction((e) -> {
			Tab t1 = new Tab("Tab1 100 100");
			t1.setContent(new Canvas(100, 100));
			Tab t2 = new Tab("Tab2 30 30");
			t2.setContent(new Canvas(30, 30));
			tp.getTabs().clear();
			tp.getTabs().addAll(t1, t2);
			primaryStage.sizeToScene();
			primaryStage.show();
			// primaryStage.getScene()
		});
		create1Button.setOnAction((e) -> {
			tp=new TabPane();
			Tab t1 = new Tab("Tab1 100 100");
			t1.setContent(new Canvas(100, 100));
			Tab t2 = new Tab("Tab2 30 30");
			t2.setContent(new Canvas(30, 30));
			pane.setCenter(tp);
			tp.getTabs().addAll(t1, t2);
			primaryStage.sizeToScene();
			
			primaryStage.show();
			// primaryStage.getScene()
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

	TabPane tp = new TabPane();

	private Node center() {
		Tab t1 = new Tab("Tab1 20 20");
		t1.setContent(new Canvas(20, 20));
		Tab t2 = new Tab("Tab2 600 600");
		t2.setContent(new Canvas(600, 600));
		tp.getTabs().addAll(t1, t2);
		return tp;
	}
}
