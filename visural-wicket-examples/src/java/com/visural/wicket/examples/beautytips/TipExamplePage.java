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

package com.visural.wicket.examples.beautytips;

import com.visural.wicket.aturl.At;
import com.visural.wicket.behavior.beautytips.BeautyTipBehavior;
import com.visural.wicket.behavior.beautytips.TipPosition;
import com.visural.wicket.examples.BasePage;
import org.apache.wicket.markup.html.WebMarkupContainer;

/**
 * @version $Id: DropDownExamplePage.java 94 2010-02-20 09:21:18Z tibes80@gmail.com $
 * @author Richard Nichols
 */
@At(url="/tooltips")
public class TipExamplePage extends BasePage {

    public TipExamplePage() {
        add(new WebMarkupContainer("field").add(new BeautyTipBehavior("You can add tooltips to pretty much anything.")));
        add(new WebMarkupContainer("button").add(new BeautyTipBehavior("Tool tips get Wickety.")));
        add(new WebMarkupContainer("shadow")
                .add(new BeautyTipBehavior("You can style the tip how you'd like, including one method call shadowing.").setDropShadow(true)));
        add(new WebMarkupContainer("tipTop").add(new BeautyTipBehavior("Here I Am!").setPositionPreference(TipPosition.top)));
        add(new WebMarkupContainer("tipLeft").add(new BeautyTipBehavior("Here I Am!").setPositionPreference(TipPosition.left)));
        add(new WebMarkupContainer("tipRight").add(new BeautyTipBehavior("Here I Am!").setPositionPreference(TipPosition.right)));
        add(new WebMarkupContainer("tipBottom").add(new BeautyTipBehavior("Here I Am!").setPositionPreference(TipPosition.bottom)));
    }

    @Override
    public String getPageTitle() {
        return "Tool Tips";
    }
}
