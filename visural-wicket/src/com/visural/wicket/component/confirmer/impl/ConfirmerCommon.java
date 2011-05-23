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
package com.visural.wicket.component.confirmer.impl;

import com.jquery.JQueryBGIFrameResourceReference;
import com.visural.javascript.JQueryCenterResourceReference;
import com.visural.wicket.component.submitters.impl.ModalCSSRef;
import com.visural.wicket.component.submitters.impl.ModalHeaderContributor;
import java.io.Serializable;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.link.AbstractLink;

/**
 * @version $Id: ConfirmerCommon.java 258 2011-02-24 07:11:45Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class ConfirmerCommon implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ConfirmerComponent imComponent;
    private final Component component;

    private ConfirmerIcon icon = ConfirmerIcon.question;
    private String messageContentHTML = "<center><p>Are you sure?</p></center>";
    private String okButtonLabel = "Yes";
    private String cancelButtonLabel = "No";
    private boolean displayOK = true;
    private boolean displayCancel = true;

    private String onClickJS = "";
    private Integer modalWidth = null;
   
    public ConfirmerCommon(ConfirmerComponent com) {
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
                .append(getContentHTML().replace("'", "\\'"))
                .append("'); jQuery('#visuraloverlaycontent_high').center(); jQuery('#visuraloverlaycontent_high').show();");
        return result.toString().replace("\"", "\\x22");
    }

    public String getContentHTML() {
        StringBuilder html = new StringBuilder("<div "+(modalWidth != null ? "style='width: "+modalWidth+"px;' " : "")+"class=\"modalborder\"><table width=\"100%\"><tr><td width=\"40px\">");
        if (imComponent.getIcon() != null) {
            html.append("<img src=\"").append(component.urlFor(new ConfirmerIconRef(icon))).append("\" style=\"float: left;\"/>");
        }
        html.append("</td><td>").append(imComponent.getMessageContentHTML()).append("</td></tr></table>");
        html.append("<p style=\"text-align: center;\">");
        if (imComponent.isDisplayOK()) {
            html.append(imComponent.getLinkHTML(imComponent.getOkButtonLabel(), getModalCloseScript()+onClickJS));
        }
        if (imComponent.isDisplayOK() && imComponent.isDisplayCancel()) {
            html.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        }
        if (imComponent.isDisplayCancel()) {
            html.append(imComponent.getLinkHTML(imComponent.getCancelButtonLabel(), getModalCloseScript()));
        }
        html.append("</p></div>");
        return html.toString();
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
                setOnClickJS(""+script);
                return getModalDisplayScript();
            }

            public CharSequence decorateOnSuccessScript(CharSequence script) {
                return getModalCloseScript();
            }

            public CharSequence decorateOnFailureScript(CharSequence script) {
                return getModalCloseScript();
            }
        };
    }

    public String getLinkHTML(String buttonLabel, String onClickJS) {
        return "<a href=\"#\" onclick=\""+onClickJS+";return false;\">"+buttonLabel+"</a>";
    }

    public String getCancelButtonLabel() {
        return cancelButtonLabel;
    }

    public void setCancelButtonLabel(String cancelButtonLabel) {
        this.cancelButtonLabel = cancelButtonLabel;
    }

    public boolean isDisplayCancel() {
        return displayCancel;
    }

    public void setDisplayCancel(boolean displayCancel) {
        this.displayCancel = displayCancel;
    }

    public boolean isDisplayOK() {
        return displayOK;
    }

    public void setDisplayOK(boolean displayOK) {
        this.displayOK = displayOK;
    }

    public ConfirmerIcon getIcon() {
        return icon;
    }

    public void setIcon(ConfirmerIcon icon) {
        this.icon = icon;
    }

    public String getOkButtonLabel() {
        return okButtonLabel;
    }

    public void setOkButtonLabel(String okButtonLabel) {
        this.okButtonLabel = okButtonLabel;
    }

    public String getMessageContentHTML() {
        return messageContentHTML;
    }

    public void setMessageContentHTML(String messageContentHTML) {
        this.messageContentHTML = messageContentHTML;
    }

    public String getOnClickJS() {
        return onClickJS;
    }

    public void setOnClickJS(String onClickJS) {
        this.onClickJS = onClickJS;
    }

    public Integer getModalWidth() {
        return modalWidth;
    }

    public void setModalWidth(Integer modalWidth) {
        this.modalWidth = modalWidth;
    }

}
