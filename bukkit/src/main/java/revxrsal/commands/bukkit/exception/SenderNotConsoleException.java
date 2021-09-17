package revxrsal.commands.bukkit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.bukkit.BukkitCommandActor;

/**
 * Thrown when a console-only command is executed by a non-console
 */
@Getter
@AllArgsConstructor
public class SenderNotConsoleException extends RuntimeException {

    /**
     * The command actor that failed to be a console.
     */
    private final @NotNull BukkitCommandActor actor;

}
