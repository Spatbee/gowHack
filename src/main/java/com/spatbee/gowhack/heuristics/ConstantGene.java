package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.RandomUtil;

public class ConstantGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;

    private Double value;

    public ConstantGene(Double value) {
        this.value = value;
    }

    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .02) {
            return NewGeneGenerator.creatNewGene();
        }
        if(r < .05) {
            return new ConstantGene(value + RandomUtil.randomDouble(-5d, 5d));
        }
        return new ConstantGene(value);
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        return value;
    }

    @Override
    public String pretty() {
        return String.format("%.4f", value);
    }
    
}
