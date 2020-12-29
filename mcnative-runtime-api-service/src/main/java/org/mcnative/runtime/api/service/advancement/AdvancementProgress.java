/*
 * (C) Copyright 2019 The McNative Project (Davide Wietlisbach & Philipp Elvin Friedhoff)
 *
 * @author Davide Wietlisbach
 * @since 06.08.19, 19:49
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

package org.mcnative.runtime.api.service.advancement;

import net.pretronic.libraries.utility.Iterators;
import net.pretronic.libraries.utility.Validate;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;
import org.mcnative.runtime.api.protocol.support.JEProtocolSupport;

import java.util.Map;

@JEProtocolSupport(min= MinecraftProtocolVersion.JE_1_12)
public interface AdvancementProgress {

    Map<Criteria, Boolean> getCriteria();

    boolean isDone();

    boolean isCriteriaDone(Criteria criteria);

    boolean isCriteriaDone(String criteria);

    void awardCriteria(Criteria criteria);

    void awardCriteria(String criteria);

    void revokeCriteria(Criteria criteria);

    void revokeCriteria(String criteria);

    class Legacy implements AdvancementProgress {

        private final Map<Criteria, Boolean> criteria;

        public Legacy(Map<Criteria, Boolean> criteria) {
            this.criteria = criteria;
        }

        @Override
        public Map<Criteria, Boolean> getCriteria() {
            return this.criteria;
        }

        @Override
        public boolean isDone() {
            return !this.criteria.containsValue(false);
        }

        @Override
        public boolean isCriteriaDone(Criteria criteria) {
            return this.criteria.get(criteria);
        }

        @Override
        public boolean isCriteriaDone(String criteria) {
            for (Map.Entry<Criteria, Boolean> entry : this.criteria.entrySet()) {
                if(entry.getKey().getName().equalsIgnoreCase(criteria)) return entry.getValue();
            }
            return false;
        }

        @Override
        public void awardCriteria(Criteria criteria) {
            Validate.isTrue(this.criteria.containsKey(criteria), "{} does not contains criteria", criteria.getName());
            this.criteria.put(criteria, true);
        }

        @Override
        public void awardCriteria(String criteria) {
            Criteria foundCriteria = Iterators.findOne(this.criteria.keySet(), searchCriteria -> searchCriteria.getName().equalsIgnoreCase(criteria));
            Validate.notNull(foundCriteria, "{} criteria was not found", criteria);
            this.criteria.put(foundCriteria, true);
        }

        @Override
        public void revokeCriteria(Criteria criteria) {
            Validate.isTrue(this.criteria.containsKey(criteria), "{} does not contains criteria", criteria.getName());
            this.criteria.put(criteria, false);
        }

        @Override
        public void revokeCriteria(String criteria) {
            Criteria foundCriteria = Iterators.findOne(this.criteria.keySet(), searchCriteria -> searchCriteria.getName().equalsIgnoreCase(criteria));
            Validate.notNull(foundCriteria, "{} criteria was not found", criteria);
            this.criteria.put(foundCriteria, false);
        }
    }
}
