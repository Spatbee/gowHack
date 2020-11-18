package com.spatbee.gowhack;

import java.io.IOException;

import com.spatbee.gowhack.heuristics.GeneticAlgorithm;
import com.spatbee.gowhack.heuristics.NewGeneGenerator;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        
        // GamePlayer.playGame();
        GeneticAlgorithm.runPopulationWithRandomSeed();
        // System.out.println(GeneticAlgorithm.getPrettyOfBestGeneInPopulationFile("gene.txt"));
    }
}
