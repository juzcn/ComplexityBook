package edu.zj.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MandelbrotBW extends Application {
	public void draw(Canvas canvas) {
		PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();
		int width = (int) canvas.getWidth();
		int height = (int) canvas.getHeight();
		int  max = 1000;

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				double c_re = (col - width / 2) * 4.0 / width;
				double c_im = (row - height / 2) * 4.0 / height;
				double x = 0, y = 0;
				int iterations = 0;
				while (x * x + y * y < 4 && iterations < max) {
					double x_new = x * x - y * y + c_re;
					y = 2 * x * y + c_im;
					x = x_new;
					iterations++;
				}
				if (iterations < max)
					pw.setColor(col,row,Color.WHITE);
				else
					pw.setColor(col,row,Color.BLACK);
			}
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Canvas canvas = new Canvas(800, 800);
		Pane pane = new Pane(canvas);

		Scene scene = new Scene(pane);
		primaryStage.setTitle("Mandelbrot Set");
		primaryStage.setScene(scene);
		primaryStage.show();
		draw(canvas);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}