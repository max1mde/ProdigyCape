package fr.cocoraid.prodigycape.support.entities_1_19_4;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityType;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class DisplayItemNMS extends DisplayNMS {

    private Display.ItemDisplay display;

    public DisplayItemNMS(World world) {
        super(world, "DISPLAY_ITEM", new Display.ItemDisplay(EntityType.ITEM_DISPLAY, ((CraftWorld) world).getHandle()));
        this.display = (Display.ItemDisplay) entity;
    }
    public void setItemStack(ItemStack itemStack) {
        display.setItemStack(
                CraftItemStack.asNMSCopy(itemStack)
        );
    }

}
