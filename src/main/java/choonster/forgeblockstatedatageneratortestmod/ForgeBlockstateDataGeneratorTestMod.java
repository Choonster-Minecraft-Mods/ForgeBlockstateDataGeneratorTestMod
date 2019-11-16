package choonster.forgeblockstatedatageneratortestmod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(ForgeBlockstateDataGeneratorTestMod.MODID)
@Mod.EventBusSubscriber(bus = Bus.MOD)
public class ForgeBlockstateDataGeneratorTestMod {
	public static final String MODID = "forge_blockstate_data_generator_test_mod";

	private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);

	public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(Block.Properties.from(Blocks.DIRT)));

	public ForgeBlockstateDataGeneratorTestMod() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		BLOCKS.register(modEventBus);
	}

	@SubscribeEvent
	public static void gatherData(final GatherDataEvent event) {
		final DataGenerator dataGenerator = event.getGenerator();
		final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		if (event.includeClient()) {
			dataGenerator.addProvider(new TestBlockStateProvider(dataGenerator, existingFileHelper));
		}
	}

	public static class TestBlockStateProvider extends BlockStateProvider {
		public TestBlockStateProvider(final DataGenerator gen, final ExistingFileHelper exFileHelper) {
			super(gen, MODID, exFileHelper);
		}

		@Override
		protected void registerStatesAndModels() {
			simpleBlock(EXAMPLE_BLOCK.get());
		}
	}
}
