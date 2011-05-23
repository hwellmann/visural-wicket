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

package com.visural.wicket.behavior.dateinput;

/**
 * Date Input Formats for {@link DateInputBehavior}
 *
 * The jquery.date_input.js uses transform functions to apply date formats.
 * For this reason it's not trivial to just use a traditional date format string.
 *
 * @version $Id: DateInputFormat.java 256 2011-02-05 12:06:02Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public enum DateInputFormat {
    DD_MMM_YYYY("",""),
    MM_DD_YYYY_SLASHES("function(string) { " +
                "   var matches; " +
                "   if (matches = string.match(/^(\\d{2,2})\\/(\\d{2,2})\\/(\\d{4,4})$/)) {"+
                "       return new Date(matches[3], matches[1] - 1, matches[2]);"+
                "   } else {"+
                "       return null;"+
                "   };" +
                "}",
                "function(date) {" +
                "    var month = (date.getMonth() + 1).toString();" +
                "    var dom = date.getDate().toString();" +
                "    if (month.length == 1) month = \"0\" + month;" +
                "    if (dom.length == 1) dom = \"0\" + dom;" +
                "    return month + \"/\" + dom + \"/\" + date.getFullYear();" +
                "  }"),
    DD_MM_YYYY_SLASHES("function(string) { " +
                "   var matches; " +
                "   if (matches = string.match(/^(\\d{2,2})\\/(\\d{2,2})\\/(\\d{4,4})$/)) {"+
                "       return new Date(matches[3], matches[2] - 1, matches[1]);"+
                "   } else {"+
                "       return null;"+
                "   };" +
                "}",
                "function(date) {" +
                "    var month = (date.getMonth() + 1).toString();" +
                "    var dom = date.getDate().toString();" +
                "    if (month.length == 1) month = \"0\" + month;" +
                "    if (dom.length == 1) dom = \"0\" + dom;" +
                "    return dom + \"/\" + month + \"/\" + date.getFullYear();" +
                "  }"),
    DD_MM_YYYY_DOTS("function(string) { " +
                "   var matches; " +
                "   if (matches = string.match(/^(\\d{2,2})\\.(\\d{2,2})\\.(\\d{4,4})$/)) {"+
                "       return new Date(matches[3], matches[2] - 1, matches[1]);"+
                "   } else {"+
                "       return null;"+
                "   };" +
                "}",
                "function(date) {" +
                "    var month = (date.getMonth() + 1).toString();" +
                "    var dom = date.getDate().toString();" +
                "    if (month.length == 1) month = \"0\" + month;" +
                "    if (dom.length == 1) dom = \"0\" + dom;" +
                "    return dom + \".\" + month + \".\" + date.getFullYear();" +
                "  }"),
    MM_DD_YYYY("function(string) { " +
                "   var matches; " +
                "   if (matches = string.match(/^(\\d{2,2})-(\\d{2,2})-(\\d{4,4})$/)) {"+
                "       return new Date(matches[3], matches[1] - 1, matches[2]);"+
                "   } else {"+
                "       return null;"+
                "   };" +
                "}",
                "function(date) {" +
                "    var month = (date.getMonth() + 1).toString();" +
                "    var dom = date.getDate().toString();" +
                "    if (month.length == 1) month = \"0\" + month;" +
                "    if (dom.length == 1) dom = \"0\" + dom;" +
                "    return month + \"-\" + dom + \"-\" + date.getFullYear();" +
                "  }"),
    DD_MM_YYYY("function(string) { " +
                "   var matches; " +
                "   if (matches = string.match(/^(\\d{2,2})-(\\d{2,2})-(\\d{4,4})$/)) {"+
                "       return new Date(matches[3], matches[2] - 1, matches[1]);"+
                "   } else {"+
                "       return null;"+
                "   };" +
                "}",
                "function(date) {" +
                "    var month = (date.getMonth() + 1).toString();" +
                "    var dom = date.getDate().toString();" +
                "    if (month.length == 1) month = \"0\" + month;" +
                "    if (dom.length == 1) dom = \"0\" + dom;" +
                "    return dom + \"-\" + month + \"-\" + date.getFullYear();" +
                "  }"),
    YYYY_MM_DD("function(string) { " +
                "   var matches; " +
                "   if (matches = string.match(/^(\\d{4,4})-(\\d{2,2})-(\\d{2,2})$/)) {"+
                "       return new Date(matches[1], matches[2] - 1, matches[3]);"+
                "   } else {"+
                "       return null;"+
                "   };" +
                "}",
                "function(date) {" +
                "    var month = (date.getMonth() + 1).toString();" +
                "    var dom = date.getDate().toString();" +
                "    if (month.length == 1) month = \"0\" + month;" +
                "    if (dom.length == 1) dom = \"0\" + dom;" +
                "    return date.getFullYear() + \"-\" + month + \"-\" + dom;" +
                "  }");

    private final String strToDate;
    private final String dateToStr;

    private DateInputFormat(String strToDate, String dateToStr) {
        this.strToDate = strToDate;
        this.dateToStr = dateToStr;
    }

    public String getDateToStr() {
        return dateToStr;
    }

    public String getStrToDate() {
        return strToDate;
    }

}
