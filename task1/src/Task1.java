public class Task1 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("You need to enter two integers separated by a space");
            return;
        }

        // Uncomment to test the getPath method
        // assert(getPath(4, 3) == 13);
        // assert(getPath(5, 4) == 14253);

        int arrLength = Integer.parseInt(args[0]);
        int step = Integer.parseInt(args[1]);

        System.out.println(getPath(arrLength, step));
    }

    /**
     * Calculates a path through a circular array with a given step size
     * until the last element of the path becomes the first element of the array.
     *
     * @param arrLength - length of the array
     * @param step      - step size for the path
     * @return - calculated path as an integer
     */
    private static int getPath(int arrLength, int step) {
        StringBuilder result = new StringBuilder();
        int index = 0; // Index of the first element in the current interval of the circular array

        do {
            result.append(index + 1); // Append the first number of the interval to the result
            index = (index + step - 1) % arrLength; // Calculate the index of the last number of the current interval / first number of the next interval
        } while (index != 0); // Continue until the index points to the first number of the array

        return Integer.parseInt(result.toString());
    }
}
