package com.teamdman_9201.nova;

import com.teamdman_9201.nova.enchantments.EnchantmentPow;
import com.teamdman_9201.nova.items.sigils.ItemSigilOfTransposition;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by TeamDman on 2015-05-30.
 */
public class NOVAConfig {
    public static Configuration config;
    public static ArrayList<String> blacklist = new ArrayList<String>();

    public static void init(File cfg) {
        config = new Configuration(cfg);
        try {
            config.load();
            syncConfig();
        } catch(Exception e) {
            System.out.println("NOVA config file failed to load.");
        } finally {
            config.save();
        }
    }

    public static void syncConfig() {
        NOVA.doLowerChat = config.get("General", "Lowercase Incoming Messages", false).getBoolean(false);

        if(NOVA.enchantPow == null)
            NOVA.enchantPow = new EnchantmentPow(config.get("Enchantments", "Pow", 214).getInt(), 1);

        for(String unloc : blacklist)
            NOVA.blacklist.put(unloc, config.get("Blacklist", unloc, false).getBoolean());

        ItemSigilOfTransposition.canMoveTiles = config.get("General", "Transposition Can Move Tiles", true).getBoolean();
        NOVA.ritualData.put("ritual.sol", config.get("Ritual Blacklist", "Ritual of Sol", false).getBoolean() ? 1 : 0);
        NOVA.ritualData.put("ritual.luna", config.get("Ritual Blacklist", "Ritual of Luna", false).getBoolean() ? 1 : 0);
        NOVA.ritualData.put("ritual.uncreate", config.get("Ritual Blacklist", "Ritual of Uncreation", false).getBoolean() ? 1 : 0);
        NOVA.ritualData.put("ritual.entropy", config.get("Ritual Blacklist", "Ritual of Entropy", false).getBoolean() ? 1 : 0);

        NOVA.ritualData.put("level.sol", config.get("Ritual Levels", "Sol Level", 1).getInt());
        NOVA.ritualData.put("level.luna", config.get("Ritual Levels", "Luna Level", 2).getInt());
        NOVA.ritualData.put("level.uncreate", config.get("Ritual Levels", "Uncreation Level", 2).getInt());
        NOVA.ritualData.put("level.entropy", config.get("Ritual Levels", "Entropy Level", 1).getInt());

        NOVA.ritualData.put("upkeep.sol", config.get("Ritual Costs", "Sol Upkeep", 10).getInt());
        NOVA.ritualData.put("upkeep.luna", config.get("Ritual Costs", "Luna Upkeep", 20).getInt());
        NOVA.ritualData.put("upkeep.uncreate", config.get("Ritual Costs", "Uncreation Upkeep", 10).getInt());
        NOVA.ritualData.put("upkeep.entropy", config.get("Ritual Costs", "Entropy Upkeep", 10).getInt());

        NOVA.ritualData.put("init.sol", config.get("Ritual Costs", "Sol Init", 1000).getInt());
        NOVA.ritualData.put("init.luna", config.get("Ritual Costs", "Luna Init", 10000).getInt());
        NOVA.ritualData.put("init.uncreate", config.get("Ritual Costs", "Uncreation Init", 50000).getInt());
        NOVA.ritualData.put("init.entropy", config.get("Ritual Costs", "Entropy Init", 1000).getInt());

        config.save();
    }
}