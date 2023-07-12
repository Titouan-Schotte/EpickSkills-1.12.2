package fr.epickskills.skills.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class EpickaItemTab extends CreativeTabs {

    public EpickaItemTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(InitItems.icon_epicka);
    }

}
