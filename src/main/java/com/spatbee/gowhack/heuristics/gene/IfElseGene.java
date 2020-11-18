package com.spatbee.gowhack.heuristics.gene;

import com.spatbee.gowhack.RandomUtil;
import com.spatbee.gowhack.heuristics.GameBoardEvaluationWrapper;
import com.spatbee.gowhack.heuristics.NewGeneGenerator;

public class IfElseGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;

    private HeuristicEvaluationGene branchGene;
    private HeuristicEvaluationGene trueGene;
    private HeuristicEvaluationGene falseGene;
    
    public IfElseGene(HeuristicEvaluationGene branchGene, HeuristicEvaluationGene trueGene, HeuristicEvaluationGene falseGene) {
        this.branchGene = branchGene;
        this.trueGene = trueGene;
        this.falseGene = falseGene;
    }

    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .04) {
            return NewGeneGenerator.creatNewGene();
        }
        return new IfElseGene(branchGene.replicateWithMutation(), trueGene.replicateWithMutation(), falseGene.replicateWithMutation());
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        double branchGeneEvaluation = branchGene.evaluate(gameBoard);
        if(branchGeneEvaluation > 1) {
            return trueGene.evaluate(gameBoard);
        }
        return falseGene.evaluate(gameBoard);
    }

    @Override
    public String pretty() {
        return "(" + branchGene.pretty() + " > 1 ? " + trueGene.pretty() + " : " + falseGene.pretty() + ")";
    }
    
}
