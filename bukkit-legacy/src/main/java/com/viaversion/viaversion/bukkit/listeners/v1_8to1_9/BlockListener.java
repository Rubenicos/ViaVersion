/*
 * This file is part of ViaVersion - https://github.com/ViaVersion/ViaVersion
 * Copyright (C) 2016-2025 ViaVersion and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.viaversion.viaversion.bukkit.listeners.v1_8to1_9;

import com.viaversion.viaversion.api.minecraft.BlockPosition;
import com.viaversion.viaversion.bukkit.listeners.ViaBukkitListener;
import com.viaversion.viaversion.protocols.v1_8to1_9.Protocol1_8To1_9;
import com.viaversion.viaversion.protocols.v1_8to1_9.storage.EntityTracker1_9;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

public class BlockListener extends ViaBukkitListener {

    public BlockListener(Plugin plugin) {
        super(plugin, Protocol1_8To1_9.class);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void placeBlock(BlockPlaceEvent e) {
        if (isOnPipe(e.getPlayer())) {
            Block b = e.getBlockPlaced();
            EntityTracker1_9 tracker = getUserConnection(e.getPlayer()).getEntityTracker(Protocol1_8To1_9.class);
            tracker.addBlockInteraction(new BlockPosition(b.getX(), b.getY(), b.getZ()));
        }
    }
}
