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

package com.visural.wicket.examples.dateinput;

import com.visural.wicket.aturl.At;
import com.visural.wicket.behavior.dateinput.DateInputBehavior;
import com.visural.wicket.behavior.dateinput.DateInputFormat;
import com.visural.wicket.component.dropdown.DropDown;
import com.visural.wicket.component.dropdown.DropDownDataSource;
import com.visural.wicket.component.submitters.IndicateModalSubmitLink;
import com.visural.wicket.examples.BasePage;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;

/**
 * @version $Id: DropDownExamplePage.java 94 2010-02-20 09:21:18Z tibes80@gmail.com $
 * @author Richard Nichols
 */
@At(url="/dateinput")
public class DateInputExamplePage extends BasePage {

    public DateInputExamplePage() {    
        add(new DateInputForm("dropDownForm"));
    }

    @Override
    public String getPageTitle() {
        return "Date Input";
    }

    class DateInputForm extends Form {

        Model basicModel = new Model(null);
        Model customModel = new Model(null);

        public DateInputForm(String id) {
            super(id);
            add(new TextField("basic", basicModel).add(new DateInputBehavior()));
            add(new TextField("custom", customModel).add(new DateInputBehavior().setDateFormat(DateInputFormat.YYYY_MM_DD)));
            addModelLabels();
            add(new IndicateModalSubmitLink("submit"));
        }

        private void addModelLabels() {
            add(new Label("basicVal", new AbstractReadOnlyModel() {
                @Override
                public Object getObject() {
                    if (basicModel.getObject() == null) {
                        return "null";
                    } else {
                        return basicModel.getObject().toString();
                    }
                }
            }));
            add(new Label("customVal", new AbstractReadOnlyModel() {
                @Override
                public Object getObject() {
                    if (customModel.getObject() == null) {
                        return "null";
                    } else {
                        return customModel.getObject().toString();
                    }
                }
            }));
        }

    }
}
