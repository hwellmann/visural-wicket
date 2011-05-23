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
package com.jquery;

import org.apache.wicket.ResourceReference;

/**
 * @version $Id: JQueryResourceReference.java 217 2010-09-30 23:57:10Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class JQueryResourceReference extends ResourceReference {
    private static final long serialVersionUID = 1L;
    public enum Version {

        V1_3_2("jquery-1.3.2.min.js"),
        V1_4_2("jquery-1.4.2.min.js");
        private final String filename;

        private Version(String filename) {
            this.filename = filename;
        }

        public String getFilename() {
            return filename;
        }
    }

    public JQueryResourceReference() {
        this(Version.V1_3_2);
    }

    public JQueryResourceReference(Version version) {
        super(JQueryResourceReference.class, version.getFilename());
    }
}
