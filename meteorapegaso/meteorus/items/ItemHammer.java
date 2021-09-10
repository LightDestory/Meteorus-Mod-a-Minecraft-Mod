package meteorapegaso.meteorus.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHammer extends Item {
	public ItemHammer(int par1) {
		super(par1);
		this.setMaxDamage(50);
		this.setNoRepair();
	}
	@Override
    public float getDamageVsEntity(Entity par1Entity, ItemStack itemStack)
    {
        return 0.5F;
    }
}
