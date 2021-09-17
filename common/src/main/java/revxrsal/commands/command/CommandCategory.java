package revxrsal.commands.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;
import revxrsal.commands.annotation.Default;
import revxrsal.commands.core.CommandPath;

import java.util.Map;

/**
 * Represents a command category.
 * <p>
 * Command categories do not have explicit actions by themselves, however
 * they can include the following:
 * <ul>
 *     <li>Subcommands, which are executable commands that perform specific actions</li>
 *     <li>Subactions, such as {@link Default}.</li>
 * </ul>
 */
public interface CommandCategory {

    /**
     * Returns the name of this category
     *
     * @return The category name
     */
    @NotNull String getName();

    /**
     * Returns the full command path to this category
     *
     * @return The command path
     */
    @NotNull CommandPath getPath();

    /**
     * Returns the parent category of this category. This can be null
     * in case of root categories.
     *
     * @return The parent category
     */
    @Nullable CommandCategory getParent();

    /**
     * Returns the {@link ExecutableCommand} of this category that is
     * executed when no arguments are supplied for the category.
     *
     * @return The category's default action
     * @see Default
     */
    @Nullable ExecutableCommand getDefaultAction();

    /**
     * Returns an unmodifiable view of all the sub-categories in this
     * category.
     *
     * @return The sub-categories
     */
    @NotNull @UnmodifiableView Map<CommandPath, CommandCategory> getCategories();

    /**
     * Returns an unmodifiable view of all the commands in this
     * category.
     *
     * @return The subcommands
     */
    @NotNull @UnmodifiableView Map<CommandPath, ExecutableCommand> getCommands();

}
