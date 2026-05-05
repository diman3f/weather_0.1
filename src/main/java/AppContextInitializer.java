import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContextInitializer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/application.xml");

        final HelloService bean = context.getBean("3", HelloService.class);
        bean.sayHello();
        System.out.println(bean);

    }
}
