package act.test.verifier;

/*-
 * #%L
 * ACT Framework
 * %%
 * Copyright (C) 2014 - 2018 ActFramework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import act.util.NoAutoRegister;
import org.osgl.$;

@NoAutoRegister
public class ReversedVerifier extends Verifier {
    private Verifier v;

    public ReversedVerifier(Verifier v) {
        this.v = $.requireNotNull(v);
    }

    @Override
    public boolean verify(Object value) {
        return !v.verify(value);
    }

    @Override
    public String toString() {
        return "!" + v.toString();
    }
}
