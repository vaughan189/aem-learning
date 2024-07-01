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

package com.adobe.aem.commons.assetshare.components.predicates.impl.options;

import com.adobe.cq.wcm.core.components.models.form.OptionItem;

public class UnselectedOptionItem implements OptionItem {

    private OptionItem wrappedOptionItem = null;

    public UnselectedOptionItem(OptionItem wrappedOptionItem) {
        this.wrappedOptionItem = wrappedOptionItem;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isDisabled() {
        return wrappedOptionItem.isDisabled();
    }

    @Override
    public String getValue() {
        return wrappedOptionItem.getValue();
    }

    @Override
    public String getText() {
        return wrappedOptionItem.getText();
    }
}
