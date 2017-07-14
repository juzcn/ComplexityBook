package edu.zj.complexityBook.Genetics;
// 出现特殊符号问题 " = 43, /n=10, /t=8
public class Quine1 {
	public static void main(String[] args) {
		String s = "package edu.zj.compplexityBook.utils;\n\npublic class Quine {\n\tpublic static void main (String[] args) {\n\t\tString s = \"%1$s\" \n\t\tSystem.out.printf(s);\n\t}\n}";
		System.out.printf(s,s);
	}
}