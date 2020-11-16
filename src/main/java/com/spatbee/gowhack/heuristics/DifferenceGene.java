package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.RandomUtil;

public class DifferenceGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;

    private HeuristicEvaluationGene minuend;
    private HeuristicEvaluationGene subtrahend;

    public DifferenceGene(HeuristicEvaluationGene minuend, HeuristicEvaluationGene subtrahend) {
        this.minuend = minuend;
        this.subtrahend = subtrahend;
    }

    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .02) {
            return NewGeneGenerator.creatNewGene();
        }
        return new DifferenceGene(minuend.replicateWithMutation(), subtrahend.replicateWithMutation());
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        return minuend.evaluate(gameBoard) - subtrahend.evaluate(gameBoard);
    }

    @Override
    public String pretty() {
        return "(" + minuend.pretty() + " - " + subtrahend.pretty() + ")";
    }
    
}
