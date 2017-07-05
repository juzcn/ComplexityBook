package edu.zj.compplexityBook.CA;

public class GolData extends CADataUnbounded<GolCellState> {
	@Override
	public void setData(GolCellState s, String row, String column) {
		if (s == GolCellState.alive)
			super.setData(s, row, column);
		else
			clear(row, column);
	}

	@Override
	public GolCellState getData(String row, String column) {
		GolCellState s = super.getData(row, column);
		if (s == null)
			return GolCellState.dead;
		else
			return s;
	}
}
