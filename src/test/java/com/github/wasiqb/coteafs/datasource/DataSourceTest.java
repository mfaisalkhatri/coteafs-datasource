/*
 * Copyright (c) 2020 {name of copyright owner}
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.wasiqb.coteafs.datasource;

import static com.google.common.truth.Truth.assertWithMessage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.github.wasiqb.coteafs.datasource.data.Login;
import com.github.wasiqb.coteafs.datasource.data.LoginData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Wasiq Bhamla
 * @since Aug 21, 2020
 */
public class DataSourceTest {
    /**
     * @author Faisal Khatri
     * @since Aug 21, 2020
     * @return test data
     */
    @DataProvider
    public Iterator<Object []> getLoginDataYml () {
        final LoginData loginData = DataSource.parse (LoginData.class);
        final List<Object []> data = new ArrayList<> ();
        loginData.getLoginData ()
            .forEach (d -> data.add (new Object [] { d }));
        return data.iterator ();
    }

    /**
     * @author Wasiq Bhamla
     * @since Aug 21, 2020
     * @param login
     */
    @Test (dataProvider = "getLoginDataYml")
    public void testYmlDataSource (final Login login) {
        assertWithMessage ("User Name").that (login.getUserName ())
            .isNotEmpty ();
        assertWithMessage ("Password").that (login.getPassword ())
            .isNotEmpty ();
    }
}