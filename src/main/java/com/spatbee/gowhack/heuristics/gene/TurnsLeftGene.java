package com.spatbee.gowhack.heuristics.gene;

import com.spatbee.gowhack.RandomUtil;
import com.spatbee.gowhack.heuristics.GameBoardEvaluationWrapper;
import com.spatbee.gowhack.heuristics.NewGeneGenerator;

public class TurnsLeftGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .04) {
            return NewGeneGenerator.creatNewGene();
        }
        return new TurnsLeftGene();
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        return gameBoard.getTurnsLeft();
    }

    @Override
    public String pretty() {
        return "number of turns left";
    }
}
