package com.spatbee.gowhack.heuristics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.spatbee.gowhack.heuristics.gene.HeuristicEvaluationGene;

public class GeneticAlgorithm {

    public void runPopulationWithRandomSeed() {
        List<HeuristicEvaluationGene> population = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            population.add(NewGeneGenerator.creatNewGene());
        }
        stepPopulation(population);
    }

    public void runPopulationFromFileSeed(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filePath)))) {
            Object fileObject = ois.readObject();
            
            if(fileObject instanceof List<?>) {
                List<HeuristicEvaluationGene> population = new ArrayList<>();
                for(Object readListObject : (List<?>) fileObject) {
                    if(readListObject instanceof HeuristicEvaluationGene) {
                        population.add((HeuristicEvaluationGene)readListObject);
                    } else {
                        throw new IOException("object in list was not of type HeuristicEvaluationGene");        
                    }
                }
                stepPopulation(population);
            } else {
                throw new IOException("object read was not of type List<?>");
            }
            List<HeuristicEvaluationGene> population = (List<HeuristicEvaluationGene>) ois.readObject();
        }
        

        

        
    }

    private List<HeuristicEvaluationGene> stepPopulation(List<HeuristicEvaluationGene> population) {
        return new ArrayList<>();
    }
}
