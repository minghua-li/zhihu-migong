import java.util.*;

public class Main {
    public static void main(String[] args) {
        Migong migong = Migong.generateMigong(8, 7);
        MigongPrinter.printMigong(migong);

        migong.findPaths();
        MigongPrinter.printMigong(migong, migong.getPaths());

    }

}
