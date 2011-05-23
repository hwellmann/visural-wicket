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

package com.visural.wicket.examples.dropdown;

import com.visural.wicket.aturl.At;
import com.visural.wicket.component.dropdown.DropDown;
import com.visural.wicket.component.dropdown.DropDownDataSource;
import com.visural.wicket.component.submitters.IndicateModalSubmitLink;
import com.visural.wicket.examples.BasePage;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;

/**
 * @version $Id: DropDownExamplePage.java 225 2010-11-22 05:35:47Z tibes80@gmail.com $
 * @author Richard Nichols
 */
@At(url="/dropdown")
public class DropDownExamplePage extends BasePage {

    private List<Country> countries = CountryFactory.getCountries();
    private DropDownDataSource countriesDS;

    public DropDownExamplePage() {
        countriesDS = new DropDownDataSource<Country>() {
            public String getName() {
                return "countries";
            }
            public List<Country> getValues() {
                return countries;
            }

            public String getDescriptionForValue(Country t) {
                return t.getName()+" ("+t.getCode().toUpperCase()+")";
            }
        };
        add(new DropDownForm("dropDownForm"));
    }

    @Override
    public String getPageTitle() {
        return "Drop Down (Combobox)";
    }

    class DropDownForm extends Form {

        Model basicModel = new Model(null);
        DropDown basic = new DropDown("basic", basicModel, countriesDS, true);
        DropDown freetext = new DropDown("freetext", new Model(null), countriesDS, false);

        public DropDownForm(String id) {
            super(id);
            add(basic.setCharacterWidth(50));
            add(freetext.setCharacterWidth(50).setEnableFilterToggle(false).setShowArrowIcon(false));
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
            add(new Label("freetextVal", new AbstractReadOnlyModel() {
                @Override
                public Object getObject() {
                    if (freetext.getModelObject() == null) {
                        return "null";
                    } else {
                        return freetext.getModelObject().toString();
                    }
                }
            }));
        }

    }
}
