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

package com.visural.wicket.examples.dialog;

import com.visural.wicket.aturl.At;
import com.visural.wicket.component.dialog.Dialog;
import com.visural.wicket.examples.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;

/**
 * @version $Id: DropDownExamplePage.java 94 2010-02-20 09:21:18Z tibes80@gmail.com $
 * @author Richard Nichols
 */
@At(url="/dialog")
public class DialogExamplePage extends BasePage {

    public DialogExamplePage() {
        final Dialog dialog = new Dialog("dialog");
        add(dialog);
        dialog.add(new WebMarkupContainer("close").add(dialog.getClickToCloseBehaviour()));
        add(new WebMarkupContainer("open").add(dialog.getClickToOpenBehaviour()));
        add(new AjaxLink("openAjax") {

            @Override
            public void onClick(AjaxRequestTarget target) {
                dialog.open(target);
            }
        });
    }

    @Override
    public String getPageTitle() {
        return "Dialog";
    }
}
