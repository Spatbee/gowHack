package com.spatbee.gowhack.heuristics.gene;

import com.spatbee.gowhack.RandomUtil;
import com.spatbee.gowhack.heuristics.GameBoardEvaluationWrapper;
import com.spatbee.gowhack.heuristics.NewGeneGenerator;

public class ConstantGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;

    private Double value;

    public ConstantGene(Double value) {
        this.value = value;
    }

    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .04) {
            return NewGeneGenerator.creatNewGene();
        }
        if(r < .1) {
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
