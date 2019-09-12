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

package org.mcnative.service.world.particle;

import org.mcnative.common.player.OnlineMinecraftPlayer;
import org.mcnative.common.player.receiver.ReceiveAble;
import org.mcnative.common.player.receiver.ReceiverChannel;
import org.mcnative.service.location.Location;
import org.mcnative.service.location.Offset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ParticleBuilder implements ReceiveAble {

    private final List<Entry> entries;
    private Collection<OnlineMinecraftPlayer> receivers;
    private Shape shape;

    private ParticleBuilder() {
        this.entries = new ArrayList<>();
    }

    public ParticleBuilder particle(Location location, Particle particle, int amount, Offset offset) {
        entries.add(new Entry(location, particle, amount, offset));
        return this;
    }

    public ParticleBuilder particle(Location location, Particle particle, int amount) {
        return particle(location, particle, amount, null);
    }

    public ParticleBuilder particle(Location location, Particle particle, Offset offset) {
        return particle(location, particle, 1, offset);
    }

    public ParticleBuilder particle(Location location, Particle particle) {
        return particle(location, particle, 1);
    }

    public ParticleBuilder receivers(Iterable<OnlineMinecraftPlayer> receivers) {
        if(this.receivers == null) this.receivers = new ArrayList<>();
        receivers.forEach(receiver -> this.receivers.add(receiver));
        return this;
    }

    public ParticleBuilder shape(Shape shape) {
        this.shape = shape;
        return this;
    }

    public void spawn() {
        if(shape == null) {
            for (Entry entry : this.entries) {
                if(this.receivers == null || this.receivers.isEmpty()) {
                    entry.location.getWorld().spawnParticle(entry.location, entry.particle, entry.amount, entry.offset);
                } else {
                    entry.location.getWorld().spawnParticle(entry.location, entry.particle, entry.amount, entry.offset, this.receivers);
                }
            }
        }
    }

    public static ParticleBuilder create() {
        return new ParticleBuilder();
    }

    @Override
    public void execute(ReceiverChannel receivers) {
        receivers(receivers);
    }

    private static final class Entry {

        final Location location;
        final Particle particle;
        final int amount;
        final Offset offset;

        private Entry(Location location, Particle particle, int amount, Offset offset) {
            this.location = location;
            this.particle = particle;
            this.amount = amount;
            this.offset = offset;
        }
    }
}