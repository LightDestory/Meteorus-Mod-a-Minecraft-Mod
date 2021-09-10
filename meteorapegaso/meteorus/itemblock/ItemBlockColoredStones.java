package meteorapegaso.meteorus.itemblock;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockColoredStones extends ItemBlock
{
       public ItemBlockColoredStones(int par1)
       {
             super(par1);
             setHasSubtypes(true);
       }
      
       public String getUnlocalizedName(ItemStack itemstack)
       {
             String name = "";
             switch(itemstack.getItemDamage())
             {
                    case 0:
                    {
                           name = "black";
                           break;
                    }
                    case 1:
                    {
                           name = "red";
                           break;
                    }
                    case 2:
                    {
                           name = "green";
                           break;
                    }
                    case 3:
                    {
                           name = "brown";
                           break;
                    }
                    case 4:
                    {
                           name = "blue";
                           break;
                    }
                    case 5:
                    {
                           name = "purple";
                           break;
                    }
                    case 6:
                    {
                           name = "cyan";
                           break;
                    }
                    case 7:
                    {
                           name = "pink";
                           break;
                    }
                    case 8:
                    {
                           name = "lightgreen";
                           break;
                    }
                    case 9:
                    {
                           name = "yellow";
                           break;
                    }
                    case 10:
                    {
                           name = "lightblue";
                           break;
                    }
                    case 11:
                    {
                           name = "magenta";
                           break;
                    }
                    case 12:
                    {
                           name = "orange";
                           break;
                    }
                    case 13:
                    {
                           name = "white";
                           break;
                    }
                    default: name = "broken";
             }
             return getUnlocalizedName() + "." + name;
       }
      
       public int getMetadata(int par1)
       {
             return par1;
       }
}