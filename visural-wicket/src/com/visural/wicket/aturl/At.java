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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is an annotation to automatically mount Wicket Webpage's at a given
 * url in the application.
 *
 * Unlike wicket-stuff annotations, all common coding strategies are supported
 * through a single annotation.
 *
 * See also {@link AtAnnotation} which is used to mount these as part of
 * application `init()`.
 *
 * Mount any typical URL using:
 *
 * `@At(url="/my-url")`
 * 
 *     public class MyPage extends WebPage {
 *        ....
 *     }
 * 
 * To specify parameters that should be encoded after the specified URL, and
 * the order they should appear:
 *
 * `@At(url="/my-url", urlParameters={"param1","param2"})`
 *
 *     public class MyPage extends WebPage {
 *        ....
 *     }
 * 
 * ... would create urls like `/my-url/param1val/param2val?param3=x&param4=y`
 *
 * If you are using "indexed" URL strategy, i.e. `/my-url/Integer0paramval/Integer1paramval/etc..`,
 * you can enable this by specifying - `@At(url="/my-url", type=URLType.Indexed)`
 *
 * If you want to use "Hybrid" style URLs where page state is encoded in the URL,
 * then specify - `@At(url="/my-url", type=URLType.StateInURL)` or,
 * `@At(url="/my-url", type=URLType.IndexedStateInURL)` for Hybrid+Indexed.
 *
 * Generally though, it is not necessary (and simpler) not to bother with types.
 *
 * @version $Id: At.java 109 2010-02-23 01:47:12Z tibes80@gmail.com $
 * @author Richard Nichols
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface At {
    String url();
    URLType type() default URLType.Standard;
    String[] urlParameters() default {};
}