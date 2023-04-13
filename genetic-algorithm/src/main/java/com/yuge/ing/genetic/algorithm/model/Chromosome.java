package com.yuge.ing.genetic.algorithm.model;

import com.yuge.ing.genetic.algorithm.context.ChromosomeContext;

import java.util.List;

/**
 *
 * @author: yuge
 * @date: 2022/12/29
 **/
public interface Chromosome<T extends Chromosome<T, G, C>, G extends Gene, C extends ChromosomeContext> {

    /**
     * 设置上下文
     *
     * @param context
     */
    void setContext(C context);

    /**
     * 获取上下文
     *
     * @return
     */
    C getContext();

    /**
     * 通过上下文初始化基因集合
     *
     * @return
     */
    T initGeneListFromContext();

    /**
     * 获取基因集合
     *
     * @return
     */
    List<G> getGeneList();

    /**
     * 获取适应度
     *
     * @return
     */
    double getFitness();

    /**
     * 交叉
     *
     * @param chromosome
     * @return 新染色体
     */
    T crossover(T chromosome);

    /**
     * 变异
     *
     * @return 新染色体
     */
    T mutation();

}
