package com.spatbee.gowhack.heuristics;

import com.spatbee.gowhack.RandomUtil;
import com.spatbee.gowhack.heuristics.gene.ConstantGene;
import com.spatbee.gowhack.heuristics.gene.HeuristicEvaluationGene;

public class NewGeneGenerator {
    
    private NewGeneGenerator() {
        //private constructor to hide implicit public one
    }
    
    public static HeuristicEvaluationGene creatNewGene() {
        return new ConstantGene(RandomUtil.randomDouble(-5d, 5d));
    }
}
