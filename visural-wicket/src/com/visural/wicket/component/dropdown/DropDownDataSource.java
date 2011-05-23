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

import java.io.Serializable;
import java.util.List;

/**
 * Interface for {@link DropDown} control's data source.
 *
 * Abstracted so that multiple drop downs can share the same data source.
 *
 * Similar to the wicket `DropDownChoice` control, the `DropDownDataSource` operates
 * on an object model as the selected choice. i.e. the value of the model backing
 * the dropdown is the selected object instance from the list of values in the
 * data source.
 *
 * @version $Id: DropDownDataSource.java 109 2010-02-23 01:47:12Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public interface DropDownDataSource<T> extends Serializable {

    /**
     * @return the text name of the data source e.g. "names"
     */
    String getName();

    /**
     * Provide the list of rows for the data source.
     *
     * The result type can be any object, e.g. Java Bean, domain object, etc.
     *
     * **The list must be immutable within the drop down component's context** 
     * i.e. the list should not mutate over time.
     *
     * @return list of rows in the data source.
     */
    List<T> getValues();

    /**
     * An implementation which generates a description for the given
     * row object.
     * 
     * @param value row object
     * @return text description of object to display in drop down
     */
    String getDescriptionForValue(T value);
}
