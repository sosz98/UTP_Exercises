package zad1;

import java.util.function.Function;

public class InputConverter<T> {
    T initialValue;
    public InputConverter(T t){
        this.initialValue = t;
    }
    public <U> U convertBy(Function... functions){
        Object result = null;
        Object value = initialValue;
        for (Function f : functions){

            result = f.apply(value);
            value = result;
        }
        return (U) result;
    }
}
