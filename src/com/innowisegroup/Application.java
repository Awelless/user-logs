package com.innowisegroup;

import com.innowisegroup.data.ConsoleDataReader;
import com.innowisegroup.data.DataReader;
import com.innowisegroup.initializer.InitializationException;
import com.innowisegroup.initializer.Initializer;
import com.innowisegroup.strategy.Strategy;
import com.innowisegroup.strategy.StrategyException;
import com.innowisegroup.strategy.StrategyFactory;

public class Application {

    private static final String CHOOSE_ACTION_MESSAGE = """
            1 - Create new user
            2 - Update existing user
            3 - Delete user
            4 - Get user info
            0 - Exit
            Enter action type:""";

    private static final String EXIT_CODE = "0";

    private static void run() {

        DataReader dataReader = ConsoleDataReader.getInstance();
        StrategyFactory strategyFactory = new StrategyFactory();

        while (true) {
            System.out.println(CHOOSE_ACTION_MESSAGE);

            String type = dataReader.readLine();

            if (type.equals(EXIT_CODE)) {
                break;
            }

            Strategy strategy;
            try {
                strategy = strategyFactory.create(type);
            } catch (StrategyException e) {
                System.out.println("Invalid action type. Try again");
                continue;
            }

            strategy.execute();

            System.out.println("-------------------------------");
        }
    }

    public static void main(String[] args) throws InitializationException {

        System.out.println("Application started");

        Initializer.initialize();
        run();

        System.out.println("Shutting down...");
    }
}
