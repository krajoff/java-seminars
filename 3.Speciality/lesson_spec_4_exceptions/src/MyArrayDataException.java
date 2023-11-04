public class MyArrayDataException extends ArrayException{
    private int row;

    private int column;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public MyArrayDataException(String message, int  row, int column){
        super(message, row, column);
        this.column = column;
        this.row = row;
    }
}
