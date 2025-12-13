package io.typeblitz;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import io.typeblitz.commands.GreetCommand;
import io.typeblitz.commands.TermCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "typeblitz", description = "...",
        mixinStandardHelpOptions = true,
        subcommands = {GreetCommand.class, TermCommand.class}
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
