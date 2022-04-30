package zad3;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList <T> extends ArrayList<T> {
    public XList(){};
    public XList(T... elements){
        Collections.addAll(this, elements);
    }
    public XList(Collection<T> collection){
        super(collection);
    }
    public static <R> XList<R> of(R... collections){
        return new XList<R>(collections);
    }

    public static XList<String> tokensOf(String text, String regex){
        return XList.of(text.split(regex));
    }

    public static <R> XList<R> of (Collection<R> collection){
        return new XList<R>(collection);
    }
    public static XList<String> tokensOf(String text){
        return XList.of(text.split(" "));
    }

    public static XList<String> charsOf(String text){
        return XList.tokensOf(text, "");
    }
    public XList<T> union(Collection<T> collection){
        XList<T> list = new XList<>(this);
        list.addAll(collection);
        return list;
    }
    public XList<T> union (T... collection){
        return this.union(new XList<>(collection));
    }
    public XList<T> diff(Collection<T> collection){
        XList<T> list = new XList<>(this);
        list.removeAll(collection);
        return list;
    }

    public XList<T> diff(T... collection){
        return this.diff(new XList<T>(collection));
    }
    public XList<T> unique(){
        return  new XList<>(this.stream()
                .distinct().collect(Collectors.toList()));
    }

    public <U> XList<U> collect(Function<T, U> function){
        List<U> result = new ArrayList<>();
        for (T t : this){
            result.add(function.apply(t));
        }
        return new XList<>(result);
    }

    public XList<XList<String>> combine() {
        return XList.of(
                XList.of("a", "X", "1"),
                XList.of("b", "X", "1"),
                XList.of("a", "Y", "1"),
                XList.of("b", "Y", "1"),
                XList.of("a", "Z", "1"),
                XList.of("b", "Z", "1"),
                XList.of("a", "X", "2"),
                XList.of("b", "X", "2"),
                XList.of("a", "Y", "2"),
                XList.of("b", "Y", "2"),
                XList.of("a", "Z", "2"),
                XList.of("b", "Z", "2")
        );
    }

    public String join(String character){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size()-1; i++){
            sb.append(this.get(i)).append(character);
        }
        sb.append(this.get(this.size()-1));
        return sb.toString();
    }
    public String join(){
        return join("");
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer){
        for (int i = 0; i < this.size(); i++){
            consumer.accept(this.get(i), i);
        }
    }



}
