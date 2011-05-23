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

package com.visural.wicket.component.confirmer.impl;

/**
 * @version $Id: ConfirmerIcon.java 256 2011-02-05 12:06:02Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public enum ConfirmerIcon {
    question("help-browser.png"),
    cog("applications-system.png"),
    disk("media-floppy.png"),
    important("emblem-important.png"),
    locked("emblem-readonly.png"),
    surprise("face-surprise.png"),
    error1("dialog-error.png"),
    error2("dialog-warning.png"),
    error3("software-update-urgent.png"),
    error4("process-stop.png"),
    info_bulb("dialog-information.png")
    ;

    private final String file;

    private ConfirmerIcon(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

}
