package meteorapegaso.meteorus.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemChisel extends Item {
	public ItemChisel(int par1) {
		super(par1);
		this.setMaxDamage(30);
		this.setNoRepair();
	}
	@Override
    public float getDamageVsEntity(Entity par1Entity, ItemStack itemStack)
    {
        return 3.5F;
    }
}
