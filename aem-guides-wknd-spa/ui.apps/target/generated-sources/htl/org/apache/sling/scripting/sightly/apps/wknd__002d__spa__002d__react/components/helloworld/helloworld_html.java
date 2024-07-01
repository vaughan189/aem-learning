/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.wknd__002d__spa__002d__react.components.helloworld;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class helloworld_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _dynamic_properties = bindings.get("properties");
Object _global_model = null;
out.write("\n<div class=\"cmp-helloworld\" data-cmp-is=\"helloworld\">\n    <h2 class=\"cmp-helloworld__title\">Hello World Component</h2>\n    ");
{
    Object var_testvariable0 = renderContext.getObjectModel().resolveProperty(_dynamic_properties, "text");
    if (renderContext.getObjectModel().toBoolean(var_testvariable0)) {
        out.write("<div class=\"cmp-helloworld__item\">\n        <p class=\"cmp-helloworld__item-label\">Text property:</p>\n        <pre class=\"cmp-helloworld__item-output\" data-cmp-hook-helloworld=\"property\">");
        {
            Object var_1 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "text"), "text");
            out.write(renderContext.getObjectModel().toString(var_1));
        }
        out.write("</pre>\n    </div>");
    }
}
out.write("\n    ");
_global_model = renderContext.call("use", com.adobe.aem.guides.wknd.spa.react.core.models.HelloWorldModel.class.getName(), obj());
{
    Object var_testvariable2 = renderContext.getObjectModel().resolveProperty(_global_model, "message");
    if (renderContext.getObjectModel().toBoolean(var_testvariable2)) {
        out.write("<div class=\"cmp-helloworld__item\">\n        <p class=\"cmp-helloworld__item-label\">Model message:</p>\n        <pre class=\"cmp-helloworld__item-output\" data-cmp-hook-helloworld=\"model\">");
        {
            Object var_3 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_model, "message"), "text");
            out.write(renderContext.getObjectModel().toString(var_3));
        }
        out.write("</pre>\n    </div>");
    }
}
out.write("\n</div>\n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

