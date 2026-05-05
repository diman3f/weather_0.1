import javax.swing.*;
import java.util.List;

public class HelloService {

    private String name;
    private Integer id;
    private List<String> boxList;
    private final WeatherService service;

    public void sayHello() {
        System.out.println("Hello Word" + service.getWeatherByLocation("Samara"));

    }

    public HelloService(String name, Integer id, List<String> boxList, WeatherService service) {
        this.name = name;
        this.id = id;
        this.boxList = boxList;
        this.service = service;
    }


    @Override
    public String toString() {
        for (String value : boxList) {
            System.out.println(value);
        }
        return "HelloService{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", boxList=" + boxList +
                '}';
    }
}
