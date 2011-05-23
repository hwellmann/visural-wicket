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
package com.visural.wicket.component.nicedit;

/**
 * Enumeration of all the different buttons provided as part of the rich text
 * editor user interface. The functionality of each should be fairly obvious.
 * Otherwise consult http://nicedit.com/docs.php.
 * 
 * @version $Id: Button.java 70 2010-02-17 03:42:03Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public enum Button {
    bold,
    italic,
    underline,
    left,
    center,
    right,
    justify,
    ol,
    ul,
    subscript,
    superscript,
    strikethrough,
    removeformat,
    indent,
    outdent,
    hr,
    image,
    upload,
    forecolor,
    bgcolor,
    link,
    unlink,
    fontSize,
    fontFamily,
    fontFormat,
    xhtml;
}
