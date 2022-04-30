package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T>{
    T initialValue = null;
    public Maybe(T t){
        this.initialValue = t;
    }
    public Maybe(){}

    public static <U>  Maybe<U> of (U t){
        return new Maybe<>(t);
    }
    public void ifPresent(Consumer<T> cons){
        if (this.initialValue != null){
            cons.accept(this.initialValue);
        }
    }
    public <R> Maybe<R> map(Function<T, R> func){
        return initialValue != null ? new Maybe<R>((R)func.apply(initialValue)) : new Maybe<>();
    }

    public T get() throws NoSuchElementException{
        if(this.initialValue == null){
            throw new NoSuchElementException(" maybe is empty");
        }
        return this.initialValue;
    }
    public boolean isPresent(){
        return this.initialValue != null;
    }
    public T orElse(T defVal){
        return this.initialValue != null ? this.initialValue : defVal;
    }

    public Maybe<T> filter(Predicate<T> pred){
        if (pred.test(this.initialValue) || this.initialValue == null){
            return this;
        } else
            return new Maybe<T>();
    }

    public String toString(){
        return this.initialValue != null ? "Maybe has value " + this.initialValue :
                "Maybe is empty";
    }


}
