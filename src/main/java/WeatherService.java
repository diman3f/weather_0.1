public class WeatherService {
    private final Integer id;
    private final String name;
    private final String dbName;

    public WeatherService(Integer id, String name, String dbName) {
        this.id = id;
        this.name = name;
        this.dbName = dbName;
    }



    public String getWeatherByLocation(String name) {
        return String.format("Погода + 10 градусов в городе %s, имя БД %s", name, dbName);
    }

}
