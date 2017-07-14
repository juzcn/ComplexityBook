package edu.zj.complexityBook.Genetics.BagProblem;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;;

public class BagProblemUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane gp = new GridPane();
		ScrollPane sp=new ScrollPane(gp);
	
		Label wLabel = new Label("重量约束 = ");
		TextField wField = new TextField("200");
		Label nLabel = new Label("物品数量 = ");
		TextField nField = new TextField("10");
		Label wTitleLabel = new Label("重量");
		Label pTitleLabel = new Label("价值");
		int n = Integer.parseInt(nField.getText());
		ArrayList<TextField> list = new ArrayList<>();
		list.add(new TextField("77"));
		list.add(new TextField("92"));
		list.add(new TextField("22"));
		list.add(new TextField("22"));
		list.add(new TextField("29"));
		list.add(new TextField("87"));
		list.add(new TextField("50"));
		list.add(new TextField("46"));
		list.add(new TextField("99"));
		list.add(new TextField("90"));
		list.add(new TextField("101"));
		list.add(new TextField("33"));
		list.add(new TextField("66"));
		list.add(new TextField("22"));
		list.add(new TextField("59"));
		list.add(new TextField("47"));
		list.add(new TextField("20"));
		list.add(new TextField("96"));
		list.add(new TextField("59"));
		list.add(new TextField("90"));
		Button runButton = new Button("最优求解");
		Label totalWeightLabel = new Label();
		Label totalPriceLabel = new Label();
		nField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) { // when focus lost
				int oldSize = list.size() / 2;
				int n1 = Integer.parseInt(nField.getText()) - oldSize;
				if (n1 > 0) {
					for (int i = 0; i < n1; i++) {
						TextField c0 = new TextField();
						TextField c1 = new TextField();
						// gp.addRow(3+(oldSize+i)/2,c0,c1);
						gp.add(c0, 0, 3 + oldSize + i);
						gp.add(c1, 1, 3 + oldSize + i);
						list.add(c0);
						list.add(c1);
					}
					gp.getChildren().remove(runButton);
					gp.getChildren().remove(totalWeightLabel);
					gp.getChildren().remove(totalPriceLabel);
					gp.addRow(3 + list.size(), totalWeightLabel,totalPriceLabel);
					gp.addRow(4 + list.size(), runButton);

				} else if (n1 < 0) {
					for (int i = oldSize - 1; i >= oldSize + n1; i--) {
						System.out.println("Remove row i=" + i);
						GridPane.clearConstraints(list.get(2 * i + 1));
						GridPane.clearConstraints(list.get(2 * i));
						gp.getChildren().removeAll(list.get(2 * i + 1), list.get(2 * i));
						list.remove(2 * i + 1);
						list.remove(2 * i);
					}
				}
			}
		});

		gp.addRow(0, wLabel, wField);
		gp.addRow(1, nLabel, nField);
		gp.addRow(2, wTitleLabel, pTitleLabel);
		for (int i = 0; i < n; i++) {
			gp.addRow(3 + i, list.get(2 * i), list.get(2 * i + 1));
		}
		
		gp.addRow(3 + n, totalWeightLabel,totalPriceLabel);
		gp.addRow(4 + n, runButton);
		
		Label crossoverRateLabel = new Label("交叉系数");
		TextField crossoverRateField = new TextField("0.2");
		Label mutationRateLabel = new Label("变异系数");
		TextField mutationRateField = new TextField("0.01");
		Label populationSizeLabel = new Label("初始种群数量");
		TextField populationSizeField = new TextField("100");
		Label elitismCountLabel = new Label("保留数");
		TextField elitismCountField = new TextField("2");
		gp.addRow(5 + n, crossoverRateLabel,crossoverRateField);
		gp.addRow(6 + n, mutationRateLabel,mutationRateField);
		gp.addRow(7 + n, populationSizeLabel,populationSizeField);
		gp.addRow(8 + n, elitismCountLabel,elitismCountField);
		Button runGA=new Button("遗传算法求解");
		gp.addRow(9 + n,runGA);
		Scene s = new Scene(sp);
		runButton.setOnAction((e) -> {
			int nn = Integer.parseInt(nField.getText());
			BagProblem.Bag[] bags = new BagProblem.Bag[nn];
			for (int i = 0; i < nn; i++) {
				bags[i] = new BagProblem.Bag(Double.parseDouble(list.get(2 * i).getText()),
						Double.parseDouble(list.get(2 * i + 1).getText()));
			}
			BagProblem bagProblem = new BagProblem(Double.parseDouble(wField.getText()), bags);
			BPSolution result = bagProblem.resolve();
			for (int i = 0; i < result.getSelections().length; i++) {
				if (result.getSelections()[i] == 1) {
					list.get(2 * i).setStyle("-fx-background-color:cyan");
					list.get(2 * i+1).setStyle("-fx-background-color:cyan");
				}
			}
			totalWeightLabel.setText("总重量："+result.getTotalWeight());
			totalPriceLabel.setText("总价值："+result.getTotalPrice());
		});
		
		runGA.setOnAction((e) -> {
			int nn = Integer.parseInt(nField.getText());
			BagProblem.Bag[] bags = new BagProblem.Bag[nn];
			for (int i = 0; i < nn; i++) {
				bags[i] = new BagProblem.Bag(Double.parseDouble(list.get(2 * i).getText()),
						Double.parseDouble(list.get(2 * i + 1).getText()));
			}
			BagProblem bagProblem = new BagProblem(Double.parseDouble(wField.getText()), bags);
			BPSolution result = bagProblem.resolve(Integer.parseInt(populationSizeField.getText()),
					Integer.parseInt(elitismCountField.getText()),
					Double.parseDouble(crossoverRateField.getText()),
					Double.parseDouble(mutationRateField.getText())
					);
			for (int i = 0; i < result.getSelections().length; i++) {
				if (result.getSelections()[i] == 1) {
					list.get(2 * i).setStyle("-fx-background-color:cyan");
					list.get(2 * i+1).setStyle("-fx-background-color:cyan");
				}
			}
			totalWeightLabel.setText("总重量："+result.getTotalWeight());
			totalPriceLabel.setText("总价值："+result.getTotalPrice());
		});

		primaryStage.setTitle("0-1背包问题");
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
