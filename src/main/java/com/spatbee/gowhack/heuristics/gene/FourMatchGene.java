package com.spatbee.gowhack.heuristics.gene;

import com.spatbee.gowhack.RandomUtil;
import com.spatbee.gowhack.heuristics.GameBoardEvaluationWrapper;
import com.spatbee.gowhack.heuristics.NewGeneGenerator;

public class FourMatchGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .04) {
            return NewGeneGenerator.creatNewGene();
        }
        return new FourMatchGene();
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        return gameBoard.getNumberOfFourMatchTurns();
    }

    @Override
    public String pretty() {
        return "turns resulting in a 4 match";
    }
    
}
