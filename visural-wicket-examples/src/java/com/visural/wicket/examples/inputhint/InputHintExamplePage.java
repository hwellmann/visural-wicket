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

package com.visural.wicket.examples.inputhint;

import com.visural.common.DateUtil;
import com.visural.wicket.aturl.At;
import com.visural.wicket.behavior.dateinput.DateInputBehavior;
import com.visural.wicket.behavior.dateinput.DateInputFormat;
import com.visural.wicket.behavior.inputhint.InputHintBehavior;
import com.visural.wicket.behavior.jsr303.JSR303ValidatedForm;
import com.visural.wicket.examples.BasePage;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.convert.IConverter;
import org.hibernate.validator.constraints.Email;

/**
 * @version $Id: DropDownExamplePage.java 94 2010-02-20 09:21:18Z tibes80@gmail.com $
 * @author Richard Nichols
 */
@At(url="/input-hint")
public class InputHintExamplePage extends BasePage {

    public InputHintExamplePage() {
        add(new InputHintForm("jsrForm"));
    }

    @Override
    public String getPageTitle() {
        return "Input Hint Example";
    }

    private class InputHintForm extends JSR303ValidatedForm<ModelBean> {

        private ModelBean model = new ModelBean();

        // Notice how I didn't add any validation?
        // It will get picked up automatically from the model bean
        public InputHintForm(String id) {
            super(id);
            setDefaultModel(new CompoundPropertyModel(model));
            add(new FeedbackPanel("feedback"));
            add(new TextField("name").add(new InputHintBehavior(this, "At least 20 characters", "color: #aaa;")));
            add(new TextField("age").add(new InputHintBehavior(this, "Years ( > 0 )", "color: #aaa;")));
            add(new TextField("dateOfBirth") {
                @Override
                public IConverter getConverter(Class<?> type) {
                    if (Date.class.isAssignableFrom(type)) {
                        return new IConverter() {
                            public Object convertToObject(String value, Locale locale) {
                                return DateUtil.parseDate(value, "yyyy-MM-dd");
                            }
                            public String convertToString(Object value, Locale locale) {
                                return DateUtil.formatDate((Date)value, "yyyy-MM-dd");
                            }
                        };
                    }
                    return super.getConverter(type);
                }
            }.add(new DateInputBehavior().setDateFormat(DateInputFormat.YYYY_MM_DD))
             .add(new InputHintBehavior(this, "Must be in the past", "color: #aaa;")));
            add(new TextField("email").add(new InputHintBehavior(this, "user@domain", "color: #aaa;")));
            add(new SubmitLink("submit"));
        }
    }

    class ModelBean implements Serializable {
        private @NotNull @Size(min=20, max=100) String name;
        private @Min(0) @Max(120) Long age;
        private @Past Date dateOfBirth;
        private @Email @Size(max=200) String email;

        public Long getAge() {
            return age;
        }

        public void setAge(Long age) {
            this.age = age;
        }

        public Date getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
