package meteorapegaso.meteorus.blocks;

import java.util.Random;

import meteorapegaso.meteorus.client.ClientProxy;
import meteorapegaso.meteorus.common.mod_meteorus;
import meteorapegaso.meteorus.tileentity.TileEntityProjectTable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockProjectTable extends BlockContainer {
	
	private Class TileEntityClass;
	
		public BlockProjectTable(int i,Class tClass, Material par3Material)
		  {
		        super(i, par3Material);
		        TileEntityClass = tClass;
		           float f = 0F;
		           setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.05F, 1.0F);
		  }
		  
		@Override
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t)
		{
		    TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		    
		            if(tile_entity == null || player.isSneaking()){
		            return false;
		            }

		    player.openGui(mod_meteorus.instance, 0, world, x, y, z);
		    return true;
		    }


		@Override
		public void breakBlock(World world, int x, int y, int z, int i, int j)
		{
		    super.breakBlock(world, x, y, z, i, j);
		    }

		
		        public TileEntity getBlockEntity()
		        {
		                        try{
		                                        return (TileEntity)TileEntityClass.newInstance();
		                        }
		                        catch(Exception exception){
		                                        throw new RuntimeException(exception);
		                        }
		        }
		   
		        public int idDropped(int i, Random random, int j)
		        {
		                return this.blockID;
		        }
		   
		        
		        public int quanityDropped(Random random)
		        {
		           return 1;
		        }
		   
		        @Override
		        public int getRenderType()
		        {
		        	 return ClientProxy.BlockProjectTableRenderID;
		        }
		   
		        public boolean isOpaqueCube()
		        {
		                return false;
		        }
		   
		        public boolean renderAsNormalBlock()
		        {
		                return false;
		        }
		public TileEntity createNewTileEntity(World world)
		{
		  return new TileEntityProjectTable();

		}
		
}



