package com.spatbee.gowhack.heuristics;

public class IfElseGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;

    private HeuristicEvaluationGene branchGene;
    private HeuristicEvaluationGene trueGene;
    private HeuristicEvaluationGene falseGene;
    
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
        return "(" + branchGene.pretty() + " > 1 ? " + trueGene.pretty() + " : " + falseGene.pretty() + ")";
    }
    
}
