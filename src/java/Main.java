import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String book1 = new String("src/resources/DzieckoPrzezPtakaPrzyniesione.txt");
        String book2 = new String("src/resources/golem.txt");
        String book3 = new String("src/resources/jaszczor.txt");
        String book4 = new String("src/resources/lalka1.txt");
        String book5 = new String("src/resources/lalka2.txt");
        String book6 = new String("src/resources/latarnik.txt");
        String book7 = new String("src/resources/plominie.txt");
        String book8 = new String("src/resources/salamandra.txt");
        String book9 = new String("src/resources/tajemniczyOgrod.txt");
        String book10 = new String("src/resources/zloteSidla.txt");

        List<String> bigArray = getWordsFromFile(book3);

        BST treeBST = new BST();
        BST treeSplay = new Splay();
        BST treeAVL = new AVL();
        
        long timeOfBuildBST = fillTreeWithWordsAndMeasureTime(treeBST, bigArray);
        long timeOfBuildSplay = fillTreeWithWordsAndMeasureTime(treeSplay, bigArray);
        long timeOfBuildAVL = fillTreeWithWordsAndMeasureTime(treeAVL, bigArray);

        int depthOfTreeBST = treeBST.measureDeepestBranch();
        int depthOfTreeSplay = treeSplay.measureDeepestBranch();
        int depthOfTreeAVL = treeAVL.measureDeepestBranch();
        
        FileWriter resultsFileWriter;
        try {
            resultsFileWriter = new FileWriter("wyniki-pomiarow.csv");

            resultsFileWriter.write(String.format("tree;time of build;height Of tree\n"));
            resultsFileWriter.write(String.format("BST;%d;%d\n", timeOfBuildBST, depthOfTreeBST));
            resultsFileWriter.write(String.format("Splay;%d;%d\n", timeOfBuildSplay, depthOfTreeSplay));
            resultsFileWriter.write(String.format("AVL;%d;%d\n", timeOfBuildAVL, depthOfTreeAVL));

            resultsFileWriter.write(String.format("time of search\n"));
            resultsFileWriter.write(String.format("word;BST level;BST time;Splay level;Splay time;AVL level; AVL time\n"));

            for (String word : generateListOf100RandomElements(bigArray)) {
                List<String> row = new ArrayList<String>();
                row.add(word);
                findWordWithTimeMeasurmentAndAddResultsToList(treeBST, word, row);
                findWordWithTimeMeasurmentAndAddResultsToList(treeSplay, word, row);
                findWordWithTimeMeasurmentAndAddResultsToList(treeAVL, word, row);
                resultsFileWriter.write(String.join(";", row) + "\n");
            }
            resultsFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findWordWithTimeMeasurmentAndAddResultsToList(BST tree, String word, List<String> list) throws IOException {
        long startTime = System.nanoTime();
        int level = findWordAndReturnItsLevel(word, tree);
        long stopTime = System.nanoTime();

        list.add(Integer.toString(level));
        list.add(Long.toString(stopTime - startTime));
    }

    public static long fillTreeWithWordsAndMeasureTime(BST tree, List<String> bigArray) {
        long startTime = System.nanoTime();
        for (int i = 0; i < bigArray.size(); i++) {
            tree.add(bigArray.get(i));
        }
        long stopTime = System.nanoTime();
        return stopTime - startTime;
    }

    public static List<String> getWordsFromFile(String fileName) {
        List<String> bigArray = new ArrayList<>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] words = line.replaceAll("[^a-zA-Z \u0100-\u01ff]", "").toLowerCase().split(" ");
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

    public static int findWordAndReturnItsLevel(String word, BST tree) {
        return tree.getLevel(word);
    }
}

