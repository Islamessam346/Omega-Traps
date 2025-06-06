package me.eslam.omegatraps.blocks;

import me.eslam.omegatraps.OmegaTraps;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ModBlocks {
    public static final Block FAKE_IRON_BLOCK = registerBlock(
            "fake_iron_block",
            new Block(FabricBlockSettings.create()
                    .mapColor(MapColor.STONE_GRAY)
                    .strength(0.5f, 2.5f)
                    .sounds(BlockSoundGroup.STONE)
                    .ticksRandomly()
                    .lightLevel(state -> 3)
                    .nonOpaque()
                    .solidBlock((state, world, pos) -> true)));


    public static final Block WEAK_LEAVES = registerBlock(
            "weak_leaves",
            new Block(AbstractBlock.Settings.create()
                    .strength(0.2f)
                    .nonOpaque()
            ) {
                private final VoxelShape SHAPE = Block.createCuboidShape(0, 14, 0, 16, 16, 16); // 2 pixels high

                @Override
                public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                    return SHAPE;
                }

                @Override
                public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                    return SHAPE;
                }

                @Override
                public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
                    if (!world.isClient && entity instanceof LivingEntity) {
                        world.breakBlock(pos, true); // Breaks the block and drops it
                    }
                    super.onSteppedOn(world, pos, state, entity);
                }
            }
    );


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(OmegaTraps.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(OmegaTraps.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        OmegaTraps.LOGGER.info("Registering Mod Blocks for " + OmegaTraps.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.FAKE_IRON_BLOCK);
            entries.add(ModBlocks.WEAK_LEAVES);
        });
    }
}
