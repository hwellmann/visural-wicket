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

import org.apache.wicket.RequestCycle;

/**
 * Provides a way to generate URL links within the context of the Java webapp.
 * This is to allow easy linking from Wicket to other webapp components, e.g. Servlet.
 * 
 * Adapted from `ExternalLink`.
 *
 * @version $Id: ContextRelativeLink.java 109 2010-02-23 01:47:12Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class ContextRelativeLink {

    /**
     * Provides a way to generate URL links within the context of the Java webapp.
     * This is to allow easy linking from Wicket to other webapp components, e.g. Servlet.
     *
     * @param relativeURL webapp relative url e.g. `/myservelet`
     * @return context adjusted relative URL
     */
    public static String get(String relativeURL) {
        if (relativeURL.length() > 0 && relativeURL.charAt(0) == '/') {
            relativeURL = relativeURL.substring(1);
        }
        String adjustedURL = RequestCycle.get().getProcessor().getRequestCodingStrategy().rewriteStaticRelativeUrl(relativeURL);
        return adjustedURL;
    }
}
