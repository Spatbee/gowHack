package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.Token;

public class HeightGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    private Token token;

    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper boardGame) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String pretty() {
        return "average height of " + token.name();
    }
    
}
