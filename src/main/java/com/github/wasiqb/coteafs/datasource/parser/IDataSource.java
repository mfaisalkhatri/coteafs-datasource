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
package com.github.wasiqb.coteafs.datasource.parser;

/**
 * Parses data file in Yaml, JSon, XML and properties file format.
 *
 * @author Wasiq Bhamla
 * @since 19-08-2020
 */
public interface IDataSource {
    /**
     * Parses data file.
     *
     * @param path Path to file.
     * @param dataClass Class object for data file to parse to.
     * @param <T> Type of Class.
     *
     * @return List of class objects.
     */
    <T> T parse (String path, Class<T> dataClass);
}