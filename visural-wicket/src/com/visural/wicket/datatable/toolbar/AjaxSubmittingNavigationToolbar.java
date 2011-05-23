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

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

/**
 * TODO: unfinished code
 *
 * AJAX navigation toolbar for a `DataTable` which submits the form upon changing the page.
 *
 * This is useful where there are form elements inside a paged list.
 *
 * @version $Id: AjaxSubmittingNavigationToolbar.java 110 2010-02-23 02:26:36Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class AjaxSubmittingNavigationToolbar extends NavigationToolbar {

    public AjaxSubmittingNavigationToolbar(DataTable<?> dataTable) {
        super(dataTable);
    }

    @Override
    protected PagingNavigator newPagingNavigator(String navigatorId, DataTable<?> table) {
        return new PagingNavigator(navigatorId, table) {
            @Override
            protected AbstractLink newPagingNavigationLink(String id, IPageable pageable, int pageNumber) {
                return new PagingNavigationAjaxSubmitLink(id, pageable, pageNumber);
            }
            @Override
            protected AbstractLink newPagingNavigationIncrementLink(String id, IPageable pageable, int increment) {
                return new PagingNavigationAjaxSubmitLink(id, pageable, increment);
            }
            @Override
            protected PagingNavigation newNavigation(IPageable pageable, IPagingLabelProvider labelProvider) {
                return new PagingNavigation("navigation", pageable, labelProvider) {
                    @Override
                    protected AbstractLink newPagingNavigationLink(String id, IPageable pageable, int pageIndex) {
                        return new PagingNavigationAjaxSubmitLink(id, pageable, pageIndex);
                    }
                };
            }
        };
    }
    
}
