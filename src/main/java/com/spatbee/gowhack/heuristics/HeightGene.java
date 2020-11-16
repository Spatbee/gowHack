package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.RandomUtil;
import com.spatbee.gowhack.Token;

public class HeightGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    private Token token;

    public HeightGene(Token token) {
        this.token = token;
    }

    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .02) {
            return NewGeneGenerator.creatNewGene();
        }
        if(r < .05) {
            return new HeightGene(Token.values()[RandomUtil.randomInt(Token.values().length)]);
        }
        return new HeightGene(token);
    }

    @Override
    public Double evaluate(GameBoardEvaluationWrapper gameBoard) {
        return gameBoard.getAverageHeightOfTokenType(token);
    }

    @Override
    public String pretty() {
        return "average height of " + token.name();
    }
    
}
