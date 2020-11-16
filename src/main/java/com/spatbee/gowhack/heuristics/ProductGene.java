package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.RandomUtil;

public class ProductGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    private HeuristicEvaluationGene multiplier;
    private HeuristicEvaluationGene multiplicand;

    public ProductGene(HeuristicEvaluationGene multiplier, HeuristicEvaluationGene multiplicand) {
        this.multiplier = multiplier;
        this.multiplicand = multiplicand;
    }

    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .02) {
            return NewGeneGenerator.creatNewGene();
        }
        return new ProductGene(multiplier.replicateWithMutation(), multiplicand.replicateWithMutation());
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        return multiplier.evaluate(gameBoard) * multiplicand.evaluate(gameBoard);
    }

    @Override
    public String pretty() {
        return "(" + multiplier.pretty() + " * " + multiplicand.pretty() + ")";
    }
    
}
