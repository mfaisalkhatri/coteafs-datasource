/*
 * Copyright (c) 2020 Wasiq Bhamla
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

import static com.github.wasiqb.coteafs.error.util.ErrorUtil.fail;
import static java.text.MessageFormat.format;

import com.github.wasiqb.coteafs.datasource.parser.IDataSource;
import com.github.wasiqb.coteafs.datasource.parser.JsonDataSource;
import com.github.wasiqb.coteafs.datasource.parser.PropertiesDataSource;
import com.github.wasiqb.coteafs.datasource.parser.XmlDataSource;
import com.github.wasiqb.coteafs.datasource.parser.YamlDataSource;
import com.github.wasiqb.coteafs.datasource.utils.DataFileUtil;
import com.github.wasiqb.coteafs.error.OperationNotSupportedError;

/**
 * Helper class to parse data file.
 *
 * @author Wasiq Bhamla
 * @since 20-08-2020
 */
public class DataSource {
    /**
     * Parses the data file according to their file format.
     *
     * @param dataClass Data file class.
     * @param <T> Class type.
     *
     * @return Data class object.
     */
    public static <T> T parse (final Class<T> dataClass) {
        final DataFileUtil<T> dataFile = DataFileUtil.getInstance (dataClass);
        final String fileName = dataFile.getFileName ();
        final String extension = fileName.substring (fileName.lastIndexOf ('.') + 1);
        IDataSource dataSource = null;
        switch (extension.toLowerCase ()) {
            case "yaml":
            case "yml":
                dataSource = new YamlDataSource ();
                break;
            case "json":
                dataSource = new JsonDataSource ();
                break;
            case "properties":
                dataSource = new PropertiesDataSource ();
                break;
            case "xml":
                dataSource = new XmlDataSource ();
                break;
            default:
                fail (OperationNotSupportedError.class,
                    format ("This data file format [{0}] is not supported.", extension));
        }
        return dataSource.parse (dataFile.getPath (), dataClass);
    }

    private DataSource () {
        // Utility class.
    }
}