package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.RandomUtil;

public class QuotientGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    private HeuristicEvaluationGene dividend;
    private HeuristicEvaluationGene divisor;

    public QuotientGene(HeuristicEvaluationGene dividend, HeuristicEvaluationGene divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
    }

    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .02) {
            return NewGeneGenerator.creatNewGene();
        }
        return new QuotientGene(dividend.replicateWithMutation(), divisor.replicateWithMutation());
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        double divisorEvaluation = divisor.evaluate(gameBoard);
        return divisorEvaluation == 0 ? 0 : dividend.evaluate(gameBoard) / divisorEvaluation;
    }

    @Override
    public String pretty() {
        return "(" + dividend.pretty() + " * " + divisor.pretty() + ")";
    }
    
}
