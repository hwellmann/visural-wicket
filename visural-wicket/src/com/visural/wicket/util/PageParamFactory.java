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
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Easy way to create a mapped PageParameters object, by passing any number
 * of pairs to this factory. 
 * 
 * @version $Id: PageParamFactory.java 261 2011-03-08 20:53:16Z tibes80@gmail.com $
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
        PageParameters params = new PageParameters();
        for (int n = 0; n < values.length - 1; n += 2) {
            if (values[n] == null) {
                throw new IllegalArgumentException("key value may not be null");
            }
            params.add(values[n].toString(), Function.nvl(values[n + 1], "").toString());
        }
        return params;
    }
}
