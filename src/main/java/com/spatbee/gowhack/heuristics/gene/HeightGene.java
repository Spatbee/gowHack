package com.spatbee.gowhack.heuristics.gene;

import com.spatbee.gowhack.RandomUtil;
import com.spatbee.gowhack.Token;
import com.spatbee.gowhack.heuristics.GameBoardEvaluationWrapper;
import com.spatbee.gowhack.heuristics.NewGeneGenerator;

public class HeightGene implements HeuristicEvaluationGene {

    private static final long serialVersionUID = 1L;
    
    private Token token;

    public HeightGene(Token token) {
        this.token = token;
    }

    @Override
    public HeuristicEvaluationGene replicateWithMutation() {
        double r = RandomUtil.randomDouble(0, 1);
        if(r < .04) {
            return NewGeneGenerator.creatNewGene();
        }
        if(r < .1) {
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
