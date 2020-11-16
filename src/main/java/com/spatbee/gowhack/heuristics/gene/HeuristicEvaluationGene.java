package com.spatbee.gowhack.heuristics.gene;

import java.io.Serializable;

import com.spatbee.gowhack.heuristics.GameBoardEvaluationWrapper;

public interface HeuristicEvaluationGene extends Serializable {
    
    public HeuristicEvaluationGene replicateWithMutation();

    public Double evaluate(GameBoardEvaluationWrapper gameBoard);

    public String pretty();

}
