/*
 * (C) Copyright 2020 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 14.02.20, 22:49
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

package org.mcnative.bukkit.player.connection;

import io.netty.channel.ChannelFuture;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NetworkManagerWrapperList implements List {

    private final BukkitChannelInjector channelInitializer;
    private final List future;

    public NetworkManagerWrapperList(BukkitChannelInjector channelInitializer, List future) {
        this.channelInitializer = channelInitializer;
        this.future = future;
    }

    @Override
    public int size() {
        synchronized (this) {
            return future.size();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (this) {
            return future.isEmpty();
        }
    }

    @Override
    public boolean contains(Object o) {
        synchronized (this) {
            return future.contains(o);
        }
    }

    @Override
    public Iterator<ChannelFuture> iterator() {
        synchronized (this) {
            return future.iterator();
        }
    }

    @Override
    public Object[] toArray() {
        synchronized (this) {
            return future.toArray();
        }
    }

    @Override
    public boolean add(Object o) {
        synchronized (this) {
            System.out.println("New Manager ------------>"+o);
            return future.add(o);
        }
    }

    @Override
    public Object[] toArray(Object[] a) {
        synchronized (this) {
            return this.future.toArray(a);
        }
    }


    @Override
    public boolean remove(Object o) {
        synchronized (this) {
            return this.future.remove(o);
        }
    }

    @Override
    public boolean containsAll(Collection c) {
        synchronized (this) {
            return  this.future.containsAll(c);
        }
    }

    @Override
    public boolean addAll(Collection c) {
        synchronized (this) {
            return this.future.addAll(c);
        }
    }

    @Override
    public boolean addAll(int index, Collection c) {
        synchronized (this) {
            return this.future.addAll(index,c);
        }
    }

    @Override
    public boolean removeAll(Collection c) {
        synchronized (this) {
            return this.future.retainAll(c);
        }
    }

    @Override
    public boolean retainAll(Collection c) {
        synchronized (this) {
            return this.future.retainAll(c);
        }
    }

    @Override
    public void clear() {
        synchronized (this) {
            this.future.clear();
        }
    }

    @Override
    public Object get(int index) {
        synchronized (this) {
            return this.get(index);
        }
    }

    @Override
    public Object set(int index, Object element) {
        synchronized (this) {
            return this.future.set(index, element);
        }
    }

    @Override
    public void add(int index, Object element) {
        synchronized (this) {
            this.future.add(index,element);
        }
    }

    @Override
    public Object remove(int index) {
        synchronized (this) {
            return this.remove(index);
        }
    }

    @Override
    public int indexOf(Object o) {
        synchronized (this) {
            return this.future.indexOf(o);
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        synchronized (this) {
            return this.future.lastIndexOf(o);
        }
    }

    @Override
    public ListIterator<ChannelFuture> listIterator() {
        synchronized (this) {
            return this.future.listIterator();
        }
    }

    @Override
    public ListIterator<ChannelFuture> listIterator(int index) {
        synchronized (this) {
            return this.future.listIterator();
        }
    }

    @Override
    public List<ChannelFuture> subList(int fromIndex, int toIndex) {
        synchronized (this) {
            return this.future.subList(fromIndex, toIndex);
        }
    }
}
