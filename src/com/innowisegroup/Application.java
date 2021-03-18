package com.innowisegroup;

import com.innowisegroup.data.ConsoleDataReader;
import com.innowisegroup.data.DataReader;
import com.innowisegroup.initializer.InitializationException;
import com.innowisegroup.initializer.Initializer;
import com.innowisegroup.uistrategy.UiStrategy;
import com.innowisegroup.uistrategy.UiStrategyException;
import com.innowisegroup.uistrategy.UiStrategyFactory;

public class Application {

    private static final String CHOOSE_ACTION_MESSAGE =
            "1 - Create new user\n" +
            "2 - Update existing user\n" +
            "3 - Delete user\n" +
            "4 - Get user info\n" +
            "0 - Exit\n" +
            "Enter action type:";

    private static final String EXIT_CODE = "0";

    private static void run() {

        DataReader dataReader = ConsoleDataReader.getInstance();
        UiStrategyFactory uiStrategyFactory = new UiStrategyFactory();

        while (true) {
            System.out.println(CHOOSE_ACTION_MESSAGE);

            String type = dataReader.readLine();

            if (type.equals(EXIT_CODE)) {
                break;
            }

            UiStrategy uiStrategy;
            try {
                uiStrategy = uiStrategyFactory.create(type);
            } catch (UiStrategyException e) {
                System.out.println("Invalid action type. Try again");
                continue;
            }

            uiStrategy.execute();

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
