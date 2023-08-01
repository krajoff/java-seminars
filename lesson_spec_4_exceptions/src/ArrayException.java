public class ArrayException extends Exception{

    private int size;

    public int getSize() {
        return size;
    }

    public ArrayException(String message, int row, int column){
        super(message);
    }

    public ArrayException(String message, int size){
        super(message);
        this.size = size;
    }

    public ArrayException(String message){
        super(message);
    }
}
