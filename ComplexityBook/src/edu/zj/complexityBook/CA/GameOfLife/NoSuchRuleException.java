package edu.zj.complexityBook.CA.GameOfLife;

public class NoSuchRuleException extends RuntimeException{
	private static final long serialVersionUID = -6535126505056913810L;

	public NoSuchRuleException(String message) {
		super(message);
	}

}
