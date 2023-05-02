package com.tntp.assemblycarts.init;

import java.lang.reflect.Field;

import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.item.ItemAssemblyCart;
import com.tntp.assemblycarts.item.ItemCrowbarAssemblium;
import com.tntp.assemblycarts.item.ItemOreLookupTable;
import com.tntp.assemblycarts.item.ItemProcessBook;
import com.tntp.minecraftmodapi.APIiTNTPiston;
import com.tntp.minecraftmodapi.item.IItemRegisterFactory;
import com.tntp.minecraftmodapi.item.ItemAPIiTNTPiston;

import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraft.item.Item;

@ObjectHolder("assemblycarts")
public class ACItems {
    public static final Item assembly_worker_cart = null;

    public static final Item crowbar_assemblium = null;

    public static final Item process_book = null;
    public static final Item ore_lookup_table = null;

    public static final Item ingot_assemblium = null;
    public static final Item plate_assemblium = null;
    public static final Item green_quartz = null;

    public static void loadItems() {
        AssemblyCartsMod.log.info("Registering Items");
        IItemRegisterFactory reg = APIiTNTPiston.newItemRegister().creativeTabs(ACCreativeTabs.instance);
        reg.of(new ItemAssemblyCart(), "assembly_worker_cart").register();
        reg.of(new ItemCrowbarAssemblium(), "crowbar_assemblium").register();
        reg.of(new ItemProcessBook(), "process_book").register();
        reg.of(new ItemOreLookupTable(), "ore_lookup_table").register();
        reg.of(new ItemAPIiTNTPiston(), "ingot_assemblium").register();
        reg.of(new ItemAPIiTNTPiston(), "plate_assemblium").register();
        reg.of(new ItemAPIiTNTPiston(), "green_quartz").register();

    }

    public static void validateInjection() {
        AssemblyCartsMod.log.info("Validating Injection");
        Field[] field = ACItems.class.getDeclaredFields();
        for (Field f : field) {
            try {
                if (f.get(null) == null) {
                    AssemblyCartsMod.log.error("Incomplete Injection!");
                    throw new IllegalStateException(f.toString() + " is null");
                }
                AssemblyCartsMod.log.info("[Injection] " + f.getName() + " validated.");
            } catch (IllegalArgumentException | IllegalAccessException e) {
                AssemblyCartsMod.log.warn("Cannot check injection!");
            }
        }
    }

}
