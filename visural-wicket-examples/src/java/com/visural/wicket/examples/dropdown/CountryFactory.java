/*
 *  Copyright 2010 Richard.
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
package com.visural.wicket.examples.dropdown;

import com.visural.common.datastruct.datagrid.DataException;
import com.visural.common.datastruct.datagrid.DataGrid;
import com.visural.common.datastruct.datagrid.io.CSVGridGenerator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Richard
 */
public class CountryFactory {

    public static List<Country> getCountries() {
        try {
            CSVGridGenerator cgg = new CSVGridGenerator("Countries", CountryFactory.class.getResourceAsStream("/com/visural/wicket/examples/dropdown/countries.csv"));
            DataGrid dg = new DataGrid(cgg, true);
            List<Country> countries = new ArrayList<Country>();
            for (int n = 0; n < dg.getNumRows(); n++) {
                Country country = new Country();
                country.setCode(dg.getCellAsString(n, 0));
                country.setName(dg.getCellAsString(n, 1));
                countries.add(country);
            }
            return countries;
        } catch (DataException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
