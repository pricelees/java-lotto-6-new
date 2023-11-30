package lotto.view;

public class Printer {
    public void print(Object input) {
        System.out.println(input);
    }

    public void printFormat(String format, Object value) {
        System.out.printf(format, value);
    }

    public void printFormat(String format, Object... args) {
        System.out.printf(format, args);
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
