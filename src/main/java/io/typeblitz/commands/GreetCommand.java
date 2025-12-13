package io.typeblitz.commands;


import picocli.CommandLine.Command;

@Command(name = "greet")
public class GreetCommand implements Runnable{

    @Override
    public void run() {
        System.out.println("hello there");
    }
}
