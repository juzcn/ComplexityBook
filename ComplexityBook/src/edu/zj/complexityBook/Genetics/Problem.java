package edu.zj.complexityBook.Genetics;

public abstract class Problem {
	private static Problem problem;

	public Problem() {
		problem = this;
	}

	@SuppressWarnings("unchecked")
	public static <P extends Problem> P getProblem() {
		return (P) problem;
	}
	
	public abstract <S extends Solution> S resolve() ;

}
