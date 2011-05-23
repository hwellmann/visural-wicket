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
package com.visural.wicket.examples.codebox;

import com.visural.common.IOUtil;
import com.visural.wicket.aturl.At;
import com.visural.wicket.component.codebox.CodeBox;
import com.visural.wicket.examples.BasePage;
import java.io.IOException;

/**
 * @version $Id: CodeboxExamplePage.java 165 2010-06-17 01:17:33Z tibes80@gmail.com $
 * @author Richard Nichols
 */
@At(url="/code-box")
public class CodeboxExamplePage extends BasePage {

    public CodeboxExamplePage() {
        try {
            add(new CodeBox("langsrc", getLangSourceCode()).setDisplayLineNumbers(true));
            add(new CodeBox("csssrc", getCSSSourceCode()).setDisplayLineNumbers(true));
        } catch (IOException ex) {
            throw new IllegalStateException("Could not read source code example.", ex);
        }
    }

    private String getLangSourceCode() throws IOException {
        return IOUtil.urlToString(CodeboxExamplePage.class.getResource("/com/visural/wicket/examples/codebox/source.txt"));
    }

    private String getCSSSourceCode() throws IOException {
        return IOUtil.urlToString(CodeboxExamplePage.class.getResource("/com/visural/wicket/examples/style.less"));
    }

    @Override
    public String getPageTitle() {
        return "Code Box";
    }
}
