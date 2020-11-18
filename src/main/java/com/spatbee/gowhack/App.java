package com.spatbee.gowhack;

import java.io.IOException;

import com.spatbee.gowhack.heuristics.GeneticAlgorithm;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        
        // GamePlayer.playGame();
        
        GeneticAlgorithm.runPopulationFromFileSeed("gene.txt");
        // System.out.println(GeneticAlgorithm.getPrettyOfBestGeneInPopulationFile("gene.txt"));
    }
}
