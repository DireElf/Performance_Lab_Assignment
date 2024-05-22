import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        /*
        Example test files:
        resources/circle.txt resources/points.txt
         */

        List<String> circleData = getStringsFromFile(args[0]);
        double centerX = getDouble(circleData.get(0), 0);
        double centerY = getDouble(circleData.get(0), 1);
        double radius = Double.parseDouble(circleData.get(1));

        List<String> pointsData = getStringsFromFile(args[1]);

        for(String coordinates : pointsData) {
            double pointX = getDouble(coordinates, 0);
            double pointY = getDouble(coordinates, 1);

            double positionFlag = Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2) - radius * radius;

            if (positionFlag > 0) {
                System.out.println(2);
            } else if(positionFlag < 0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    /**
     * Reads lines from a file at the specified path.
     * @param path - the file path
     * @return a list of strings from the file
     */
    private static List<String> getStringsFromFile(String path) {
        Path filePath = Paths.get(path);
        List<String> strings;
        try {
            strings = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return strings;
    }

    /**
     * Converts a string to an array (using space as a delimiter) and returns
     * the numeric value of the array element at the specified index.
     * @param s - the input string
     * @param index - the index of the element after converting the string to an array
     * @return a double value
     */
    private static double getDouble(String s, int index) {
        return Double.parseDouble(s.split(" ")[index]);
    }
}
