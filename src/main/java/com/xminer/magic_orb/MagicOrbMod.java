package com.xminer.magic_orb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MagicOrbMod.MOD_ID)
public class MagicOrbMod {
	private static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "magic_orb_mod";

	public static OrbBlock orbBlock;
	public static BlockItem orbItem;

	public MagicOrbMod() {
		FMLJavaModLoadingContext.get().getModEventBus().register(MagicOrbMod.class);
	}

	@SubscribeEvent
	public static void onBlocksRegistration(final RegistryEvent.Register<Block> blockRegisterEvent) {
		LOGGER.info("Registering Magic Orb Block");
		orbBlock = (OrbBlock) (new OrbBlock().setRegistryName(MOD_ID, OrbBlock.BLOCK_ID));
		blockRegisterEvent.getRegistry().register(orbBlock);
	}

	@SubscribeEvent
	public static void onItemsRegistration(final RegistryEvent.Register<Item> itemRegisterEvent) {
		LOGGER.info("Registering Magic Orb Item");
		Item.Properties itemProps = new Item.Properties().maxStackSize(99).group(ItemGroup.MISC);
		orbItem = new BlockItem(orbBlock, itemProps);
		orbItem.setRegistryName(orbBlock.getRegistryName());
		itemRegisterEvent.getRegistry().register(orbItem);
	}

	@SubscribeEvent
	public static void onClientSetupEvent(FMLClientSetupEvent event) {
		LOGGER.info("Setting Render Layer for Orb");
		RenderTypeLookup.setRenderLayer(orbBlock, RenderType.getSolid());
	}

}
