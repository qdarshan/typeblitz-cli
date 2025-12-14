package io.typeblitz;

import io.micronaut.configuration.picocli.PicocliRunner;

import io.typeblitz.commands.GreetCommand;
import io.typeblitz.commands.StartCommand;
import picocli.CommandLine.Command;

@Command(name = "typeblitz", description = "...",
        mixinStandardHelpOptions = true,
        subcommands = {GreetCommand.class, StartCommand.class}
)
public class TypeblitzCommand implements Runnable {

    @Command
    String greet;

    static void main(String[] args) throws Exception {
        PicocliRunner.run(TypeblitzCommand.class, args);
    }

    public void run() {
        System.out.println("Hi");
    }
}
