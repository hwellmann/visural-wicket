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

package com.visural.wicket.examples.fancybox;

import com.visural.wicket.aturl.At;
import com.visural.wicket.component.fancybox.Fancybox;
import com.visural.wicket.component.fancybox.FancyboxGroup;
import com.visural.wicket.component.fancybox.TitlePosition;
import com.visural.wicket.examples.BasePage;
import com.visural.wicket.util.images.ImageReferenceFactory;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @version $Id: FancyBoxExamplePage.java 262 2011-03-08 21:09:06Z tibes80@gmail.com $
 * @author Richard Nichols
 */
@At(url="/fancybox")
public class FancyBoxExamplePage extends BasePage {

    public FancyBoxExamplePage() {
        add(new Fancybox("single", ImageReferenceFactory.fromURL(""+urlFor(new ImageRef("image.JPG"), new PageParameters()))).setTitlePosition(TitlePosition.InsideFrame).setBoxTitle("Singapore airport."));
        FancyboxGroup group = FancyboxGroup.get();
        add(new Fancybox("image1", ImageReferenceFactory.fromURL(""+urlFor(new ImageRef("image1.JPG"), new PageParameters()))).setGroup(group).setTitlePosition(TitlePosition.InsideOver).setBoxTitle("Photo 1 of 4"));
        add(new Fancybox("image2", ImageReferenceFactory.fromURL(""+urlFor(new ImageRef("image2.JPG"), new PageParameters()))).setGroup(group).setBoxTitle("Photo 2 of 4"));
        add(new Fancybox("image3", ImageReferenceFactory.fromURL(""+urlFor(new ImageRef("image3.JPG"), new PageParameters()))).setGroup(group).setBoxTitle("Photo 3 of 4"));
        add(new Fancybox("image4", ImageReferenceFactory.fromURL(""+urlFor(new ImageRef("image4.JPG"), new PageParameters()))).setGroup(group).setBoxTitle("Photo 4 of 4"));
        WebMarkupContainer youtubeVid = new WebMarkupContainer("youtubevid");
        youtubeVid.setOutputMarkupId(true);
        add(youtubeVid);
        add(new Fancybox("pagediv", youtubeVid).setWidth(425).setHeight(344));
        add(new Fancybox("iframe", "http://www.google.com/").setWidth(800).setHeight(600));
    }

    @Override
    public String getPageTitle() {
        return "Fancy Box";
    }

}
