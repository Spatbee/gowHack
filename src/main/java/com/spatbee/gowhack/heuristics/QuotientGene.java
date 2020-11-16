package com.spatbee.gowhack.heuristics;

public class QuotientGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    private HeuristicEvaluationGene dividend;
    private HeuristicEvaluationGene divisor;

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
        return "(" + dividend.pretty() + " * " + divisor.pretty() + ")";
    }
    
}
