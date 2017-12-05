package com.horstmann.corejava.v2ch01;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TransferQueue;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

//        List<Integer> words = new ArrayList<>();
//        words.add(1);
//        words.add(2);
//        words.add(3);
//        words.add(4);

//        System.out.println(words.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
//        words.parallelStream().forEachOrdered(System.out::println);
//        String[] str = words.stream().toArray(String[]::new);
//        System.out.println(Arrays.toString(str));

//        TreeSet<String> list = words.stream().collect(Collectors.toCollection(TreeSet::new));
//        System.out.println(list);
//        Set<String> list = words.stream().collect(Collectors.toSet());
//        System.out.println(list.getClass().getName());

//        String s = words.stream().collect(Collectors.joining(", "));
//        System.out.println(s);

//        IntSummaryStatistics summaryStatistics = words.stream().collect(Collectors.summarizingInt(Integer::intValue));
//        long sum = summaryStatistics.getSum();
//        long count = summaryStatistics.getCount();
//        double average = summaryStatistics.getAverage();
//        int max = summaryStatistics.getMax();
//        int min = summaryStatistics.getMin();
//
//        System.out.println(sum);
//        System.out.println(count);
//        System.out.println(average);
//        System.out.println(max);
//        System.out.println(min);
//
//        int sum0 = words.stream().collect(Collectors.summingInt(Integer::intValue));
//        System.out.println(sum0);
//
//        double sum1 = words.stream().mapToInt(Integer::intValue).average().orElse(0);
//        System.out.println(sum1);
//
//        IntSummaryStatistics intSummaryStatistics = words.stream().mapToInt(Integer::intValue).summaryStatistics();
//        double a = intSummaryStatistics.getAverage();
//        System.out.println(a);



//        BankCard bc0 = new BankCard("中国银行", 1000.39);
//        BankCard bc1 = new BankCard("中国建设银行", 2000.28);
//        BankCard bc2 = new BankCard("中国工商银行", 3000.64);
//
//        Person p0 = new Person(1, "张三", bc0);
//        Person p1 = new Person(2, "李四", bc1, bc2);
//        Person p2 = new Person(3, "王五", bc0, bc1, bc2);
//
//        List<Person> persons = new ArrayList<>();
//        persons.add(p0);
//        persons.add(p1);
//        persons.add(p2);
//
//        //法一
//        List<BankCard> allBankCards = new ArrayList<>();
//        persons.forEach(it -> allBankCards.addAll(it.getBankCards()));
//        BigDecimal sum = allBankCards.stream().map(BankCard::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
//        System.out.println(sum);
//
//        //法二
//        sum = persons.stream().map(person ->
//                person.getBankCards().stream().map(BankCard::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add)
//        ).reduce(BigDecimal.ZERO, BigDecimal::add);
//        System.out.println(sum);
//
//        //法三
//        sum = persons.stream().flatMap(person -> person.getBankCards().stream().map(BankCard::getMoney)).reduce(BigDecimal.ZERO, BigDecimal::add);
//        System.out.println(sum);

        System.out.println(Math.round(-11.5));

    }
}

class Person {

    private Long id;
    private String name;
    private List<BankCard> bankCards;

    public Person(long id, String name, BankCard... bankCards) {
        this.id = id;
        this.name = name;
        this.bankCards = Arrays.asList(bankCards);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BankCard> getBankCards() {
        return bankCards;
    }

    public void setBankCards(List<BankCard> bankCards) {
        this.bankCards = bankCards;
    }
}

class BankCard {

    private String bankName;
    private BigDecimal money;

    public BankCard(String bankName, double money) {
        this.bankName = bankName;
        this.money = BigDecimal.valueOf(money);
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
