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

package com.adobe.aem.commons.assetshare.configuration;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface AssetDetails {
    /**
     * @return the url to the asset details page, but do NOT include the asset reference in the suffix.
     */
    String getUrl();

    /**
     * @return the full asset details link including the asset reference as the suffix.
     */
    default String getFullUrl() { return null; }
}

