// $Id$
/*
 * WorldGuard
 * Copyright (C) 2010 sk89q <http://www.sk89q.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.worldguard.bukkit;


import static com.sk89q.worldguard.bukkit.BukkitUtil.isBlockLava;
import static com.sk89q.worldguard.bukkit.BukkitUtil.setBlockToLava;
import org.bukkit.World;

public class SpongeUtilLava {

//    I made this separate from SpongeUtil.java to keep it more distinct from the water draining function

    /**
     * Remove lava around a sponge.
     *
     * @param plugin
     * @param world
     * @param ox
     * @param oy
     * @param oz
     */
    public static void clearSpongeLava(WorldGuardPlugin plugin, World world, int ox, int oy, int oz) {
        ConfigurationManager cfg = plugin.getGlobalStateManager();
        WorldConfiguration wcfg = cfg.get(world);

        for (int cx = -wcfg.spongeRadius; cx <= wcfg.spongeRadius; cx++) {
            for (int cy = -wcfg.spongeRadius; cy <= wcfg.spongeRadius; cy++) {
                for (int cz = -wcfg.spongeRadius; cz <= wcfg.spongeRadius; cz++) {
                    if (isBlockLava(world, ox + cx, oy + cy, oz + cz)) {
                        world.getBlockAt(ox + cx, oy + cy, oz + cz).setTypeId(0);
                    }
                }
            }
        }
    }

/**
 * Add lava around a sponge.
 *
 * @param plugin
 * @param world
 * @param ox
 * @param oy
 * @param oz
 */
public static void addSpongeLava(WorldGuardPlugin plugin, World world, int ox, int oy, int oz) {
    ConfigurationManager cfg = plugin.getGlobalStateManager();
    WorldConfiguration wcfg = cfg.get(world);

    // The negative x edge
    int cx = ox - wcfg.spongeRadius - 1;
    for (int cy = oy - wcfg.spongeRadius - 1; cy <= oy + wcfg.spongeRadius + 1; cy++) {
        for (int cz = oz - wcfg.spongeRadius - 1; cz <= oz + wcfg.spongeRadius + 1; cz++) {
            if (isBlockLava(world, cx, cy, cz)) {
                setBlockToLava(world, cx + 1, cy, cz);
            }
        }
    }

    // The positive x edge
    cx = ox + wcfg.spongeRadius + 1;
    for (int cy = oy - wcfg.spongeRadius - 1; cy <= oy + wcfg.spongeRadius + 1; cy++) {
        for (int cz = oz - wcfg.spongeRadius - 1; cz <= oz + wcfg.spongeRadius + 1; cz++) {
            if (isBlockLava(world, cx, cy, cz)) {
                setBlockToLava(world, cx - 1, cy, cz);
            }
        }
    }

    // The negative y edge
    int cy = oy - wcfg.spongeRadius - 1;
    for (cx = ox - wcfg.spongeRadius - 1; cx <= ox + wcfg.spongeRadius + 1; cx++) {
        for (int cz = oz - wcfg.spongeRadius - 1; cz <= oz + wcfg.spongeRadius + 1; cz++) {
            if (isBlockLava(world, cx, cy, cz)) {
                setBlockToLava(world, cx, cy + 1, cz);
            }
        }
    }

    // The positive y edge
    cy = oy + wcfg.spongeRadius + 1;
    for (cx = ox - wcfg.spongeRadius - 1; cx <= ox + wcfg.spongeRadius + 1; cx++) {
        for (int cz = oz - wcfg.spongeRadius - 1; cz <= oz + wcfg.spongeRadius + 1; cz++) {
            if (isBlockLava(world, cx, cy, cz)) {
                setBlockToLava(world, cx, cy - 1, cz);
            }
        }
    }

    // The negative z edge
    int cz = oz - wcfg.spongeRadius - 1;
    for (cx = ox - wcfg.spongeRadius - 1; cx <= ox + wcfg.spongeRadius + 1; cx++) {
        for (cy = oy - wcfg.spongeRadius - 1; cy <= oy + wcfg.spongeRadius + 1; cy++) {
            if (isBlockLava(world, cx, cy, cz)) {
                setBlockToLava(world, cx, cy, cz + 1);
            }
        }
    }

    // The positive z edge
    cz = oz + wcfg.spongeRadius + 1;
    for (cx = ox - wcfg.spongeRadius - 1; cx <= ox + wcfg.spongeRadius + 1; cx++) {
        for (cy = oy - wcfg.spongeRadius - 1; cy <= oy + wcfg.spongeRadius + 1; cy++) {
            if (isBlockLava(world, cx, cy, cz)) {
                setBlockToLava(world, cx, cy, cz - 1);
            }
        }
    }
}
}

