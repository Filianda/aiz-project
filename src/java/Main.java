import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        List<String> bigArray = getWordsFromFile("C:/Users/Olimpia/Documents/Visual Studio 2019/javaProjects/BigProject2/BigProject2Trees/latarnik.txt");

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
            for (int i = 0; i < 10; i++) {
                List <String> list = generateListOf100RandomElements(bigArray, bigArray.size(), 1);
                long timeOfSearch = findAndMeasureExecutionSearchTime(list, tree);
                resultsFileWriter.write(String.format("%d\n", timeOfSearch));
            }
            resultsFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //wypisz drzewo
           tree.add("kaczka");
           
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
                    bigArray.add(word);
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

    public static List<String> generateListOf100RandomElements(List<String> bigArray, int upperBoundary,
            int lowerBoundary) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(bigArray.get(getRandomNumber(lowerBoundary, upperBoundary)));
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
}

