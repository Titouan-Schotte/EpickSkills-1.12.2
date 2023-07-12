package fr.epickskills.skills.items;

import fr.epickskills.skills.Main;
import net.minecraft.item.Item;

public class ItemMod extends Item {
    public ItemMod(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.creativeTab);
    }
}
