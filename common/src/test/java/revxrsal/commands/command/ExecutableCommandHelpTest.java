package revxrsal.commands.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import revxrsal.commands.Lamp;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.CommandPlaceholder;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.help.Help;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

class ExecutableCommandHelpTest {

    private Lamp<CommandActor> lamp;

    @BeforeEach
    void setUp() {
        lamp = Lamp.builder().build();
    }

    @Test
    void testSiblingCommands() {
        lamp.register(new CommandA(), new CommandB());
        ExecutableCommand<CommandActor> commandA = getCommand("a");
        ExecutableCommand<CommandActor> commandAA = getCommand("a a");
        ExecutableCommand<CommandActor> commandB = getCommand("b");

        assertPathsEquals(commandAA.siblingCommands(), "a b", "a");
        assertPathsEquals(commandA.siblingCommands(), "a a", "a b");
        assertPathsEquals(commandB.siblingCommands());
    }

    @Test
    void testChildrenCommands() {
        lamp.register(new CommandA(), new CommandB());
        ExecutableCommand<CommandActor> commandA = getCommand("a");
        ExecutableCommand<CommandActor> commandAA = getCommand("a a");
        ExecutableCommand<CommandActor> commandB = getCommand("b");

        assertPathsEquals(commandAA.childrenCommands());
        assertPathsEquals(commandA.childrenCommands(), "a a", "a b");
        assertPathsEquals(commandB.childrenCommands());
    }

    @Test
    void testRelatedCommands() {
        lamp.register(new CommandA(), new CommandB());
        ExecutableCommand<CommandActor> commandA = getCommand("a");
        ExecutableCommand<CommandActor> commandAA = getCommand("a a");
        ExecutableCommand<CommandActor> commandB = getCommand("b");

        assertPathsEquals(commandAA.relatedCommands(), "a b", "a");
        assertPathsEquals(commandA.relatedCommands(), "a a", "a b");
        assertPathsEquals(commandB.relatedCommands());
    }

    private ExecutableCommand<CommandActor> getCommand(String path) {
        return lamp.registry().commands().stream().filter(cmd -> cmd.path().equals(path))
                .findFirst().get();
    }

    private static void assertPathsEquals(Help.CommandList<CommandActor> commands,
            String... paths) {
        // we use sets here because we don't care about ordering
        assertEquals(new HashSet<>(Arrays.asList(paths)),
                commands.all().stream().map(cmd -> cmd.path()).collect(Collectors.toSet()));
    }

    @Command("a")
    class CommandA {

        @CommandPlaceholder
        public void placeholder() {}

        @Subcommand("a")
        public void a() {}

        @Subcommand("b")
        public void b() {}

    }

    @Command("b")
    class CommandB {

        @CommandPlaceholder
        public void b() {}

    }

}
