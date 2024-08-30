import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1
        List<Transaction> yearFilteredTransactions = yearFilteredTransactions(transactions);
        for (Transaction transaction: yearFilteredTransactions) {
            System.out.println(transaction);
        }

        //2
        printCityList(transactions);
        System.out.println();

        //3
        List<Trader> cambridgeTradersList = cambridgeTradersList(transactions);
        for (Trader trader: cambridgeTradersList) {
            System.out.println(trader);
        }
        System.out.println();

        //4
        String names = getStringTradersNames(transactions);
        System.out.println(names);
        System.out.println();

        //5
        boolean fromMilan = isTraderFromMilan(transactions);
        System.out.println(fromMilan);
        System.out.println();

        //6
        printValues(transactions);
        System.out.println();

        //7
        int maxValue = maxValue(transactions);
        System.out.println(maxValue);
        System.out.println();

        //8
        Transaction minValueTransaction = minValueTransaction(transactions);
        System.out.println(minValueTransaction);
        System.out.println();

    }

    public static List<Transaction> yearFilteredTransactions(List<Transaction> transactions) {
        return transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(t -> t.getValue()))
                .collect(Collectors.toList());
    }

    public static void printCityList(List<Transaction> transactions) {
        transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().forEach(s -> System.out.println(s));
    }

    public static List<Trader> cambridgeTradersList(List<Transaction> transactions) {
        return transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getTrader())
                .sorted(Comparator.comparing(trader -> trader.getName())).collect(Collectors.toList());
    }

    public static String getStringTradersNames(List<Transaction> transactions) {
        return Arrays.stream(transactions.stream().map(t -> t.getTrader().getName()).toArray(String[]::new)).sorted(String::compareTo)
                .map(s -> s + " ").collect(Collectors.joining());
    }


    public static boolean isTraderFromMilan(List<Transaction> transactions){
        return transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
    }

    public static void printValues(List<Transaction> transactions) {
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .forEach(transaction -> System.out.println(transaction.getValue()));
    }

    public static int maxValue(List<Transaction> transactions) {
        return transactions.stream().mapToInt(transaction -> transaction.getValue()).max().getAsInt();
    }

    public static Transaction minValueTransaction(List<Transaction> transactions) {
        return transactions.stream().min(Comparator.comparingInt(Transaction::getValue)).get();
    }

}
