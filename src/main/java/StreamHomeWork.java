import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamHomeWork {
    /*  #1
     * Метод принимает на вход список имен. Вернуть строку в виде: "1. Ivan, 3. Peter ...",
     * с именами из списка, стоящими под нечетным индексом (1, 3 и т.д.);
     *
     *  #2
     * Метод принимает на вход список из строк (можно список из Задания 1).
     * Возвращает список этих строк в верхнем регистре и отсортированные по убыванию (от Z до А);
     *
     *  #3
     * Дан массив: ["1, 2, 0", "4, 5"]
     * Получить из массива все числа, вернуть в отсортированном виде,
     * разделенные запятой, то есть "0, 1, 2, 4, 5";
     *
     *  #4
     * Используя Stream.iterate сделайте бесконечный стрим рандомных чисел, но не используя Math.random.
     * Реализуйте свой "линейный конгруэнтный генератор". Для этого начните с x[0] = seed и затем каждый следующий
     * элемент x[n + 1] = 1 (a x[n] + c) % m, для корректных значений a, c, и m. Необходимо имплементировать метод,
     * который принимает на вход параметрыa, c, m и seed и возвращает Stream<Long>. Для теста используйте данные
     * a = 25214903917, c = 11 и m = 2^48 (2 в степени 48);
     *
     *  #5
     * Напишите метод public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) который "перемешивает"
     * элементы из стримов first и second, останавливается тогда, когда у одного из стримов закончатся элементы;
     *
     * */

    public static void main(String[] args) {

        // ex.1 & ex.2
        List<Personage> personage = new ArrayList<>(Arrays.asList(
                new Personage(1, "Batman"),
                new Personage(2, "Joker"),
                new Personage(3, "Penguin"),
                new Personage(4, "Poison Ivy"),
                new Personage(5, "Harley Quinn"),
                new Personage(6, "Deadpool"),
                new Personage(7, "Bane"),
                new Personage(8, "Batwoman"),
                new Personage(9, "Ragman"),
                new Personage(10, "Deadshot"),
                new Personage(11, "Batgirl"),
                new Personage(12, "Catwoman"),
                new Personage(13, "Riddler"),
                new Personage(14, "Robin")
        ));

        // ex.3
        String[] stringsDigits = new String[]{"1, 2, 0", "4, 5"};

        // ex.4
        long seed = 0;
        long a = 25214903917L;
        long c = 11;
        long m = (long) Math.pow(2, 48);

        // ex.5
        Stream<String> first = Arrays.stream(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"});
        Stream<String> second = Arrays.stream(new String[]{"one", "two", "three", "four", "five", "six", "seven",
                "eight", "nine", "zero"});


        // ex.1 call method
        getNameByOddId(personage);
        System.out.println("----------------------------");

        // ex.2 call method
        getNameByReverseSortedToUpperCase(personage);
        System.out.println("----------------------------");

        // ex.3 call method
        getSortedNumberStrings(stringsDigits);
        System.out.println("----------------------------");

        // ex.4 call method
        Stream<Long> longStream = generateInfinityStreamNumber(seed, a, c, m);
        longStream.forEach(System.out::println);
        System.out.println("----------------------------");


        // ex.5 call method
        Stream<String> zipShuffleStreams = zip(first, second);
        System.out.println(zipShuffleStreams.collect(Collectors.joining(", ")));

    }


    // ex.1 method
    private static void getNameByOddId(List<Personage> personage) {

        String oddNames = personage.stream()
                .filter(id -> id.getId() % 2 != 0)
                .map(person -> person.getId() + "." + person.getName())
                .collect(Collectors.joining(", "));

        System.out.println(oddNames);
    }

    // ex.2 method
    private static void getNameByReverseSortedToUpperCase(List<Personage> personage) {

        List<String> sortedReverseName = personage.stream()
                .map(person -> person.getName().toUpperCase())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println(sortedReverseName);
    }

    // ex.3 method
    private static void getSortedNumberStrings(String[] stringsDigits) {

        String resultSortedNumbers = Arrays.stream(stringsDigits)
                .flatMap(p -> Arrays.stream(p.replaceAll(" ", "").split(",")))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(", "));

        System.out.println(resultSortedNumbers);
    }


    /*
     * проверка работы метода не бесконечный цикл - убрать метод .limit(13)
     * */
    // ex.4 method
    private static Stream<Long> generateInfinityStreamNumber(long seed, long a, long c, long m) {
        return Stream.iterate(seed, x -> (a * x + c) % m).limit(13);
    }

    // ex.5 method
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> shuffleStreams = Stream.concat(first, second)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.shuffle(list);
                    return list;
                }));

        return shuffleStreams.stream();
    }
}
