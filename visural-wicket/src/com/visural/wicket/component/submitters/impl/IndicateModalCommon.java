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
package com.visural.wicket.component.submitters.impl;

import com.jquery.JQueryBGIFrameResourceReference;
import com.visural.javascript.JQueryCenterResourceReference;
import com.visural.wicket.util.PageParamFactory;
import java.io.Serializable;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.link.AbstractLink;

/**
 * @version $Id: IndicateModalCommon.java 232 2010-11-22 09:51:32Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class IndicateModalCommon implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final IndicateModalComponent imComponent;
    private final Component component;

    public IndicateModalCommon(IndicateModalComponent com) {
        this.imComponent = com;
        this.component = (Component) imComponent;
        if (com.autoAddToHeader()) {
            ((Component) com).add(new HeaderContributor(new ModalHeaderContributor()));
            component.add(JavascriptPackageResource.getHeaderContribution(new JQueryCenterResourceReference()));
            component.add(CSSPackageResource.getHeaderContribution(new ModalCSSRef()));
            if (com.isSupportIE6()) {
                component.add(JavascriptPackageResource.getHeaderContribution(new JQueryBGIFrameResourceReference()));
            }
        }
        // preload image
        ((Component) com).add(new HeaderContributor(new IHeaderContributor() {
            public void renderHead(IHeaderResponse arg0) {
                arg0.renderOnDomReadyJavascript("jQuery('<img />').attr('src', '" + component.urlFor(getIndicatorImage()) + "');");
            }
        }));
    }

    private ResourceReference getIndicatorImage() {
        return new LargeAjaxIndicatorRef();
    }

    public String getDefaultIndicatorHTML() {
        return "<div class=\"modalborder\"><br/><img src=\"" + component.urlFor(getIndicatorImage()) + "\"/><br/><br/>Please wait...<br/><br/></div>";
    }

    public String getDefaultTimeoutHTML() {
        return "<div><br/>The request has taken longer than expected to process. An error has most likely occured.<br/><br/><a href=\"" + component.urlFor(component.getApplication().getHomePage(), PageParamFactory.get()) + "\">Click here to return to the home page.</a><br/><br/></div>";
    }

    private boolean comEnabled() {
        if (!component.isEnabled()) {
            return false;
        }
        if (AbstractLink.class.isAssignableFrom(component.getClass()) && !((AbstractLink)component).isEnabledInHierarchy()) {
            return false;
        }
        return true;
    }

    public String getModalDisplayScript() {
        if (!comEnabled()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append("jQuery('#visuraloverlay_high').show();");
        if (imComponent.isSupportIE6()) {
            result.append("jQuery('#visuraloverlay_high').bgiframe();");
        }
        result.append("jQuery('#visuraloverlay_high').height(jQuery(document).height()); jQuery('#visuraloverlaycontent_high').html('")
              .append(imComponent.getIndicatorHTML())
              .append("'); jQuery('#visuraloverlaycontent_high').center(); jQuery('#visuraloverlaycontent_high').show();");
        return result.toString();
    }

    public String getModalCloseScript() {
        StringBuilder result = new StringBuilder();
        result.append("jQuery('#visuraloverlay_high').hide();");
        result.append("jQuery('#visuraloverlaycontent_high').hide();");
        return result.toString();
    }

    public IAjaxCallDecorator getAjaxCallDecorator() {
        return new IAjaxCallDecorator() {

            public CharSequence decorateScript(CharSequence script) {
                return getModalDisplayScript() + script;
            }

            public CharSequence decorateOnSuccessScript(CharSequence script) {
                return getModalCloseScript();
            }

            public CharSequence decorateOnFailureScript(CharSequence script) {
                return getModalCloseScript();
            }
        };
    }
}
