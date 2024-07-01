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
package org.apache.sling.scripting.sightly.apps.wknd__002d__spa__002d__react.components.page;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class customfooterlibs_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_clientlib = null;
Object _dynamic_wcmmode = bindings.get("wcmmode");
out.write("\n");
_global_clientlib = renderContext.call("use", "/libs/granite/sightly/templates/clientlib.html", obj());
out.write("\n    ");
{
    Object var_templatevar0 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "js");
    {
        String var_templateoptions1_field$_categories = "wknd-spa-react.base";
        {
            java.util.Map var_templateoptions1 = obj().with("categories", var_templateoptions1_field$_categories);
            callUnit(out, renderContext, var_templatevar0, var_templateoptions1);
        }
    }
}
out.write("\n\n\n");
{
    Object var_testvariable2 = ((renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(_dynamic_wcmmode, "edit")) ? renderContext.getObjectModel().resolveProperty(_dynamic_wcmmode, "edit") : renderContext.getObjectModel().resolveProperty(_dynamic_wcmmode, "preview")));
    if (renderContext.getObjectModel().toBoolean(var_testvariable2)) {
        {
            Object var_templatevar3 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "js");
            {
                String var_templateoptions4_field$_categories = "cq.authoring.pagemodel.messaging";
                {
                    java.util.Map var_templateoptions4 = obj().with("categories", var_templateoptions4_field$_categories);
                    callUnit(out, renderContext, var_templatevar3, var_templateoptions4);
                }
            }
        }
    }
}
out.write("\n\n\n");
{
    Object var_testvariable5 = renderContext.getObjectModel().resolveProperty(_dynamic_wcmmode, "edit");
    if (renderContext.getObjectModel().toBoolean(var_testvariable5)) {
        {
            Object var_templatevar6 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "css");
            {
                String var_templateoptions7_field$_categories = "wknd-spa-react.author";
                {
                    java.util.Map var_templateoptions7 = obj().with("categories", var_templateoptions7_field$_categories);
                    callUnit(out, renderContext, var_templatevar6, var_templateoptions7);
                }
            }
        }
    }
}
out.write("\n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

