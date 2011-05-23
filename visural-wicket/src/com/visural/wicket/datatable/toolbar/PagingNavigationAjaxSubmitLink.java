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
package com.visural.wicket.datatable.toolbar;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.navigation.paging.IPageable;

/**
 * TODO: unfinished code
 *
 * Ajax submitting link that can be used inside a paging navigator for `DataTable`.
 *
 * Most of this copied from wicket source.
 *
 * @version $Id: PagingNavigationAjaxSubmitLink.java 110 2010-02-23 02:26:36Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class PagingNavigationAjaxSubmitLink extends AjaxSubmitLink {

    private static final long serialVersionUID = 1L;
    protected final IPageable pageable;
    private final int pageNumber;

    public PagingNavigationAjaxSubmitLink(final String id, final IPageable pageable, final int pageNumber) {
        super(id);
        this.setDefaultFormProcessing(false);
        this.pageNumber = pageNumber;
        this.pageable = pageable;
    }

    public final int getPageNumber() {
        return cullPageNumber(pageNumber);
    }

    protected int cullPageNumber(int pageNumber) {
        int idx = pageNumber;
        if (idx < 0) {
            idx = pageable.getPageCount() + idx;
        }

        if (idx > (pageable.getPageCount() - 1)) {
            idx = pageable.getPageCount() - 1;
        }

        if (idx < 0) {
            idx = 0;
        }

        return idx;
    }

    public final boolean isFirst() {
        return getPageNumber() == 0;
    }

    public final boolean isLast() {
        return getPageNumber() == (pageable.getPageCount() - 1);
    }

    @Override
    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
        pageable.setCurrentPage(getPageNumber());
        if (pageable instanceof Component) {
            target.addComponent((Component) pageable);
        }
    }
}
