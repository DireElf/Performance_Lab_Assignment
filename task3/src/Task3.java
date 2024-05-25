import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class Task3 {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // Ignore fields with null values
            Tests tests = mapper
                    .readValue(Paths.get(args[0]).toFile(), Tests.class); // Test file: resources/tests.json
            Values values = mapper
                    .readValue(Paths.get(args[1]).toFile(), Values.class); // Test file: resources/values.json

            fillTestsWithResults(tests.getTests(), values.getValues());

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(Paths.get(args[2]).toFile(), tests); // Test file: resources/report.json
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fills the value fields of each test if a corresponding value is found.
     * @param tests - list of tests
     * @param values - list of values
     */
    private static void fillTestsWithResults(List<Test> tests, List<Value> values) {
        for (Test test : tests) {
            Optional<String> testResult = findTestResultById(values, test.getId());
            testResult.ifPresent(test::setValue);
            if (test.getValues() != null) {
                fillTestsWithResults(test.getValues(), values);
            }
        }
    }

    /**
     * Finds the test result in the list of values by the test's ID.
     * @param list - list of test results
     * @param id - test ID
     * @return Optional containing the result value if found
     */
    private static Optional<String> findTestResultById(List<Value> list, int id) {
        for (Value value : list) {
            if (value.id == id) {
                return Optional.of(value.value);
            }
        }
        return Optional.empty();
    }

    /*
    POJO classes
    */
    private static class Test {
        private int id;
        private String title;
        private String value;
        private List<Test> values;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<Test> getValues() {
            return values;
        }

        public void setValues(List<Test> values) {
            this.values = values;
        }
    }

    private static class Value {
        private int id;
        private String value;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private static class Tests {
        private List<Test> tests;

        public List<Test> getTests() {
            return tests;
        }

        public void setTests(List<Test> tests) {
            this.tests = tests;
        }
    }

    private static class Values {
        private List<Value> values;

        public List<Value> getValues() {
            return values;
        }

        public void setValues(List<Value> values) {
            this.values = values;
        }
    }
}
