package com.spatbee.gowhack.heuristics;

public class SumGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    private HeuristicEvaluationGene addend1;
    private HeuristicEvaluationGene addend2;

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
        return "(" + addend1.pretty() + " * " + addend2.pretty() + ")";
    }
    
}
