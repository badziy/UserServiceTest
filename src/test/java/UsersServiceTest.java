import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class UsersServiceTest {
    private UsersService usersService;

    @BeforeAll
    public static void startTesting() {
        System.out.println("Запуск тестов");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("Действия перед каждым тестом");
        Users user1 = new Users("Алан", LocalDate.of(1994, 3, 17));
        Users user2 = new Users("Лариса", LocalDate.of(1970, 4, 17));
        Users user3 = new Users("Ирина", LocalDate.of(1997, 6, 23));
        List<Users> usersList = new ArrayList<>();
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
        usersService = new UsersService(usersList);
    }

    @Test
    public void given2Beans_whenHavingSameValues_thenCorrect() {
        Users user4 = new Users("Игорь", LocalDate.of(1991, 12, 22));
        Users user5 = new Users("Игорь", LocalDate.of(1991, 12, 22));
        assertThat(user4, samePropertyValuesAs(user5));
    }

    @Test
    public void whenCreateNewUserThenReturnListWithNewUser() throws Exception {
        assertThat(usersService.getUsers().size(), is(3));
        usersService.createNewUser("New User", LocalDate.of(1990, 2, 1));
        assertThat(usersService.getUsers().size(), is(4));
    }

    @Test
    public void whenRemoveUserWhenRemoveUserByName(){
        usersService.removeUser("Ирина");
        List<Users> usersList = usersService.getUsers();
        assertThat(usersList.size(), is(2));
    }


    @Test
    public void whenIsBirthDayWhenBirthdayThenReturnTrue() throws CustomFieldException {
        boolean isBirthday = usersService.isBirthDay(usersService.getUsers().get(0), LocalDate.of(1990, 2, 1));
        assertFalse(isBirthday);
    }

    @Test
    public void whenIsBirthDayWhenNotBirthdayThenReturnFalse() throws CustomFieldException {
        boolean isBirthday = usersService.isBirthDay(usersService.getUsers().get(0), LocalDate.of(1990, 3, 17));
        assertTrue(isBirthday);
    }

    @AfterAll
    public static void testFinish() {
        System.out.println("Тесты пройдены");
    }

    @AfterEach
    public void afterMethod() {
        System.out.println("Код выполняется после каждого теста");
    }
}
