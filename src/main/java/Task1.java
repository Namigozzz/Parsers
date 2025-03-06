import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        String outputFileName = "data.json";
        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = listToJson(list);
        writeString(json, outputFileName);
    }

    public static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();

            return csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeString(String json, String outPutFileName) {
        try (FileWriter fileWriter = new FileWriter(outPutFileName)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String listToJson(List<Employee> list) {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        String json = gson.toJson(list, listType);

        return json;
    }
}
