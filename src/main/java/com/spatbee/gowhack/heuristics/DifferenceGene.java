package com.spatbee.gowhack.heuristics;

public class DifferenceGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;

    private HeuristicEvaluationGene minuend;
    private HeuristicEvaluationGene subtrahend;

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
        return "(" + minuend.pretty() + " - " + subtrahend.pretty() + ")";
    }
    
}
