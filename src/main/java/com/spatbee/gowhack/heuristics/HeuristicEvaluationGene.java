package com.spatbee.gowhack.heuristics;

import java.io.Serializable;

public interface HeuristicEvaluationGene extends Serializable {
    
    public HeuristicEvaluationGene replicateWithMutation();

    public Double evaluate(GameBoardEvaluationWrapper gameBoard);

    public String pretty();

}
