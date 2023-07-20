import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class BasicLamdas<T> {
    public static void main(String[] args) {
        BasicLamdas basicLamdas = new BasicLamdas();
        basicLamdas.consumer();
        basicLamdas.supplier();
        basicLamdas.predicate();

        Predicate<Integer> integerPredicate = i -> i%2==0;
        Predicate<String> stringPredicate = s -> s.startsWith("Mr.");
        Predicate<BasicLamdas.Person> personPredicate = p -> p.getAge() >= 18;

        System.out.println(basicLamdas.check(4, integerPredicate));
        System.out.println(basicLamdas.check(7, integerPredicate));

        System.out.println(basicLamdas.check("Mr. Joe Bloggs", stringPredicate));
        System.out.println(basicLamdas.check("Ms. Ann Bloggs", stringPredicate));

        System.out.println(basicLamdas.check(new Person("Mike", 33, 1.8), personPredicate));
        System.out.println(basicLamdas.check(new Person("Ann", 13, 1.4), personPredicate));

        basicLamdas.function();
        List<Person> listPeople = basicLamdas.getPeople();
        basicLamdas.sortAge(listPeople);
        basicLamdas.sortName(listPeople);
        basicLamdas.sortHeight(listPeople);


    }

    interface Printable<T> {
        void print(T stringToPrint);
    }

    interface Retrievable<T> {
        T retrieve();
    }

    interface Evaluate<T>{
        boolean isNegative(T integerToEvaluate);
    }

    interface Functionable<T, R>{
        R applyThis(T type);
    }

    static class Person {
        Integer age;
        String name;
        Double height;

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", height=" + height +
                    '}';
        }

        Person(String name, Integer age, Double height){
            this.age = age;
            this.name = name;
            this.height = height;
        }

        public Integer getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public Double getHeight() {
            return height;
        }
    }

    void consumer() {
        Printable<String> printable = System.out::println;
        Consumer<String> consumer = printable::print;
        consumer.accept("Hello World!");
    }

    void supplier() {
        Retrievable<Integer> retrievable = () -> 77;
        Supplier<Integer> supplier = retrievable::retrieve;
        System.out.println(supplier.get());
    }

    void predicate(){
        Evaluate<Integer> evaluate = i -> i < 0;
        System.out.println(evaluate.isNegative(-1));
        System.out.println(evaluate.isNegative(1));

        Predicate<Integer> predicate = evaluate::isNegative;
        System.out.println(predicate.test(-1));
        System.out.println(predicate.test(1));
    }

    boolean check(T type, Predicate<T> predicate){
        return predicate.test(type);
    }

    void function(){
        Functionable<Integer, String> functionable = i -> "Number is " + i;
        System.out.println(functionable.applyThis(25));

        Function<Integer, String> function = i -> "Number is " + i;
        System.out.println(function.apply(25));
    }

    List<Person> getPeople(){
        List<Person> result = new ArrayList<>();
        result.add(new Person("Mike", 33, 1.8 ));
        result.add(new Person("Mary", 25, 1.4 ));
        result.add(new Person("Alan", 34, 1.7 ));
        result.add(new Person("Zoe", 30, 1.5 ));

        return result;
    }

    void sortAge(List<Person> personList){
        personList.sort(Comparator.comparing(person -> person.getAge()));
        personList.forEach(person -> System.out.println(person.toString()));
    }

    void sortName(List<Person> personList){
        personList.sort(Comparator.comparing(person -> person.getName()));
        personList.forEach(person -> System.out.println(person.toString()));

    }

    void sortHeight(List<Person> personList){
        personList.sort(Comparator.comparing(Person::getHeight));
        personList.forEach(person -> System.out.println(person.toString()));
    }

}
