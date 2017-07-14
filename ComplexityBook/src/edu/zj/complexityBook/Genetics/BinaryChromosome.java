package edu.zj.complexityBook.Genetics;

import java.util.BitSet;

public abstract class BinaryChromosome extends Chromosome {
	private final BitSet bsGenes;
	private final int length;
	public BinaryChromosome(byte[] genes) {
		super(null);
		bsGenes = new BitSet(genes.length);
		for (int i = 0; i < genes.length; i++) {
			setGene(i, genes[i]);
		}
		length=genes.length;
	}

	public BinaryChromosome(int length) {
		super(null);
		bsGenes = new BitSet(length);
		for (int i = 0; i < length; i++) {
			bsGenes.set(i, random.nextBoolean());
		}
		this.length=length;
	}

	@Override
	public void setGene(int index, int gene) {
		if (gene == 0)
			bsGenes.set(index, false);
		else
			bsGenes.set(index, true);
	}

	@Override
	public int getLength() {
		return length;
	}

	@Override
	public int getGene(int index) {
		return bsGenes.get(index) ? 1 : 0;
	}

	public byte[] getGenes() {
		byte[] genes=new byte[getLength()];
		for (int i=0;i<genes.length;i++) {
			genes[i]=(byte) getGene(i);
		}
		return genes;
	}

}
