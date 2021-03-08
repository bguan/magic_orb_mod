package com.xminer.magic_orb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OrbBlock extends Block {

	public static final String BLOCK_ID = "orb_block";

	private static final Logger LOGGER = LogManager.getLogger();
	private static final VoxelShape INNER_CUBE_SHAPE = VoxelShapes.create(.3, .3, .3, .7, .7, .7);

	public OrbBlock() {
		super(Block.Properties.create(Material.WOOD).hardnessAndResistance(1).sound(SoundType.WOOD));
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		LOGGER.info("Gift for player.");
		event.getPlayer().inventory.addItemStackToInventory(new ItemStack(MagicOrbMod.orbItem));
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		player.inventory.addItemStackToInventory(new ItemStack(state.getBlock().asItem()));
		super.onBlockHarvested(worldIn, pos, state, player);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return INNER_CUBE_SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return INNER_CUBE_SHAPE;
	}
}
