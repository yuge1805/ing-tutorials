package com.yuge.ing.genetic.algorithm;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yuge.ing.genetic.algorithm.context.ChromosomeContext;
import com.yuge.ing.genetic.algorithm.model.Chromosome;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: yuge
 * @date: 2022/12/29
 **/
@Data
public abstract class GeneticAlgorithm<T extends Chromosome, C extends ChromosomeContext> {

    /**
     * 交叉父亲数量
     */
    private static int CROSSOVER_PARENT_SIZE = 2;

    /**
     * 交叉概率
     */
    private float crossoverProbability = 0.8f;

    /**
     * 变异概率
     */
    private float mutationProbability = 0.05f;

    /**
     * 种群大小
     */
    private int populationSize = 50;

    /**
     * 迭代次数
     */
    private int iterations = 10000;

    /**
     * 预期适应度
     */
    private double expectedFitness;

    /**
     * 种群
     */
    private List<T> populations;

    /**
     * 上下文
     */
    private C context;

    /**
     * 最佳染色体
     */
    private T bestChromosome;

    private SecureRandom random = new SecureRandom();

    /**
     * 执行算法
     */
    public void run() {
        // 初始化种群
        this.initPopulation();
        int count = 0;
        while (count < iterations) {
            Optional<T> bestChromosomeOptional = this.getBestChromosomeFromPopulation();
            if (bestChromosomeOptional.isPresent()
                    && bestChromosomeOptional.get().getFitness() >= expectedFitness) {
                bestChromosome = bestChromosomeOptional.get();
                return;
            }
            // 种群演变
            this.evolvePopulation();
            count++;
        }
    }

    /**
     * 初始化种群
     */
    public void initPopulation() {
        populations = new ArrayList<>(populationSize);
        IntStream.range(0, populationSize)
                .forEach(i -> {
                    populations.set(i, newChromosome());
                });
    }

    /**
     * 创建新的染色体
     *
     * @return
     */
    abstract T newChromosome();

    /**
     * 获取种群中适应度最高的染色体
     *
     * @return
     */
    public Optional<T> getBestChromosomeFromPopulation() {
        if (CollectionUtils.isEmpty(populations)) {
            return Optional.empty();
        }
        return populations.stream()
                .filter(Objects::nonNull)
                .reduce((c, c2) -> {
                    return c.getFitness() < c2.getFitness() ? c2 : c;
                });
    }

    /**
     * 种群演变
     */
    public void evolvePopulation() {
        // 选择种群
        List<T> selectPopulations = this.selectPopulation();
        int needSize = populationSize - selectPopulations.size();
        // 生成子代
        List<T> offspringList = Lists.newArrayListWithCapacity(needSize);
        IntStream.range(0, needSize)
                .forEach(value -> {
                    List<T> parentList = this.selectCrossoverParent();
                    Assert.isTrue(parentList.size() == CROSSOVER_PARENT_SIZE, "invalid parent!");
                    // 子代
                    T offspring = null;
                    // 交叉
                    if (isCrossover()) {
                        offspring = this.crossover(parentList.get(0), parentList.get(1));
                    } else {
                        int randomParentIndex = random.nextInt(parentList.size());
                        offspring = parentList.get(randomParentIndex);
                    }
                    // 突变
                    if (isMutation()) {
                        offspring = this.mutation(offspring);
                    }
                    offspringList.add(offspring);
                });
        // 产生新种群，并替换
        List<T> newPopulations = Lists.newArrayListWithCapacity(populationSize);
        newPopulations.addAll(selectPopulations);
        newPopulations.addAll(newPopulations);
        populations = newPopulations;
    }

    /**
     * 种群选择
     * 使用算法：轮盘赌选择
     * 适应度较高的个体大概率保留，适应度较低的个体可能淘汰
     * todo 是否优化？ 保留最高适应度个体
     *
     * @return
     */
    public List<T> selectPopulation() {
        if (CollectionUtils.isEmpty(populations)) {
            return Collections.emptyList();
        }
        double totalFitness = populations.stream().filter(Objects::nonNull)
                .map(Chromosome::getFitness)
                .filter(Objects::nonNull)
                .reduce((fitness, fitness2) -> NumberUtil.add(fitness, fitness2))
                .orElseThrow(() -> new IllegalStateException("calculate total fitness error!"));
        // 累计概率
        double cumulativeProbability = 0;
        double[] cumulativeArray = new double[populations.size()];
        // 默认累计概率下限为0
        cumulativeArray[0] = cumulativeProbability;
        for (int i = 1; i < populations.size(); i++) {
            T t = populations.get(i);
            double probability = NumberUtil.div(t.getFitness(), totalFitness);
            cumulativeProbability = NumberUtil.add(cumulativeProbability, probability);
            cumulativeArray[i] = cumulativeProbability;
        }
        // 选中索引
        Set<Integer> selectedIndex = Sets.newHashSetWithExpectedSize(populations.size());
        IntStream.range(0, populations.size())
                .forEach(value -> {
                    double currentProbability = random.nextDouble();
                    for (int i = cumulativeArray.length -1; i >= 0; i--){
                        if (currentProbability > cumulativeArray[i]) {
                            selectedIndex.add(i);
                            break;
                        }
                    }
                });
        return Lists.newArrayList(selectedIndex)
                .stream()
                .filter(Objects::nonNull)
                .sorted()
                .map(index -> populations.get(index))
                .collect(Collectors.toList());
    }

    /**
     * 选择交叉的父亲
     *
     * @return 返回两个染色体
     */
    public List<T> selectCrossoverParent() {
        if (CollectionUtils.isEmpty(populations)) {
            return Collections.emptyList();
        }
        List<T> parentList = Lists.newArrayListWithCapacity(CROSSOVER_PARENT_SIZE);
        int firstIndex = random.nextInt(populations.size());
        int secondIndex = random.nextInt(populations.size());
        secondIndex = secondIndex != firstIndex ? secondIndex : (secondIndex + 1) % populations.size();
        parentList.add(populations.get(firstIndex));
        parentList.add(populations.get(secondIndex));
        return parentList;
    }

    /**
     * 是否交叉
     *
     * @return
     */
    public boolean isCrossover() {
        float probability = random.nextFloat();
        return probability < crossoverProbability;
    }

    /**
     * 是否变异
     *
     * @return
     */
    public boolean isMutation() {
        float probability = random.nextFloat();
        return probability < mutationProbability;
    }

    /**
     * 交叉
     *
     * @param t1
     * @param t2
     * @return
     */
    abstract T crossover(T t1, T t2);

    /**
     * 突变
     *
     * @param t
     * @return
     */
    abstract T mutation(T t);


}
