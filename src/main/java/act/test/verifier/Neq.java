package act.test.verifier;

/*-
 * #%L
 * ACT Framework
 * %%
 * Copyright (C) 2018 ActFramework
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

import org.osgl.$;
import org.osgl.util.C;

import java.util.List;

public class Neq extends Verifier {

    @Override
    public boolean verify(Object value) {
        return $.ne(value, initVal);
    }

    @Override
    protected List<String> aliases() {
        return C.list("not-equals", "notEqualsTo", "not-equal", "notEqualTo", "ne");
    }
}
