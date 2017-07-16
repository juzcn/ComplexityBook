package edu.zj.complexityBook.CellularAutomata.GameOfLife;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import edu.zj.complexityBook.CA.CASparseMatrix;
import edu.zj.complexityBook.CA.I_CACell;
import edu.zj.complexityBook.CellularAutomata.GameOfLife.GolCell.State;
import edu.zj.utils.SparseMatrix.Position;

public class GolData extends CASparseMatrix<GolCell.State, BigInteger> {

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
			Position<BigInteger>[] neighbs = neighboursPos8(entry.getRow(), entry.getColumn());
			for (int i = 0; i < neighbs.length; i++) {
				cells.add(new GolCell(this, neighbs[i].getRow(), neighbs[i].getColumn(),
						getData(neighbs[i].getRow(), neighbs[i].getColumn())));
			}
		}
		this.cells=new I_CACell[cells.size()]; 
		return cells.toArray(this.cells);
	}

}
