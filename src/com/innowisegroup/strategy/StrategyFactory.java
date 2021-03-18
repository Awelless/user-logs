package com.innowisegroup.strategy;

public class StrategyFactory {

    public Strategy create(String type) throws StrategyException {

        return switch (type) {
            case "1" -> new CreateUserStrategy();
            case "2" -> new UpdateUserStrategy();
            case "3" -> new DeleteUserStrategy();
            case "4" -> new FindUserStrategy();
            default -> throw new StrategyException("Invalid type");
        };
    }
}
