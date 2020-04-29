import java.util.List;

public class MigongPrinter {

    public static void printMigong(Migong migong) {
        if (migong.getCells() != null) {
            System.out.println(toStringBuilder(migong.getCells()));
        }
    }

    public static void printMigong(Migong migong, List<Point> paths) {
        if (migong.getCells() != null) {
            int maxRow = migong.getCells().length;
            if (maxRow > 0) {
                int maxCol = migong.getCells()[0].length;
                System.out.println(appendPaths(toStringBuilder(migong.getCells()), paths, maxRow, maxCol));
            }
        }
    }

    private static final char DIRECTION_UP = '↑';
    private static final char DIRECTION_DOWN = '↓';
    private static final char DIRECTION_LEFT = '←';
    private static final char DIRECTION_RIGHT = '→';

    private static StringBuilder appendPaths(StringBuilder stringBuilder, List<Point> paths, int maxRow, int maxCol) {
        int r = 0;
        int c = 0;

        char previous = FULL_SPACE;
        for (Point path : paths) {
            int toC = path.getX();
            int toR = path.getY();

            int index = 0;

            char direction = FULL_SPACE;
            if (toR == r - 1) {
                direction = DIRECTION_UP;
                index = ((r + 1) * 2 - 2) * ((maxCol) * 2 + 2) + (c + 1) * 2 - 1;
            } else if (toR == r + 1) {
                direction = DIRECTION_DOWN;
                index = ((r + 1) * 2) * ((maxCol) * 2 + 2) + (c + 1) * 2 - 1;
            } else if (toC == c - 1) {
                direction = DIRECTION_LEFT;
                index = ((r + 1) * 2 - 1) * ((maxCol) * 2 + 2) + (c + 1) * 2 - 2;
            } else if (toC == c + 1) {
                direction = DIRECTION_RIGHT;
                index = ((r + 1) * 2 - 1) * ((maxCol) * 2 + 2) + (c + 1) * 2;
            } else {
                continue;
            }
            stringBuilder.replace(index, index + 1, "" + direction);

            char cell = FULL_SPACE;

            if ((previous == DIRECTION_UP || previous == DIRECTION_DOWN) && (direction == DIRECTION_UP || direction == DIRECTION_DOWN)) {
                cell = '│';
            } else if ((previous == DIRECTION_LEFT || previous == DIRECTION_RIGHT) && (direction == DIRECTION_LEFT || direction == DIRECTION_RIGHT)) {
                cell = '─';
            } else if ((previous == DIRECTION_LEFT && direction == DIRECTION_UP) || (previous == DIRECTION_DOWN && direction == DIRECTION_RIGHT)) {
                cell = '└';
            } else if ((previous == DIRECTION_LEFT && direction == DIRECTION_DOWN) || (previous == DIRECTION_UP && direction == DIRECTION_RIGHT)) {
                cell = '┌';
            } else if ((previous == DIRECTION_UP && direction == DIRECTION_LEFT) || (previous == DIRECTION_RIGHT && direction == DIRECTION_DOWN)) {
                cell = '┐';
            } else if ((previous == DIRECTION_RIGHT && direction == DIRECTION_UP) || (previous == DIRECTION_DOWN && direction == DIRECTION_LEFT)) {
                cell = '┘';
            }

            if (cell != FULL_SPACE) {
                index = ((r + 1) * 2 - 1) * ((maxCol) * 2 + 2) + (c + 1) * 2 - 1;
                stringBuilder.replace(index, index + 1, "" + cell);
            }
            previous = direction;

            r = toR;
            c = toC;
        }
        return stringBuilder;
    }

    private static final char FULL_SPACE = '　';

    private static StringBuilder toStringBuilder(Cell[][] cells) {
        StringBuilder sline = new StringBuilder();
        int maxRow = 0;
        int maxCol = 0;
        for (int r = 0; r < cells.length; r++) {
            maxRow = cells.length;
            for (int c = 0; c < cells[r].length; c++) {
                maxCol = cells[r].length;
                if (c == 0) {
                    if (r == 0) {
                        sline.append('┌');
                    } else {
                        sline.append('├');
                    }
                } else {
                    if (r == 0) {
                        sline.append('┬');
                    } else {
                        sline.append('┼');
                    }
                }
                if (cells[r][c].isN()) {
                    sline.append(FULL_SPACE);
                } else {
                    sline.append("─");
                }
                if (c == cells[r].length - 1) {
                    if (r == 0) {
                        sline.append('┐');
                    } else {
                        sline.append('┤');
                    }
                }
            }
            sline.append("\n");
            for (int c = 0; c < cells[r].length; c++) {
                if (cells[r][c].isW()) {
                    sline.append(FULL_SPACE);
                } else {
                    sline.append("│");
                }
                sline.append(FULL_SPACE);
                if (c == cells[r].length - 1) {
                    sline.append('│');
                }
            }
            sline.append("\n");
            if (r == cells.length - 1) {
                for (int c = 0; c < cells[r].length; c++) {
                    if (c == 0) {
                        sline.append('└');
                    } else {
                        sline.append('┴');
                    }
                    sline.append("─");
                    if (c == cells[r].length - 1) {
                        sline.append("┘");
                    }
                }
            }
        }
        // ☆★
        int index = ((maxCol) * 2 + 2) + 1;
        sline.replace(index, index + 1, "☆");
        index = (maxRow * 2 - 1) * ((maxCol) * 2 + 2) + maxCol * 2 - 1;
        sline.replace(index, index + 1, "★");
        return sline;
    }
}
