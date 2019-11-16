# Forge Blockstate Data Generator Test Mod

Test mod that demonstrates that mod resources aren't being loaded during data generation, causing calls to `ModelBuilder#texture`
to fail with `IllegalArgumentException: Texture <texture> does not exist in any known resource pack` for mod textures.
