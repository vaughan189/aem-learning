/*
 * Asset Share Commons
 *
 * Copyright (C) 2017 Adobe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.adobe.aem.commons.assetshare.search.results;

import org.osgi.annotation.versioning.ConsumerType;

@ConsumerType
public interface Result {
    /**
     * This is used by the presentation layer to make decisions.
     *
     * In future versions or extensions, this could return values like "folder", allowing the presentation layer to under the result refers to a Folder and not an Asset.
     *
     * @return the type of result.
     */
    String getType();

    /***
     *
     * @return String path to the Asset
     */
    String getPath();

    /***
     *
     * @return String node name of an Asset,
     */
    String getName();
}
