package com.innowisegroup.data;

import java.util.Scanner;

public class ConsoleDataReader implements DataReader {

    private static final ConsoleDataReader INSTANCE = new ConsoleDataReader();

    private final Scanner scanner = new Scanner(System.in);

    private ConsoleDataReader() {
    }

    public static ConsoleDataReader getInstance() {
        return INSTANCE;
    }

    public String readLine() {
        return scanner.nextLine();
    }
}
