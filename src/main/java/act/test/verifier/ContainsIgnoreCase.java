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
import org.osgl.util.S;

import java.lang.reflect.Array;
import java.util.Collection;

public class ContainsIgnoreCase extends Verifier {

    boolean isString;
    String lowerCase;

    @Override
    public void init(Object param) {
        super.init(param);
        isString = String.class.isInstance(param);
        if (isString) {
            lowerCase = S.string(param).toLowerCase();
        }
    }

    @Override
    public boolean verify(Object value) {
        if (null == value) {
            return initVal == null;
        }
        if (value instanceof String) {
            return S.string(value).toLowerCase().contains(lowerCase);
        } else if (value instanceof Collection) {
            if (isString) {
                for (Object o : (Collection) value) {
                    if (S.string(o).equalsIgnoreCase(lowerCase)) {
                        return true;
                    }
                }
                return false;
            }
            return ((Collection) value).contains(initVal);
        } else if (value.getClass().isArray()) {
            int len = Array.getLength(value);
            for (int i = 0; i < len; ++i) {
                Object component = Array.get(value, i);
                if (isString) {
                    if (S.string(component).equalsIgnoreCase(lowerCase)) {
                        return true;
                    }
                } else if ($.eq(component, initVal)) {
                    return true;
                }
            }
        }
        return false;
    }
}
