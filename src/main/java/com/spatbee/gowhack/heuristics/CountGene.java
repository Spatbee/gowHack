package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.RandomUtil;
import com.spatbee.gowhack.Token;

public class CountGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;

    private Token token;

    public CountGene(Token token) {
        this.token = token;
    }

    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .02) {
            return NewGeneGenerator.creatNewGene();
        }
        if(r < .05) {
            return new CountGene(Token.values()[RandomUtil.randomInt(Token.values().length)]);
        }
        return new CountGene(token);
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        return gameBoard.getNumberOfToken(token);
    }

    @Override
    public String pretty() {
        return "number of " + token.name();
    }
    
}