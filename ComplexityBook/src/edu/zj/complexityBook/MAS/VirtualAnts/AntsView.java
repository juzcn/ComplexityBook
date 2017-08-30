package edu.zj.complexityBook.MAS.VirtualAnts;

import edu.zj.complexityBook.MAS.Grid.GridWorldDisplay;
import edu.zj.utils.UIGadgets;
import edu.zj.utils.Grid.Model.GridPos;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class AntsView extends GridWorldDisplay<AntWorld,Integer,AntAgent>
 {

	public AntsView(AntWorld world, AntsConfig.ViewParams params) {
		super(world.getRowCount(), world.getColumnCount(), params.getCellSize(), params.getBorderColor(), world);
		this.setTitle("–Èƒ‚¬Ï“œœµÕ≥—›±‰");
	}

	private Image antImage = UIGadgets.image("resources/pictures/119436.gif.png");

	@Override
	public void drawAgent(GridPos pos, AntAgent ga) {
		
		getAgentGC().drawImage(antImage, pos.getColumn()*getCellSize(), pos.getRow()*getCellSize(), getCellSize()/2, getCellSize()/2);
	}

	@Override
	public Color cellFill(Integer data) {
		if (data == 1)
			return Color.BLACK;
		return Color.WHITE;
	}
}
