/*
 *  Copyright 2009 Richard Nichols.
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

package com.google.prettify;

import com.visural.wicket.component.codebox.CodeBoxLanguage;
import org.apache.wicket.ResourceReference;

/**
 * @version $Id: PrettifyJSResourceReference.java 17 2009-11-15 04:56:30Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class ExtraJSResourceReference extends ResourceReference {
    private static final long serialVersionUID = 1L;
    public ExtraJSResourceReference(CodeBoxLanguage lang) {
        super(ExtraJSResourceReference.class, lang.getExtraJSfile());
    }
}
