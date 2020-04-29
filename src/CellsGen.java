import java.util.*;

public class CellsGen {

    public static Cell[][] random(int row, int col) {
        Cell[][] cells = new Cell[row][col];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }

        breakWalls(cells, row, col);

        return cells;
    }

    private static void breakWalls(Cell[][] cells, int maxRow, int maxCol) {
        Random random = new Random(System.currentTimeMillis());

        int r = random.nextInt(maxRow);
        int c = random.nextInt(maxCol);


        Stack<Point> history = new Stack<>();
        Set<Point> accessed = new HashSet<>();
        boolean start = false;
        boolean end = false;

        accessed.add(new Point(c, r));

        while (!start || !end) {
            Point up = new Point(c, r - 1);
            Point down = new Point(c, r + 1);
            Point left = new Point(c - 1, r);
            Point right = new Point(c + 1, r);

            List<Point> possibles = new ArrayList<>();
            if (r > 0 && !accessed.contains(up)) {
                possibles.add(up);
            }
            if (r < maxRow - 1 && !accessed.contains(down)) {
                possibles.add(down);
            }
            if (c > 0 && !accessed.contains(left)) {
                possibles.add(left);
            }
            if (c < maxCol - 1 && !accessed.contains(right)) {
                possibles.add(right);
            }

            if (possibles.isEmpty()) {
                if (history.isEmpty()) {
                    return;
                }
                Point pop = history.pop();
                r = pop.getY();
                c = pop.getX();
                continue;
            }

            int index = random.nextInt(possibles.size());
            Point pos = possibles.get(index);

            if (pos.equals(up)) {
                cells[r][c].setN(true);
                r = r - 1;
                cells[r][c].setS(true);
            } else if (pos.equals(down)) {
                cells[r][c].setS(true);
                r = r + 1;
                cells[r][c].setN(true);
            } else if (pos.equals(left)) {
                cells[r][c].setW(true);
                c = c - 1;
                cells[r][c].setE(true);
            } else if (pos.equals(right)) {
                cells[r][c].setE(true);
                c = c + 1;
                cells[r][c].setW(true);
            }

            Point point = new Point(c, r);
            accessed.add(point);
            history.push(point);
            if (r == 0 && c == 0) {
                start = true;
            }
            if (r == maxRow - 1 && c == maxCol - 1) {
                end = true;
            }
        }
    }
}
