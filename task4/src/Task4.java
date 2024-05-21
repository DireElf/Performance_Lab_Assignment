import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        /*
        Тестовые файлы:
        resources/file1.txt (ожидаемый результат 2)
        resources/file2.txt (ожидаемый результат 16)
         */
        List<Integer> list = getNumbersFromFile(args[0]);
        int middle = getMiddle(list);
        System.out.println(getStepsNumber(list, middle));
    }

    /**
     * Метод считывает числа из файла в список
     * @param path - путь к файлу
     * @return список чисел
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
     * Метод рассчитывает количество шагов инкремента/декремента чисел из списка до целевого числа
     * @param list - список чисел
     * @param middle - целевое число
     * @return количество шагов
     */
    private static int getStepsNumber(List<Integer> list, int middle) {
        if (list.size() == 0) return 0;
        int result = 0;
        for (int i : list) {
            result += Math.abs(i - middle);
        }
        return result;
    }

    /**
     * Метод выбирает число, к которому будут приводиться остальные числа
     * @param list - список чисел
     * @return целевое число
     */
    private static int getMiddle(List<Integer> list) {
        List<Integer> sortedList = list.stream().sorted().toList();
        return sortedList.get(sortedList.size() / 2);
    }
}
