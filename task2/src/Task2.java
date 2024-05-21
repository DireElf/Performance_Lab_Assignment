import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        /*
        Тестовые файлы:
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

            double checkPosition = Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2) - radius * radius;

            if (checkPosition > 0) {
                System.out.println(2);
            } else if(checkPosition < 0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    /**
     * Метод считывает строки из файла по указанному пути
     * @param path - путь к файлу
     * @return список строк
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
     * Метод преобразует строку в массив (с пробелом в качестве разделителя) и возвращает
     * числовое значение элемента массива по указанному индексу
     * @param s - входящая строка
     * @param index - индекс элемента после преобразования строки в массив
     * @return дробное число
     */
    private static double getDouble(String s, int index) {
        return Double.parseDouble(s.split(" ")[index]);
    }
}
