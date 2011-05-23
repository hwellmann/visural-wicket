/*
 *  Copyright 2010 Richard Nichols.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package com.visural.wicket.util;

import com.visural.common.Function;
import java.util.HashMap;
import java.util.Map;
import org.apache.wicket.PageParameters;

/**
 * Easy way to create a mapped PageParameters object, by passing any number
 * of pairs to this factory. 
 * 
 * @version $Id: PageParamFactory.java 109 2010-02-23 01:47:12Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class PageParamFactory {

    /**
     * Easy way to create a mapped PageParameters object, by passing any number
     * of pairs to this factory. Note that all items will be .toString()'ed -
     * even the value parameters.
     * 
     * @param values
     * @return
     */
    public static PageParameters get(Object... values) {
        Map<String, String> params = new HashMap<String, String>();
        for (int n = 0; n < values.length - 1; n += 2) {
            if (values[n] == null) {
                throw new IllegalArgumentException("key value may not be null");
            }
            params.put(values[n].toString(), Function.nvl(values[n + 1], "").toString());
        }
        return new PageParameters(params);
    }
}
