public class WeatherService {
    private final Integer id;
    private final String name;

    public WeatherService(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getWeatherByLocation(String name) {
        return String.format("Погода + 10 градусов в городе %s", name);
    }

}
