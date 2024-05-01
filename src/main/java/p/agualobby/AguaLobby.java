package p.agualobby;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import p.agualobby.Listener.JoinListener;

public final class AguaLobby extends JavaPlugin {
    private static AguaLobby instance;

    private JoinListener joinListener;
    public AguaLobby() {
        // Este constructor vacío es necesario para que Bukkit pueda inicializar el plugin correctamente
    }

    @Override
    public void onEnable() {
        instance = this;
        // Crea una instancia del listener
        this.joinListener = new JoinListener();
        getServer().getPluginManager().registerEvents(joinListener, this);
    }

    @Override
    public void onDisable() {
        // Desregistra los eventos
    }


    public static AguaLobby getInstance() {
        return instance;
    }
}
