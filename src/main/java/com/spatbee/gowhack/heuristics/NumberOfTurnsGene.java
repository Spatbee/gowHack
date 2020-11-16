package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.RandomUtil;

public class NumberOfTurnsGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .02) {
            return NewGeneGenerator.creatNewGene();
        }
        return new NumberOfTurnsGene();
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        return gameBoard.getNumberOfTurnsAvailable();
    }

    @Override
    public String pretty() {
        return "number of available turns";
    }
}
