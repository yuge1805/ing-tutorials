package com.yuge.ing.genetic.algorithm.model;

import com.yuge.ing.genetic.algorithm.context.ChromosomeContext;

import java.util.List;

/**
 * @author: yuge
 * @date: 2022/12/30
 **/
public abstract class AbstractChromosome<T extends Chromosome<T, G, C>, G extends Gene, C extends ChromosomeContext> implements Chromosome<T, G, C>{

    /**
     * 基因集合
     */
    protected List<G> geneList;

    /**
     * 上下文
     */
    protected C context;

    /**
     * 适应度
     */
    protected double fitness;

    public AbstractChromosome() {
    }

    public AbstractChromosome(List<G> geneList) {
        this.geneList = geneList;
    }

    public AbstractChromosome(C context) {
        this.context = context;
    }

    @Override
    public List<G> getGeneList() {
        return geneList;
    }

    @Override
    public void setContext(C context) {
        this.context = context;
    }

    @Override
    public C getContext() {
        return context;
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

}
