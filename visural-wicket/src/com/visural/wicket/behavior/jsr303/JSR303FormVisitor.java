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
package com.visural.wicket.behavior.jsr303;

import java.io.Serializable;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

/**
 * Adds JSR303 property model behavior to a form's components.
 * 
 * @version $Id$
 * @author Richard Nichols
 */
public class JSR303FormVisitor implements IVisitor<Component, Void>, Serializable {

    private static final long serialVersionUID = 1L;

    public void component(Component com, IVisit<Void> ivisit) {
        if (FormComponent.class.isAssignableFrom(com.getClass())) {
            FormComponent fc = (FormComponent) com;
            if (noJSR303Behavior(fc)) {
                fc.add(newJSR303AnnotatedPropertyModelBehavior());
            }
        }
    }

    protected JSR303AnnotatedPropertyModelBehavior newJSR303AnnotatedPropertyModelBehavior() {
        return new JSR303AnnotatedPropertyModelBehavior();
    }

    private boolean noJSR303Behavior(FormComponent fc) {
        List<? extends Behavior> bhvs = fc.getBehaviors();
        for (Behavior b : bhvs) {
            if (JSR303AnnotatedPropertyModelBehavior.class.isAssignableFrom(b.getClass())) {
                return false;
            }
        }
        return true;
    }
}
