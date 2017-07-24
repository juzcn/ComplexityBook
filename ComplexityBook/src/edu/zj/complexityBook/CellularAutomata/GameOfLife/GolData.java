package edu.zj.complexityBook.CellularAutomata.GameOfLife;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import edu.zj.complexityBook.CA.I_CACell;
import edu.zj.complexityBook.CA.I_CAData;
import edu.zj.complexityBook.CellularAutomata.GameOfLife.GolCell.State;
import edu.zj.utils.Grid.Data.GridPos;
import edu.zj.utils.Grid.Data.SparseGrid;

public class GolData extends SparseGrid<GolCell.State, BigInteger> implements I_CAData {
	private I_CACell[] cells;
	public I_CACell[] getCells() {
		return cells;
	}


	public void setCells(I_CACell[] cells) {
		this.cells = cells;
	}


	public GolData(State asNull) {
		super(asNull);
		// TODO Auto-generated constructor stub
	}

	
	public I_CACell[] cells() {
		Set<I_CACell> cells = new HashSet<>();
		Set<Element> all = get(State.alive);
		// System.out.println("All alive Cell Size "+all.size());
		for (Element entry : all) {
			cells.add(new GolCell(this,entry.getRow(),entry.getColumn(),entry.getData()));
			GridPos<BigInteger>[] neighbs = neighbs8(entry.getRow(), entry.getColumn());
			for (int i = 0; i < neighbs.length; i++) {
				cells.add(new GolCell(this, neighbs[i].getRow(), neighbs[i].getColumn(),
						getData(neighbs[i].getRow(), neighbs[i].getColumn())));
			}
		}
		this.cells=new I_CACell[cells.size()]; 
		return cells.toArray(this.cells);
	}

}
