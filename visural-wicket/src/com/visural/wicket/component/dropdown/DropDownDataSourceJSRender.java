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
package com.visural.wicket.component.dropdown;

/**
 * @version $Id: DropDownDataSourceJSRender.java 256 2011-02-05 12:06:02Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class DropDownDataSourceJSRender {

    public static String getJS(DropDownDataSource ddds) {
        StringBuffer sb = new StringBuffer();
        sb.append("visural_datasources['" + ddds.getName() + "'] = new VisuralDataSource('" + ddds.getName() + "', [");
        int n = 0;
        for (Object o : ddds.getValues()) {
            if (n > 0) {
                sb.append(",");
            }
            sb.append("new VisuralDataSourceValue('" + n + "', '" + escapeJS(ddds.getDescriptionForValue(o)) + "')");
            n++;
        }
        sb.append("]);");
        return sb.toString();
    }

    private static String escapeJS(String js) {
        if (js == null) {
            return "";
        } else {
            return js.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'").replaceAll("\"", "\\\\\"");
        }
    }
}
