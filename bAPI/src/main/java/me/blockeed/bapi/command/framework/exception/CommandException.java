package me.blockeed.bapi.command.framework.exception;

public class CommandException extends RuntimeException {
    public CommandException(String exception) {
        super(exception);
    }
}
