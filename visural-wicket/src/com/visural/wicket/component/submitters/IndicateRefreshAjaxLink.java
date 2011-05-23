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
package com.visural.wicket.component.submitters;

import com.visural.wicket.component.submitters.impl.SmallAjaxIndicatorRef;
import com.visural.wicket.security.IPrivilege;
import com.visural.wicket.security.ISecureEnableInstance;
import com.visural.wicket.security.ISecureRenderInstance;
import java.util.Collection;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;

/**
 * An AJAX `Link` which replaces the given elements with an indicator image, while the update occurs. *Requires JQuery*
 *
 * @version $Id: IndicateRefreshAjaxLink.java 232 2010-11-22 09:51:32Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public abstract class IndicateRefreshAjaxLink extends AjaxLink implements ISecureEnableInstance, ISecureRenderInstance {
    private static final long serialVersionUID = 1L;
    // TODO: modularise this with AjaxSubmitLink

    public static final int AJAX_SUBMIT_DELAY_WINDOW = 300;

    public IndicateRefreshAjaxLink(String id) {
        super(id);
        add(new HeaderContributor(new IHeaderContributor() {
            public void renderHead(IHeaderResponse arg0) {
                arg0.renderOnDomReadyJavascript("jQuery('<img />').attr('src', '" + urlFor(getRefreshIndicatorImageReference()) + "');");
            }
        }));
    }

    /**
     * Override and return false to suppress static Javascript and CSS contributions.
     * (May be desired if you are concatenating / compressing resources as part of build process)
     * @return
     */
    public boolean autoAddToHeader() {
        return true;
    }

    /**
     * @return the collection of web markup containers to be replaced with an AJAX
     *         indicator. Note that you must setOutputMarkupId(true) on each of
     *         these elements.
     */
    protected abstract Collection<? extends Component> getIndicateRefreshContainers();

    /**
     * You may override this method to modify the delay threshold before the
     * indicator is displayed.
     *
     * @return the milliseconds to wait before displaying the AJAX indicator.
     */
    protected int getIndicatorDisplayThresholdMillis() {
        return AJAX_SUBMIT_DELAY_WINDOW;
    }

    /**
     * You may override the html that is inserted into the page to something
     * other than a simple AJAX indicator image.
     *
     * This method also would allow you to implement different indicators for
     * different page components should you need to.
     * 
     * @return the html to be replaced for the given container component.
     */
    protected String getIndicatorHTML(Component container) {
        return "<img src=\""+urlFor(getRefreshIndicatorImageReference())+"\"/>";
    }

    /**
     * Override to modify the AJAX indicator image used.
     *
     * @return resource reference to AJAX indicator image.
     */
    protected ResourceReference getRefreshIndicatorImageReference() {
        return new SmallAjaxIndicatorRef();
    }

    private String getAjaxImageReplaceScript() {
        Collection<? extends Component> containers = getIndicateRefreshContainers();
        StringBuffer result = new StringBuffer();
        if (containers != null) {
            // TODO: refactor JS - fix namespacing across all components
            result.append("VISURAL"+this.getMarkupId()+"Complete = false; window.setTimeout(function() { if (!VISURAL"+this.getMarkupId()+"Complete) { ");
            for (Component container : containers) {
                result.append("jQuery('#"+container.getMarkupId()+"').hide();");
                result.append("jQuery('#"+container.getMarkupId()+"').after('<span class=\"visuralajaxind_"+this.getId()+"\">"+getIndicatorHTML(container)+"</span>');");
            }
            result.append("} }, "+getIndicatorDisplayThresholdMillis()+");");
        }
        return result.toString();
    }

    private String getAjaxImageUnreplaceScript() {
        Collection<? extends Component> containers = getIndicateRefreshContainers();
        StringBuffer result = new StringBuffer();
        if (containers != null) {
            // TODO: refactor JS - fix namespacing across all components
            result.append("VISURAL"+IndicateRefreshAjaxLink.this.getMarkupId()+"Complete = true;");
            for (Component container : containers) {
                result.append("jQuery('#"+container.getMarkupId()+"').show();");
                result.append("jQuery('.visuralajaxind_"+this.getId()+"').remove();");
            }
        }
        return result.toString();
    }

    @Override
    protected IAjaxCallDecorator getAjaxCallDecorator() {
        return new IAjaxCallDecorator() {

            public CharSequence decorateScript(CharSequence script) {
                return getAjaxImageReplaceScript()+script;
            }

            public CharSequence decorateOnSuccessScript(CharSequence script) {
                return getAjaxImageUnreplaceScript();
            }

            public CharSequence decorateOnFailureScript(CharSequence script) {
                return getAjaxImageUnreplaceScript();
            }

        };
    }

    public IPrivilege getRenderPrivilege() {
        return IPrivilege.NULL;
    }

    public IPrivilege getEnablePrivilege() {
        return IPrivilege.NULL;
    }


}
