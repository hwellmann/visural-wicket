/*
 * Copyright 2011 Richard Nichols.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.visural.wicket.security;

import com.visural.common.StringUtil;
import java.io.Serializable;
import junit.framework.TestCase;

/**
 *
 * @author Richard Nichols
 */
public class IPrivilegeTest extends TestCase {

    public void testSyntax() {
        assertTrue(new LoggedOn().isGrantedToClient(new User("fred")));
    }

    public static class User implements IClient {

        private final String username;

        public User(String username) {
            this.username = username;
        }

        public Serializable getId() {
            return username;
        }
    }

    public static class LoggedOn implements IPrivilege<User> {

        public boolean isGrantedToClient(User client) {
            return client != null && StringUtil.isNotBlankStr(client.getId().toString());
        }
    }
}
