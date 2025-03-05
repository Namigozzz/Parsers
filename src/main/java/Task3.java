import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        String json = readString("new_data.json");
        List<Employee> employees = jsonToList(json);

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public static String readString(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> employees = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();

        for (JsonElement jsonElement : jsonArray) {
            Employee employee = gson.fromJson(jsonElement, Employee.class);
            employees.add(employee);
        }

        return employees;
    }
}
