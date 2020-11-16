package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.RandomUtil;

public class ScoreGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .02) {
            return NewGeneGenerator.creatNewGene();
        }
        return new ScoreGene();
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        return gameBoard.getScore();
    }

    @Override
    public String pretty() {
        return "board score";
    }
    
}
