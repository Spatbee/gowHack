package com.spatbee.gowhack.heuristics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.spatbee.gowhack.GameBoard;
import com.spatbee.gowhack.GameCoordinator;
import com.spatbee.gowhack.NewBoardGenerator;
import com.spatbee.gowhack.exception.CouldNotScrambleBoardException;
import com.spatbee.gowhack.exception.IllegalBoardStateException;
import com.spatbee.gowhack.exception.MatchDoesNotContainSingleTurnCoordinateException;
import com.spatbee.gowhack.heuristics.gene.HeuristicEvaluationGene;

public class GeneticAlgorithm {

    public static void runPopulationWithRandomSeed() {
        List<HeuristicEvaluationGene> population = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            population.add(NewGeneGenerator.creatNewGene());
        }
        stepPopulation(population);
    }

    public static void runPopulationFromFileSeed(String filePath) throws IOException, ClassNotFoundException {
        stepPopulation(readPopulationFromFile(filePath));
    }

    private static List<HeuristicEvaluationGene> readPopulationFromFile(String filePath) throws IOException, ClassNotFoundException {
        List<HeuristicEvaluationGene> population = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filePath)))) {
            Object fileObject = ois.readObject();
            
            if(fileObject instanceof List<?>) {
                
                for(Object readListObject : (List<?>) fileObject) {
                    if(readListObject instanceof HeuristicEvaluationGene) {
                        population.add((HeuristicEvaluationGene)readListObject);
                    } else {
                        throw new IOException("object in list was not of type HeuristicEvaluationGene");        
                    }
                }
                
            } else {
                throw new IOException("object read was not of type List<?>");
            }
        }
        return population;
    }

    public static String getPrettyOfBestGeneInPopulationFile(String filePath) throws ClassNotFoundException, IOException {
        return readPopulationFromFile(filePath).get(0).pretty();
    }

    private static void stepPopulation(List<HeuristicEvaluationGene> population) {
        for(int i = 0; i < 10000; i++) {
            int[] topScores = new int[population.size() / 2];
            HeuristicEvaluationGene[] bestGenes = new HeuristicEvaluationGene[population.size() / 2];
            for(HeuristicEvaluationGene gene : population) {
                updateTopScores(topScores, bestGenes, gene, getAverageScore(gene));
            }
            population = new ArrayList<>();
            Collections.addAll(population, bestGenes);
            for(HeuristicEvaluationGene gene : bestGenes) {
                population.add(gene.replicateWithMutation());
            }
            System.out.println("Generation: " + i);
            System.out.println("Average score: " + topScores[0]);
            System.out.println("Best Gene: " + bestGenes[0].pretty());
            if(i % 100 == 0) {
                try {
                    savePopulation(population, "generation" + i + "a" + topScores[0] + ".txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void updateTopScores(int[] topScores, HeuristicEvaluationGene[] bestGenes, HeuristicEvaluationGene gene, int averageScore) {
        for(int scoreIndex = 0; scoreIndex < topScores.length; scoreIndex ++) {
            if(averageScore > topScores[scoreIndex]) {
                for(int moveIndex = topScores.length - 1; moveIndex > scoreIndex; moveIndex --) {
                    topScores[moveIndex] = topScores[moveIndex - 1];
                    bestGenes[moveIndex] = bestGenes[moveIndex - 1];
                }
                topScores[scoreIndex] = averageScore;
                bestGenes[scoreIndex] = gene;
            }
        }
    }

    private static int getAverageScore(HeuristicEvaluationGene gene) {
        int totalScore = 0;
        for(int j = 0; j < 5; j++) {
            try {
                GameBoard gameBoard = NewBoardGenerator.generateStartingGameBoard();
                while(gameBoard.getTurnsLeft() > 0) {
                    gameBoard.doTurn(GameCoordinator.getBestTurnFromHeuristicEvaluation(gameBoard, gene));
                }
                totalScore += gameBoard.score();
            } catch (MatchDoesNotContainSingleTurnCoordinateException | CouldNotScrambleBoardException | IllegalBoardStateException e) {
                e.printStackTrace();
            }
        }
        int averageScore = totalScore / 5;
        return averageScore;
    }
    
    private static void savePopulation(List<HeuristicEvaluationGene> population, String fileName) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))) {
            oos.writeObject(population);
        }
    }
}
