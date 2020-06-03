package io.jonashackt.lectures.exercises.model.excptions;

public class PersonNotFoundException extends Throwable {
    public PersonNotFoundException(String message) {
        super(message);
    }
}
