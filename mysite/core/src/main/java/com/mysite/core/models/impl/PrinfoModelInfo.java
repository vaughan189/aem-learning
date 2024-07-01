package com.mysite.core.models.impl;
 
import java.util.List;
 
 
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
 
import javax.annotation.PostConstruct;
 
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
 
import com.mysite.core.models.PrinfoModel;
import com.mysite.core.models.ProductRetriever;
 
import com.adobe.cq.commerce.core.components.client.MagentoGraphqlClient;
import com.adobe.cq.commerce.core.components.models.common.CombinedSku;
import com.adobe.cq.commerce.core.components.models.productteaser.ProductTeaser;
import com.adobe.cq.commerce.core.components.models.retriever.AbstractProductRetriever;
import com.adobe.cq.commerce.magento.graphql.ConfigurableProduct;
import com.adobe.cq.commerce.magento.graphql.ConfigurableVariant;
import com.adobe.cq.commerce.magento.graphql.ProductInterface;
import com.adobe.cq.commerce.magento.graphql.SimpleProduct;
 
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.via.ResourceSuperType;
 
@Model(
    adaptables = SlingHttpServletRequest.class,
    adapters = { PrinfoModel.class, ComponentExporter.class },
    resourceType = PrinfoModelInfo.RESOURCE_TYPE,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
    )
@Exporter( //Exporter annotation that serializes the modoel as JSON
    name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
    extensions = ExporterConstants.SLING_MODEL_EXTENSION
    )
public class PrinfoModelInfo implements PrinfoModel {
 
    protected static final String RESOURCE_TYPE = "mysite/components/prinfo";
    private static final String SELECTION_PROPERTY = "selection";
 
    @Self(injectionStrategy = InjectionStrategy.OPTIONAL)
    private MagentoGraphqlClient magentoGraphqlClient;
     
    @Self
    @Via(type = ResourceSuperType.class)
    private ProductTeaser productTeaser;
 
    @Self
    @Via("resource")
    private ValueMap properties;
 
    @ValueMapValue
    private String label; //maps variable to jcr property named "label" persisted by Dialog
 
 
    private CombinedSku combinedSku;
    private AbstractProductRetriever productRetriever;
 
    @PostConstruct
    protected void initModel() {
 
        String selection = properties.get(SELECTION_PROPERTY, String.class);
        if (selection != null && !selection.isEmpty()) {
            if (selection.startsWith("/")) {
                selection = StringUtils.substringAfterLast(selection, "/");
            }
            combinedSku = CombinedSku.parse(selection);
 
            // Fetch product data
            if (magentoGraphqlClient != null) {
                productRetriever = new ProductRetriever(magentoGraphqlClient);
                productRetriever.setIdentifier(combinedSku.getBaseSku());
            }
        }
    }
 
    @JsonIgnore
    private ProductInterface getProduct() {
        if (productRetriever == null) {
            return null;
        }
 
        ProductInterface baseProduct = productRetriever.fetchProduct();
        if (combinedSku.getVariantSku() != null && baseProduct instanceof ConfigurableProduct) {
            ConfigurableProduct configurableProduct = (ConfigurableProduct) baseProduct;
            SimpleProduct variant = findVariant(configurableProduct, combinedSku.getVariantSku());
            if (variant != null) {
                return variant;
            }
        }
        return baseProduct;
    }
 
    private SimpleProduct findVariant(ConfigurableProduct configurableProduct, String variantSku) {
        List<ConfigurableVariant> variants = configurableProduct.getVariants();
        if (variants == null || variants.isEmpty()) {
            return null;
        }
        return variants.stream().map(v -> v.getProduct()).filter(sp -> variantSku.equals(sp.getSku())).findFirst().orElse(null);
    }
 
    @Override
    public String getName() {
        if (getProduct() != null) {
            return getProduct().getName();
        }
        return null;
    }
 
    @Override
    public String getImage() {
        if (getProduct() != null) {
            return getProduct().getImage().getUrl();
        }
        return null;
    }
 
    @Override
    public String getSku() {
        return combinedSku != null ? StringUtils.defaultIfEmpty(combinedSku.getVariantSku(), combinedSku.getBaseSku()) : null;
    }
 
    @Override
    public Double getPriceRange() {
        if (getProduct() != null) {
            return getProduct().getPriceRange().getMinimumPrice().getRegularPrice().getValue();
        }
        return null;
    }
 
    // public getter method to expose value of private variable `label`
    // adds additional logic to default the label to "(Default)" if not set.
    @Override
    public String getLabel() {
        return StringUtils.isNotBlank(label) ? label : "(Default)";
    }
 
    // method required by `ComponentExporter` interface
    // exposes a JSON property named `:type` with a value of `mysite/components/prinfo`
    // required to map the JSON export to the SPA component props via the `MapTo`
    @Override
    public String getExportedType() {
        return PrinfoModelInfo.RESOURCE_TYPE;
    }
}