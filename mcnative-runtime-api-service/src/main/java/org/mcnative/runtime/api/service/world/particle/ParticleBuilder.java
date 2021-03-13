/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Philipp Elvin Friedhoff
 * @since 07.09.19, 13:50
 *
 * The McNative Project is under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.mcnative.runtime.api.service.world.particle;

import org.mcnative.runtime.api.player.OnlineMinecraftPlayer;
import org.mcnative.runtime.api.player.receiver.SendAble;
import org.mcnative.runtime.api.player.receiver.LocalReceiverChannel;
import org.mcnative.runtime.api.service.location.Location;
import org.mcnative.runtime.api.service.location.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParticleBuilder implements SendAble {

    private final List<Entry> entries;
    private Shape shape;

    private ParticleBuilder() {
        this.entries = new ArrayList<>();
    }

    public ParticleBuilder particle(Location location, Particle particle, int amount, Vector offset) {
        entries.add(new Entry(location, particle, amount, offset));
        return this;
    }

    public ParticleBuilder particle(Location location, Particle particle, int amount) {
        return particle(location, particle, amount, null);
    }

    public ParticleBuilder particle(Location location, Particle particle, Vector offset) {
        return particle(location, particle, 1, offset);
    }

    public ParticleBuilder particle(Location location, Particle particle) {
        return particle(location, particle, 1);
    }

    public ParticleBuilder shape(Shape shape) {
        this.shape = shape;
        return this;
    }

    public void spawn(Iterable<? extends OnlineMinecraftPlayer> receivers) {
        Objects.requireNonNull(receivers,"No players");
        if(shape == null) {
            for (Entry entry : this.entries) {
                entry.location.getWorld().spawnParticle(entry.location, entry.particle, entry.amount, entry.offset, receivers);
            }
        }
    }

    public static ParticleBuilder create() {
        return new ParticleBuilder();
    }

    @Override
    public void execute(LocalReceiverChannel receivers) {
        spawn(receivers);
    }

    private static final class Entry {

        final Location location;
        final Particle particle;
        final int amount;
        final Vector offset;

        private Entry(Location location, Particle particle, int amount, Vector offset) {
            this.location = location;
            this.particle = particle;
            this.amount = amount;
            this.offset = offset;
        }
    }
}
