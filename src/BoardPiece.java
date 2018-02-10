public interface BoardPiece {
    public int getX();
    public int getY();
    public void moveUp(int steps);
    public void moveDown(int steps);
    public void moveLeft(int steps);
    public void moveRight(int steps);
    public void setXY(int x, int y);
}