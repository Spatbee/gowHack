package com.spatbee.gowhack.heuristics;

public class ProductGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    private HeuristicEvaluationGene multiplier;
    private HeuristicEvaluationGene multiplicand;

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
        return "(" + multiplier.pretty() + " * " + multiplicand.pretty() + ")";
    }
    
}
