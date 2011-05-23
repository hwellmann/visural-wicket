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
package com.visural.wicket.aturl;

import com.visural.common.ClassFinder;
import java.util.Set;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.target.coding.HybridUrlCodingStrategy;
import org.apache.wicket.request.target.coding.IndexedHybridUrlCodingStrategy;
import org.apache.wicket.request.target.coding.IndexedParamUrlCodingStrategy;
import org.apache.wicket.request.target.coding.MixedParamUrlCodingStrategy;

/**
 * Used to mount {@link At} annotated WebPages at given urls, using either the
 * default coding strategy, or a specified type.
 *
 * Usage:
 *
 *     public class MyApp extends WebApplication {
 *         protected void init() {
 *             try {
 *                 AtAnnotation.mount(this, "com.mycom.myapp");
 *             } catch (ClassNotFoundException ex) {
 *                 throw new IllegalStateException("Failed mounting URLs.", ex);
 *             }
 *         }
 *         ...
 *     }
 * 
 * @version $Id: AtAnnotation.java 109 2010-02-23 01:47:12Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class AtAnnotation {

    /**
     * Search the class path for `@At` annotated classes to mount against a WebApplication.
     *
     * @param app WebApplication to mount against
     * @param packageBase base package to scan from, e.g. "com.mycompany.appname"
     * @throws ClassNotFoundException if a Class path scanning error occurs
     */
    public static void mount(WebApplication app, String packageBase) throws ClassNotFoundException {
        Set<Class> pages = findClasses(packageBase);
        for (Class page : pages) {
            mountPage(app, page);
        }
    }

    private static Set<Class> findClasses(String packageBase) throws ClassNotFoundException {
        ClassFinder finder = new ClassFinder(packageBase, true);
        finder.addClassAnnotationFilter(At.class);
        finder.addSuperClassFilter(WebPage.class);
        return finder.find();
    }

    private static void mountPage(WebApplication app, Class page) {
        At at = (At) page.getAnnotation(At.class);
        switch (at.type()) {
            case Standard:
                if (at.urlParameters().length == 0) {
                    app.mountBookmarkablePage(at.url(), page);
                } else {
                    app.mount(new MixedParamUrlCodingStrategy(at.url(), page, at.urlParameters()));
                }
                break;
            case Indexed:
                app.mount(new IndexedParamUrlCodingStrategy(at.url(), page));
                break;
            case StateInURL:
                app.mount(new HybridUrlCodingStrategy(at.url(), page));
                break;
            case IndexedStateInURL:
                app.mount(new IndexedHybridUrlCodingStrategy(at.url(), page));
                break;
        }

    }
}
