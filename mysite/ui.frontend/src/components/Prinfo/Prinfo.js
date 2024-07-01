import React from 'react';
import {MapTo} from '@adobe/aem-react-editable-components';
 
 
// Logic to render placeholder or component
const PrinfoEditConfig = {
 
    emptyLabel: 'Prinfo',
    isEmpty: function(props) {
        return !props || !props.label;
    }
};
 
// Wrapper function that includes react-open-weather component
function ReactPrinfoWrapper(props) {
 
    return (
        <div className="prinfo">
            <table border="1"><tr><th>
            <p>{props.label}</p>
            <img src={props.image} alt="product" width="80%"/>
            <p>{props.name} {props.sku}</p>
            <p>$ {props.priceRange}</p>
            </th></tr></table>
        </div>
    );
}
 
export default function Prinfo(props) {
 
        // render nothing if component not configured
        if(PrinfoEditConfig.isEmpty(props)) {
            return null;
        }
 
        // render ReactPr component if component configured
        // pass props to ReactPrinfoWrapper. These props include the mapped properties from AEM JSON
        return ReactPrinfoWrapper(props);
 
}
 
// Map OpenWeather to AEM component
MapTo('mysite/components/prinfo')(Prinfo, PrinfoEditConfig);