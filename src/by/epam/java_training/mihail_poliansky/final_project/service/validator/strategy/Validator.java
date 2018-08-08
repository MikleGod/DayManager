package by.epam.java_training.mihail_poliansky.final_project.service.validator.strategy;

public interface Validator {
    <T> boolean validate(T t);
}
