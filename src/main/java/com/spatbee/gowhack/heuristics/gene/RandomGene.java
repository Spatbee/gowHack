package com.spatbee.gowhack.heuristics.gene;

import com.spatbee.gowhack.RandomUtil;
import com.spatbee.gowhack.heuristics.GameBoardEvaluationWrapper;
import com.spatbee.gowhack.heuristics.NewGeneGenerator;

public class RandomGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .04) {
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
