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

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

/**
 * This is a pre-made JSR303 annotated model, validated Wicket Form.
 *
 * There is no requirement to use this form as a superclass should you already have
 * custom super-classes. However if you choose not to use it, it at least
 * serves as an example on how to integrate these features.
 *
 * @version $Id$
 * @author Richard Nichols
 */
public class JSR303ValidatedForm<T> extends Form<T> {

    private static final long serialVersionUID = 1L;

    public JSR303ValidatedForm(String id) {
        super(id);
    }

    public JSR303ValidatedForm(final String id, IModel<T> model) {
        super(id, model);
    }
    private JSR303FormVisitor jsr303vis = null;

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        if (jsr303vis == null) {
            jsr303vis = newJSR303FormVisitor();
        }
        visitChildren(jsr303vis);
    }

    protected JSR303FormVisitor newJSR303FormVisitor() {
        return new JSR303FormVisitor();
    }
}
