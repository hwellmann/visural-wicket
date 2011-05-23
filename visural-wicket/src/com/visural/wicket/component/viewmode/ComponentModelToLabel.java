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
package com.visural.wicket.component.viewmode;

import java.io.Serializable;

/**
 * A short cut interface for the {@link ViewOrEdit} component which will
 * provide the interface implementation with the model object for the form
 * component. To be converted into a label's html.
 *
 * You can implement your own `IModel` but this is less work and covers
 * 99% of use cases.
 *
 * @version $Id: ComponentModelToLabel.java 109 2010-02-23 01:47:12Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public interface ComponentModelToLabel<T> extends Serializable {

    String convertToLabel(T modelObject);
}
