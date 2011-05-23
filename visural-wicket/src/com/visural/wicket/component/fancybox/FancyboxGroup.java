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
package com.visural.wicket.component.fancybox;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Used to identify a group of {@link Fancybox}s.
 *
 * The group name is arbitrary and just needs to identify the group.
 *
 * Use the supplied factory `get()` method to generate a new group.
 *
 * @version $Id: FancyboxGroup.java 217 2010-09-30 23:57:10Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class FancyboxGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final String name;
    private Fancybox main = null;
    private final Set<Fancybox> boxes = new HashSet<Fancybox>();

    public FancyboxGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected void addFancybox(Fancybox fb) {
        if (main == null) {
            main = fb;
        }
        boxes.add(fb);
    }

    protected void removeFancybox(Fancybox fb) {
        if (main == fb) {
            main = (boxes.size() > 0 ? boxes.iterator().next() : null);
        }
        boxes.remove(fb);
    }

    protected String getGroupSelector() {
        if (boxes.size() == 0) {
            return "";
        }
        Iterator<Fancybox> i = boxes.iterator();
        StringBuffer selector = new StringBuffer("#"+i.next().getMarkupId());
        while (i.hasNext()) {
            selector.append(", #");
            selector.append(i.next().getMarkupId());
        }
        return selector.toString();
    }

    protected Fancybox getMain() {
        return main;
    }

    // --- factory for groups - it's really irrevlant what they're actually called

    private static int groupIdx = 0;

    public synchronized static FancyboxGroup get() {
        return new FancyboxGroup("fbgrp_" + groupIdx++);
    }
}
