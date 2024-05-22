import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        /*
         * Example test files:
         * resources/file1.txt (expected result 2)
         * resources/file2.txt (expected result 16)
         */
        List<Integer> list = getNumbersFromFile(args[0]);
        assert list != null && !list.isEmpty();
        int middle = getMiddle(list);
        System.out.println(getStepsNumber(list, middle));
    }

    /**
     * Reads numbers from a file and returns them as a list.
     * @param path - the file path
     * @return a list of numbers from the file
     */
    private static List<Integer> getNumbersFromFile(String path) {
        List<Integer> list = null;
        try {
            list = Files.readAllLines(Paths.get(path)).stream()
                    .map(Integer::parseInt)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Calculates the number of steps to increment/decrement numbers from the list to the target number.
     * @param list - the list of numbers
     * @param middle - the target number
     * @return the number of steps to make all numbers in the list equal to the target number
     */
    private static int getStepsNumber(List<Integer> list, int middle) {
        int result = 0;
        for (int i : list) {
            result += Math.abs(i - middle);
        }
        return result;
    }

    /**
     * Selects the median value from the list of numbers.
     * The median value is the number to which all other numbers will be adjusted.
     * @param list - the list of numbers
     * @return the median value from the list
     */
    private static int getMiddle(List<Integer> list) {
        List<Integer> sortedList = list.stream().sorted().toList();
        return sortedList.get(sortedList.size() / 2);
    }
}

