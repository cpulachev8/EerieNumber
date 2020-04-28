package com.cpulachev.eerienumber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EerieNumber {

    private static Integer EERIE_NUMBER = 6174;

    public void findEerieNumber(Integer number) {
        System.out.println("***** Número Elegido - "+ number+" *******");
        int i = 1;
        while (!EERIE_NUMBER.equals(number)) {
            String sNumber = String.format("%04d", number);
            Integer numberMax = getNumberHigher(sNumber);
            Integer numberMin = getNumberLess(sNumber);
            number = numberMax - numberMin;
            System.out.println("[" + i + "] " + numberMax + " - " + numberMin + " = " + number);
            i++;
        }
    }

    private Integer getNumberLess(String sNumber) {
        List<Integer> numberList = new ArrayList<>();
        fillListInt(sNumber, numberList);
        return getNumberFromList(sortList(numberList, Boolean.FALSE));
    }

    private Integer getNumberHigher(String sNumber) {
        List<Integer> numberList = new ArrayList<>();
        fillListInt(sNumber, numberList);
        return getNumberFromList(sortList(numberList, Boolean.TRUE));
    }

    private void fillListInt(String sNumber, List<Integer> numberList) {
        IntStream.range(0, sNumber.length())
                .forEach(i -> numberList.add(Integer.valueOf(sNumber.substring(i, i+1))));
    }

    private Integer getNumberFromList(List<Integer> sortList) {
        String numberHigher = sortList.stream()
                .map(n -> n.toString())
                .collect(Collectors.joining(""));
        return Integer.valueOf(numberHigher);
    }

    private List<Integer> sortList(List<Integer> list, Boolean asc) {
        return list.stream()
                .sorted(asc ? Comparator.comparing(Integer::intValue).reversed() : Comparator.comparing(Integer::intValue))
                .collect(Collectors.toList());
    }

}
