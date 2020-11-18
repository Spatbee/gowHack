package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.RandomUtil;
import com.spatbee.gowhack.Token;
import com.spatbee.gowhack.heuristics.gene.ConstantGene;
import com.spatbee.gowhack.heuristics.gene.CountGene;
import com.spatbee.gowhack.heuristics.gene.DifferenceGene;
import com.spatbee.gowhack.heuristics.gene.FiveMatchGene;
import com.spatbee.gowhack.heuristics.gene.FourMatchGene;
import com.spatbee.gowhack.heuristics.gene.HeightGene;
import com.spatbee.gowhack.heuristics.gene.HeuristicEvaluationGene;
import com.spatbee.gowhack.heuristics.gene.IfElseGene;
import com.spatbee.gowhack.heuristics.gene.NumberOfPairsGene;
import com.spatbee.gowhack.heuristics.gene.NumberOfTurnsGene;
import com.spatbee.gowhack.heuristics.gene.ProductGene;
import com.spatbee.gowhack.heuristics.gene.QuotientGene;
import com.spatbee.gowhack.heuristics.gene.ScoreGene;
import com.spatbee.gowhack.heuristics.gene.SumGene;
import com.spatbee.gowhack.heuristics.gene.TurnsLeftGene;

public class NewGeneGenerator {
    
    private NewGeneGenerator() {
        //private constructor to hide implicit public one
    }
    
    public static HeuristicEvaluationGene creatNewGene() {
        if(RandomUtil.randomDouble(0d, 1d) < .42) {
            return createNewNonterminalGene();
        }
        return createNewTerminalGene();
    }

    private static HeuristicEvaluationGene createNewTerminalGene() {
        int r = RandomUtil.randomInt(9);
        switch(r) {
            case 0:
                return new ConstantGene(RandomUtil.randomDouble(-5d, 5d));
            case 1:
                return new CountGene(Token.values()[RandomUtil.randomInt(Token.values().length)]);
            case 2:
                return new FiveMatchGene();
            case 3:
                return new FourMatchGene();
            case 4:
                return new HeightGene(Token.values()[RandomUtil.randomInt(Token.values().length)]);
            case 5:
                return new NumberOfTurnsGene();
            case 6:
                return new ScoreGene();
            case 7:
                return new TurnsLeftGene();
            case 8:
                return new NumberOfPairsGene();
            default:
                return null; //this will never happen
        }
    }
    
    private static HeuristicEvaluationGene createNewNonterminalGene() {
        int r = RandomUtil.randomInt(5);
        switch (r) {
            case 0:
                return new DifferenceGene(creatNewGene(), creatNewGene());
            case 1:
                return new IfElseGene(creatNewGene(), creatNewGene(), creatNewGene());
            case 2:
                return new ProductGene(creatNewGene(), creatNewGene());
            case 3:
                return new SumGene(creatNewGene(), creatNewGene());
            case 4:
                return new QuotientGene(creatNewGene(), creatNewGene());
            default:
                return null; //this will never happen
        }
    }
}
