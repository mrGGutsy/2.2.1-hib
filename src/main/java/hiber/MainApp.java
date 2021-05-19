package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Hyundai", 111)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("BMW", 777)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("KIA", 123)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Mers", 35)));

        List<User> users = userService.listUsers();
        users.forEach(System.out::println);

        Car car = new Car("KIA", 123);
        List<User> user = userService.getUsersOnCar(car.getModel(), car.getSeries());
        user.forEach(System.out::println);

        context.close();
    }
}
