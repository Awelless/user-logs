package com.innowisegroup.uistrategy;

public class UiStrategyFactory {

    public UiStrategy create(String type) throws UiStrategyException {

        return switch (type) {
            case "1" -> new CreateUserStrategy();
            case "2" -> new UpdateUserStrategy();
            case "3" -> new DeleteUserStrategy();
            case "4" -> new FindUserStrategy();
            default -> throw new UiStrategyException("Invalid type");
        };
    }
}
