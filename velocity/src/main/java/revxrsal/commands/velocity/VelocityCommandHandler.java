package revxrsal.commands.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.CommandHandler;
import revxrsal.commands.velocity.core.VelocityHandler;

/**
 * Represents Velocity's command handler implementation
 */
public interface VelocityCommandHandler extends CommandHandler {

    /**
     * Returns the {@link ProxyServer} this command handler was
     * registered on.
     *
     * @return The proxy server.
     */
    ProxyServer getServer();

    /**
     * Creates a new {@link VelocityCommandHandler} for the given proxy.
     *
     * @param server Server proxy to register for
     * @return The newly created handler
     */
    static VelocityCommandHandler create(@NotNull ProxyServer server) {
        return new VelocityHandler(server);
    }

}
