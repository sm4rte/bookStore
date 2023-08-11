package gpt.task.bookStore.validator;

public interface Validator<T> {
    void validate(T target) throws Exception;
}
