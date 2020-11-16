package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.RandomUtil;

public class RandomGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .02) {
            return NewGeneGenerator.creatNewGene();
        }
        return new RandomGene();
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        return RandomUtil.randomDouble(0d, 1d);
    }

    @Override
    public String pretty() {
        return "random 0 to 1";
    }
    
}
