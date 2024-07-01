package com.mysite.core.models;
 
import com.adobe.cq.commerce.core.components.client.MagentoGraphqlClient;
import com.adobe.cq.commerce.core.components.models.retriever.AbstractProductRetriever;
import com.adobe.cq.commerce.magento.graphql.BundleProductQueryDefinition;
import com.adobe.cq.commerce.magento.graphql.GroupedProductQueryDefinition;
import com.adobe.cq.commerce.magento.graphql.ProductInterfaceQueryDefinition;
import com.adobe.cq.commerce.magento.graphql.SimpleProductQueryDefinition;
import com.adobe.cq.commerce.magento.graphql.SwatchDataInterfaceQuery;
 
 
public class ProductRetriever extends AbstractProductRetriever{
 
    public ProductRetriever(MagentoGraphqlClient client) {
        super(client);
    }
 
    private SimpleProductQueryDefinition generateSimpleProductQuery() {
        return q -> {
            q.sku()
                .name()
                .description(d -> d.html())
                .image(i -> i.label().url())
                .thumbnail(t -> t.label().url())
                .urlKey()
                .stockStatus()
                .color()
                .priceRange(r -> r
                    .minimumPrice(generatePriceQuery()))
                .mediaGallery(g -> g
                    .disabled()
                    .url()
                    .label()
                    .position())
                .categories(c -> c
                    .uid()
                    .name()
                    .image());
 
            // Apply product variant query hook
            if (variantQueryHook != null) {
                variantQueryHook.accept(q);
            }
        };
    }
 
    @Override
    protected ProductInterfaceQueryDefinition generateProductQuery() {
        return q -> {
            q.sku()
                .urlKey()
                .name()
                .description(d -> d.html())
                .image(i -> i.label().url())
                .thumbnail(t -> t.label().url())
                .stockStatus()
                .metaDescription()
                .metaKeyword()
                .metaTitle()
                .priceRange(r -> r
                    .minimumPrice(generatePriceQuery()))
                .mediaGallery(g -> g
                    .disabled()
                    .url()
                    .label()
                    .position())
                .categories(c -> c
                    .uid()
                    .name()
                    .image())
                .onConfigurableProduct(cp -> cp
                    .priceRange(r -> r
                        .maximumPrice(generatePriceQuery()))
                    .configurableOptions(o -> o
                        .label()
                        .attributeCode()
                        .values(v -> v
                            .valueIndex()
                            .label()
                            .defaultLabel()
                            .swatchData(SwatchDataInterfaceQuery::value)))
                    .variants(v -> v
                        .attributes(a -> a
                            .code()
                            .valueIndex())
                        .product(generateSimpleProductQuery())))
                .onGroupedProduct(generateGroupedProductQuery())
                .onBundleProduct(generateBundleProductQuery());
 
            // Apply product query hook
            if (productQueryHook != null) {
                productQueryHook.accept(q);
            }
        };
    }
 
    private GroupedProductQueryDefinition generateGroupedProductQuery() {
        return gp -> gp
            .items(i -> i
                .position()
                .qty()
                .product(p -> p
                    .sku()
                    .name()
                    .priceRange(r -> r
                        .minimumPrice(generatePriceQuery()))));
    }
 
    private BundleProductQueryDefinition generateBundleProductQuery() {
        return bp -> bp
            .priceRange(r -> r
                .maximumPrice(generatePriceQuery()));
    }
     
}