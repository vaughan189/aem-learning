package com.mysite.core.models;
 
import org.osgi.annotation.versioning.ProviderType;
 
import com.adobe.cq.export.json.ComponentExporter;
 
 
@ProviderType
public interface PrinfoModel extends ComponentExporter {
     
    public String getLabel();
 
    public String getName();
 
    public String getImage();
 
    public String getSku();
 
    public Double getPriceRange();
}