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

import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.navigation.paging.IPageable;

/**
 * TODO: unfinished code
 *
 * Submitting link that can be used inside a paging navigator for `DataTable`.
 *
 * Most of this copied from wicket source.
 *
 * @version $Id: PagingNavigationSubmitLink.java 110 2010-02-23 02:26:36Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class PagingNavigationSubmitLink extends SubmitLink {

    private static final long serialVersionUID = 1L;
    protected final IPageable pageable;
    private final int pageNumber;

    public PagingNavigationSubmitLink(final String id, final IPageable pageable, final int pageNumber) {
        super(id);
        this.setDefaultFormProcessing(false);
        this.pageNumber = pageNumber;
        this.pageable = pageable;
    }

    @Override
    public void onSubmit() {
        pageable.setCurrentPage(getPageNumber());
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
}
