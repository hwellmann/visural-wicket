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

import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.markup.html.form.Form;

/**
 * Required to fix a bug in Wicket that prevents a form's onSubmit event handler
 * from firing during AJAX form submissions.
 * @deprecated since Wicket 1.4.11
 * @author Richard Nichols
 */
@Deprecated
public class InvokeClientSideFormSubmitHandlerDecorator implements IAjaxCallDecorator {
    private static final long serialVersionUID = 1L;
    
    private final String formMarkupId;

    public InvokeClientSideFormSubmitHandlerDecorator(Form form) {
        form.setOutputMarkupId(true);
        formMarkupId = form.getMarkupId();
    }

    public CharSequence decorateScript(CharSequence script) {
        // only apply this behavior in wicket version < 1.4.11
        if (WicketVersion.detect().after(new WicketVersion("1.4.10"))) {
            return script;
        }
        return "eval(jQuery('#" + formMarkupId + "').attr('onsubmit'));"+script;
    }

    public CharSequence decorateOnSuccessScript(CharSequence script) {
        return script;
    }

    public CharSequence decorateOnFailureScript(CharSequence script) {
        return script;
    }

}
