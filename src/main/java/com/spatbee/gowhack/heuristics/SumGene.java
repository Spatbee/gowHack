package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.RandomUtil;

public class SumGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    private HeuristicEvaluationGene addend1;
    private HeuristicEvaluationGene addend2;

    public SumGene(HeuristicEvaluationGene addend1, HeuristicEvaluationGene addend2) {
        this.addend1 = addend1;
        this.addend2 = addend2;
    }

    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .02) {
            return NewGeneGenerator.creatNewGene();
        }
        return new SumGene(addend1.replicateWithMutation(), addend2.replicateWithMutation());
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        return addend1.evaluate(gameBoard) + addend2.evaluate(gameBoard);
    }

    @Override
    public String pretty() {
        return "(" + addend1.pretty() + " * " + addend2.pretty() + ")";
    }
    
}
