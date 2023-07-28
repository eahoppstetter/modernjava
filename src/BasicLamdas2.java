import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class BasicLamdas2 {
    public static void main(String[] args) {
        BasicLamdas2 bl = new BasicLamdas2();
        //bl.staticMR();
        //bl.boundMR();
        //bl.unboundMR();
        bl.constructorMR();

    }

    void staticMR() {
        List<Integer> intList = new ArrayList<Integer>(Arrays.asList(1, 2, 7, 4, 5));
        System.out.println(intList);
        Consumer<List<Integer>> consumer = list -> Collections.sort(list);
        consumer.accept(intList);
        System.out.println(intList);

        intList = new ArrayList<Integer>(Arrays.asList(1, 2, 7, 4, 5));
        System.out.println(intList);
        consumer = Collections::sort;
        consumer.accept(intList);
        System.out.println(intList);


    }

    void boundMR() {
        String name = "Mr. Joe Bloggs";
        Predicate<String> predicate = prefix -> name.startsWith(prefix);
        predicate = name::startsWith;
        System.out.println(predicate.test("Mr"));
        System.out.println(predicate.test("Mrs"));
        predicate = name::startsWith;
        System.out.println(predicate.test("Mr"));
        System.out.println(predicate.test("Mrs"));

    }

    void unboundMR() {
        Predicate<String> predicate = str -> str.isEmpty();
        System.out.println(predicate.test(""));
        System.out.println(predicate.test("hello"));
        predicate = String::isEmpty;
        System.out.println(predicate.test(""));
        System.out.println(predicate.test("hello"));

        BiPredicate<String, String> biPredicate = (str, str2) -> str.startsWith(str2);
        System.out.println(biPredicate.test("Mr. Joe Bloggs", "Mr"));
        System.out.println(biPredicate.test("Mr. Joe Bloggs", "Mrs"));
        biPredicate = String::startsWith;
        System.out.println(biPredicate.test("Mr. Joe Bloggs", "Mr"));
        System.out.println(biPredicate.test("Mr. Joe Bloggs", "Mrs"));
    }

    void constructorMR() {
        Supplier<List<String>> supplier = () -> new ArrayList<String>();
        List<String> list = supplier.get();
        list.add("Lambda");
        System.out.println(list);
        supplier = ArrayList::new;
        list = supplier.get();
        list.add("Method Reference");
        System.out.println(list);

        Function<Integer, List<String>> function = size -> new ArrayList<>(size);
        list = function.apply(10);
        list.add("Lambda");
        System.out.println(list);

        function = ArrayList::new;
        list = function.apply(10);
        list.add("Lambda");
        System.out.println(list);

    }
}
