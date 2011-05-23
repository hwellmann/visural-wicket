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
package com.visural.wicket.message.filter;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;

/**
 * TODO: unfinished code
 *
 * Message filter which does not display messages generated from a
 * reporter within the given component.
 *
 * Typically the current form would be provided as the container, so that
 * field level messages are removed from the top-level feedback panel.
 *
 * @version $Id: NoMessagesFromWithin.java 256 2011-02-05 12:06:02Z tibes80@gmail.com $
 * @author Richard Nichols
 */
public class NoMessagesFromWithin implements IFeedbackMessageFilter {
    private static final long serialVersionUID = 1L;
    private final MarkupContainer container;

    public NoMessagesFromWithin(MarkupContainer container) {
        this.container = container;
    }

    public boolean accept(FeedbackMessage message) {
        return (message.getReporter() == null || !container.contains(message.getReporter(), true));
    }
}
