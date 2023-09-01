package gpt.task.bookStore.exception;

public class DuplicateItemException extends RuntimeException{
    public DuplicateItemException(String message){
        super(message);
    }
}
