import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        List<String> bigArray = getWordsFromFile("src/resources/latarnik.txt");

        BST treeBST = new BST();
        BST treeSplay = new Splay();
        BST treeAVL = new AVL();
        
        BST tree = treeBST;

        long timeOfBuild = fillTreeWithWordsAndMeasureTime(tree, bigArray);
        int depthOfTree = tree.measureDeepestBranch();
        
        FileWriter resultsFileWriter;
        try {
            resultsFileWriter = new FileWriter("wyniki-pomiarow.csv");
            resultsFileWriter.write(String.format("time of build;dapth Of tree\n"));
            resultsFileWriter.write(String.format("%d;%d\n", timeOfBuild, depthOfTree));
            resultsFileWriter.write(String.format("time of search\n"));

            List <String> list = generateListOf100RandomElements(bigArray);
            for (String word : list) {
                long startTime = System.currentTimeMillis();
                int level = findWordAndReturnItsLevel(word, tree);
                long stopTime = System.currentTimeMillis();
                resultsFileWriter.write(String.format("%s;%d;%d\n", word, stopTime - startTime, level));
            }
            resultsFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long fillTreeWithWordsAndMeasureTime(BST tree, List<String> bigArray) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < bigArray.size(); i++) {
            tree.add(bigArray.get(i));
        }
        long stopTime = System.currentTimeMillis();
        return stopTime - startTime;
    }

    public static List<String> getWordsFromFile(String fileName) {
        List<String> bigArray = new ArrayList<>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] words = line.replaceAll("[^a-zA-Z \u0100-\u01ff]", "").split(" ");
                for (String word : words) {
                    if (!word.trim().isEmpty()) {
                        bigArray.add(word);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return bigArray;
    }

    static int getRandomNumber(int lowerBoundary, int upperBoundary) {
        return (int) ((Math.random() * (upperBoundary - lowerBoundary)) + lowerBoundary);
    }

    public static List<String> generateListOf100RandomElements(List<String> bigArray) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(bigArray.get(getRandomNumber(0, bigArray.size())));
        }
        return list;
    }

    public static long findAndMeasureExecutionSearchTime(List<String> list, BST tree) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            tree.contains(list.get(i)); 
        }
        long stopTime = System.currentTimeMillis();
        return stopTime - startTime;
    }

    public static int findWordAndReturnItsLevel(String word, BST tree) {
        return tree.getLevel(word);
    }
}

