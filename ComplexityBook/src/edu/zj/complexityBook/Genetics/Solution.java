package edu.zj.complexityBook.Genetics;

public abstract class Solution {
	public abstract <C extends Chromosome> C encode();
	@SuppressWarnings("unchecked")
	public <P extends Problem> P getProblem() {
		return (P) Problem.getProblem();
	}
}
