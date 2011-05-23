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
package com.visural.wicket.model;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * TODO: unfinished code
 *
 * An implementation of `LoadableDetachableModel` designed to store a java bean in-session, i.e. not detachable.
 *
 * @version $Id: RetainedBeanModel.java 256 2011-02-05 12:06:02Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class RetainedBeanModel<T> extends LoadableDetachableModel<T> {

    private T bean;

    public RetainedBeanModel(T bean) {
        this.bean = bean;
    }

    @Override
    protected T load() {
        return bean;
    }
}
