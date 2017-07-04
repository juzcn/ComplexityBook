package edu.zj.compplexityBook.Genetics;
// ？ 里面会造成回归
public class Quine0 {
	public static void main(String[] args) {
		String s = "package edu.zj.compplexityBook.utils;\n\npublic class Quine {\n\tpublic static void main (String[] args) {\n\t\tString s = \"?\" \n\t\tSystem.out.printf(s);\n\t}\n}";
		System.out.printf(s);
	}
}