public class Task1 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Нужно ввести два целых числа через пробел");
            return;
        }

//        assert(getPath(4, 3) == 13);
//        assert(getPath(5, 4) == 14253);

        int arrLength = Integer.parseInt(args[0]);
        int step = Integer.parseInt(args[1]);

        System.out.println(getPath(arrLength, step));
    }

    /**
     * Метод получения пути по круговому массиву с заданным шагом,
     * пока последним элементом пути не станет первый элемент массива
     *
     * @param arrLength - длина массива
     * @param step      - шаг пути
     * @return - значение пути
     */
    private static int getPath(int arrLength, int step) {
        StringBuilder result = new StringBuilder(); // возвращаемый результат
        int index = 0; // индекс первого числа текущего интервала в круговом массиве

        do {
            result.append(index + 1); // получаем и "конкатенируем" первое число интервала с результатом
            index = (index + step - 1) % arrLength; // получаем индекс первого числа следующего интервала
        } while (index != 0); // пока индекс не укажет на первое число массива

        return Integer.parseInt(result.toString());
    }
}
