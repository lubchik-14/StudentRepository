package studentrepo;

import studentrepo.services.IStudentRepository;
import studentrepo.services.MainMenu;
import studentrepo.services.ConsolePrinter;
import studentrepo.services.StudentRepository;

public class JdbcMain {
    public static void main(String[] args) {
        IStudentRepository sqlMaker = new StudentRepository(
                "jdbc:mysql://localhost/people?serverTimezone=Europe/Kiev", "root", "123456789");
        MainMenu menu = new MainMenu(sqlMaker, new ConsolePrinter());
        menu.start();
    }
}
