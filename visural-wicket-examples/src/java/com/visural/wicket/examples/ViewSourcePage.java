/*
 *  Copyright 2010 Richard.
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
package com.visural.wicket.examples;

import com.visural.common.IOUtil;
import com.visural.wicket.component.codebox.CodeBox;
import java.io.IOException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author Richard
 */
public class ViewSourcePage extends WebPage {

    public static final String PARAM_RESOURCE = "resource";

    public ViewSourcePage(PageParameters param) {
        super(param);
        try {
            String resource = param.get(PARAM_RESOURCE).toString();
            String source = IOUtil.urlToString(this.getClass().getResource(resource));
            add(new CodeBox("codebox", source));
        } catch (IOException ex) {
            throw new IllegalStateException("Error occured reading source.");
        }

    }
}
