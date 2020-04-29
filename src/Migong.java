import java.util.LinkedList;

public class Migong {
    private Cell[][] cells = null;
    private LinkedList<Point> paths = null;

    public static Migong generateMigong(int row, int col) {
        Migong migong = new Migong();
        migong.cells = CellsGen.random(row, col);
        return migong;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public LinkedList<Point> getPaths() {
        return paths;
    }

    public void findPaths() {
        if (cells == null) {
            return;
        }

        int maxRow = cells.length;
        int maxCol = 0;
        if (maxRow > 0) {
            maxCol = cells[0].length;
        }

        MyStack<Point> pointStack = new MySequentialStack<>();
        pointStack.push(new Point(0, 0));

        Point point = null;
        char lastTriedDirection = ' ';

        while ((point = pointStack.pop()) != null) {
            if (point.getX() == maxCol - 1 && point.getY() == maxRow - 1) {
                paths = new LinkedList<>();
                do {
                    paths.addFirst(point);
                } while ((point = pointStack.pop()) != null);
                return;
            }

            Cell cell = cells[point.getY()][point.getX()];

            char fromDirection = direction(pointStack.peek(), point);
            Point next = null;

            if (lastTriedDirection < 'e' && cell.isE() && fromDirection != 'e') {
                next = new Point(point.getX() + 1, point.getY());
            }
            if (lastTriedDirection < 'n' && next == null && cell.isN() && fromDirection != 'n') {
                next = new Point(point.getX(), point.getY() - 1);
            }
            if (lastTriedDirection < 's' && next == null && cell.isS() && fromDirection != 's') {
                next = new Point(point.getX(), point.getY() + 1);
            }
            if (lastTriedDirection < 'w' && next == null && cell.isW() && fromDirection != 'w') {
                next = new Point(point.getX() - 1, point.getY());
            }
            if (next == null) {
                lastTriedDirection = direction(point, pointStack.peek());
            } else {
                pointStack.push(point);
                pointStack.push(next);
                lastTriedDirection = ' ';
            }
        }
    }

    private char direction(Point from, Point to) {
        if (from == null || to == null) {
            return ' ';
        }
        if (from.getX() > to.getX()) {
            return 'e';
        }
        if (from.getX() < to.getX()) {
            return 'w';
        }
        if (from.getY() > to.getY()) {
            return 's';
        }
        if (from.getY() < to.getY()) {
            return 'n';
        }
        return ' ';
    }
}
