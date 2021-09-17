package revxrsal.commands.sponge.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import revxrsal.commands.sponge.SpongeCommandActor;
import revxrsal.commands.sponge.exception.SenderNotConsoleException;
import revxrsal.commands.sponge.exception.SenderNotPlayerException;

import java.util.UUID;

final class SpongeActor implements SpongeCommandActor {

    private static final UUID CONSOLE_UUID = new UUID(0, 0);

    private final CommandSource source;

    public SpongeActor(CommandSource source) {
        this.source = source;
    }

    @Override public @NotNull String getName() {
        return isConsole() ? "Console" : requirePlayer().getName();
    }

    @Override public @NotNull UUID getUniqueId() {
        return isConsole() ? CONSOLE_UUID : requirePlayer().getUniqueId();
    }

    @Override public void reply(@NotNull String message) {
        source.sendMessage(Text.of(message.replace('&', '\u00A7')));
    }

    @Override public void error(@NotNull String message) {
        reply("&c" + message);
    }

    @Override public @NotNull CommandSource getSource() {
        return source;
    }

    @Override public boolean isPlayer() {
        return source instanceof Player;
    }

    @Override public boolean isConsole() {
        return source instanceof ConsoleSource;
    }

    @Override public @Nullable Player getAsPlayer() {
        return isPlayer() ? (Player) source : null;
    }

    @Override public @NotNull Player requirePlayer() throws SenderNotPlayerException {
        if (!isPlayer())
            throw new SenderNotPlayerException(this);
        return (Player) source;
    }

    @Override public @NotNull ConsoleSource requireConsole() throws SenderNotConsoleException {
        if (!isPlayer())
            throw new SenderNotConsoleException(this);
        return (ConsoleSource) source;
    }

    @Override public void reply(@NotNull Text message) {
        source.sendMessage(message);
    }
}
