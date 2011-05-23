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
package com.visural.wicket.util.images;

import java.net.URL;

/**
 * A factory for creating simple {@link ImageReference} implementations.
 * 
 * @version $Id: ImageReferenceFactory.java 256 2011-02-05 12:06:02Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class ImageReferenceFactory {

    public static String toImageTag(ImageReference ref) {
        StringBuffer imageLink = new StringBuffer("<img src=\"");
        imageLink.append(ref.getURL());
        imageLink.append("\"");
        if (ref.getHeightOverride() != null) {
            imageLink.append(" height=\"");
            imageLink.append(ref.getHeightOverride());
            imageLink.append("\"");
        }
        if (ref.getWidthOverride() != null) {
            imageLink.append(" width=\"");
            imageLink.append(ref.getWidthOverride());
            imageLink.append("\"");
        }
        imageLink.append("/>");
        return imageLink.toString();
    }

    public static ImageReference fromURL(URL url) {
        return fromURL(url, null, null);
    }

    public static ImageReference fromURL(URL url, final Integer widthOverride, final Integer heightOverride) {
        return fromURL(url.toString(), widthOverride, heightOverride);
    }

    public static ImageReference fromURL(String url) {
        return fromURL(url, null, null);
    }

    public static ImageReference fromURL(final String url, final Integer widthOverride, final Integer heightOverride) {
        return new ImageReference() {
            public Integer getWidthOverride() {
                return widthOverride;
            }
            public Integer getHeightOverride() {
                return heightOverride;
            }
            public String getURL() {
                return url;
            }
        };
    }
    
}
