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
package com.visural.wicket.examples.nicedit;

import com.visural.wicket.aturl.At;
import com.visural.wicket.component.nicedit.Button;
import com.visural.wicket.component.nicedit.RichTextEditor;
import com.visural.wicket.component.nicedit.RichTextEditorFormBehavior;
import com.visural.wicket.component.submitters.IndicateModalSubmitLink;
import com.visural.wicket.examples.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;

/**
 * @version $Id: RichTextEditorExamplePage.java 94 2010-02-20 09:21:18Z tibes80@gmail.com $
 * @author Richard Nichols
 */
@At(url="/rich-text-editor")
public class RichTextEditorExamplePage extends BasePage {

    public RichTextEditorExamplePage() {
        add(new RTEForm("rteForm"));
    }

    @Override
    public String getPageTitle() {
        return "Rich Text Editor";
    }

    class RTEForm extends Form {

        public RTEForm(String id) {
            super(id);

            add(new RichTextEditorFormBehavior());
            final RichTextEditor rte = new RichTextEditor("rte", new Model(""));
            add(rte);
            add(new RichTextEditor("rtered", new Model("")) {
                @Override
                public boolean isButtonEnabled(Button button) {
                    switch (button) {
                        case bold:
                        case italic:
                        case ol:
                        case ul:
                        case underline:
                            return true;
                        default:
                            return false;
                    }
                }
            });

            add(new IndicateModalSubmitLink("submit"));

            add(new Label("rteModel", new AbstractReadOnlyModel<String>() {
                @Override
                public String getObject() {
                    return (String)rte.getModelObject();
                }
            }));
        }

    }
}
