package nms;

import fr.cocoraid.prodigycape.nms.NmsHandler;
import fr.cocoraid.prodigycape.utils.Reflection;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public  class NMS_1_20_2 implements NmsHandler {

    private static final Class<?> craftPlayerClass = Reflection.getCraftBukkitClass("entity.CraftPlayer");
    private static final Reflection.MethodInvoker getHandleMethod = Reflection.getMethod(craftPlayerClass, "getHandle");


    @Override
    public Object clientInfoWithoutCape(Object object) {
            ClientInformation clientInformation = (ClientInformation) object;
            return new ClientInformation(
                    clientInformation.language(),
                    clientInformation.viewDistance(),
                    clientInformation.chatVisibility(),
                    clientInformation.chatColors(),
                    126,
                    clientInformation.mainHand(),
                    clientInformation.textFilteringEnabled(),
                    clientInformation.allowsListing()
            );

    }

    @Override
    public void removeCape(Player player) {
        ServerPlayer sp = (ServerPlayer) getHandleMethod.invoke(player);

        SynchedEntityData entityData = sp.getEntityData();
        entityData.set(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), (byte) 126);
        List<SynchedEntityData.DataValue<?>> eData = new ArrayList<>();
        eData.add(SynchedEntityData.DataValue.create(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), (byte) 126));


        ClientboundSetEntityDataPacket meta = new ClientboundSetEntityDataPacket(sp.getId(), eData);
        sp.connection.send(meta);
    }
}
