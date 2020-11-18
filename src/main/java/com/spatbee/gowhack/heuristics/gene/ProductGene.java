package com.spatbee.gowhack.heuristics.gene;

import com.spatbee.gowhack.RandomUtil;
import com.spatbee.gowhack.heuristics.GameBoardEvaluationWrapper;
import com.spatbee.gowhack.heuristics.NewGeneGenerator;

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
        if(r < .04) {
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
