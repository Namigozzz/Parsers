import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> list = parseXML("data.xml");
        String json = listToJson(list);
        writeString(json);
    }

    public static List<Employee> parseXML(String fileName) {
        List<Employee> employees = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fileName));
            Node root = document.getDocumentElement();
            NodeList list = ((Element) root).getElementsByTagName("employee");

            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                long id = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());
                String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                String country = element.getElementsByTagName("country").item(0).getTextContent();
                int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                employees.add(new Employee(id, firstName, lastName, country, age));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static void writeString(String json) {
        try (FileWriter fileWriter = new FileWriter("data2.json")) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String listToJson(List<Employee> list) {
        return Task1.listToJson(list);
    }
}
