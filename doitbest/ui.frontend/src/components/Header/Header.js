/* eslint-disable no-useless-constructor */
import React, { Component } from "react";
import { MapTo } from '@adobe/aem-react-editable-components';
import { withRouter } from "react-router-dom";

require('./Header.scss');

export const HeaderEditConfig = {
  emptyLabel: "Header",

  isEmpty: function (props) {
    return !props || !props.items || props.items.length < 1;
  },
};

export default class Header extends Component {
  constructor(props) {
    super(props);
    console.log(this.props);
  }
  render() {
    if (HeaderEditConfig.isEmpty(this.props)) {
      return null;
    }
    return (
      <header className="header">
        <div className="header-container">
          {/* <div className="row"> */}
            <div className="logo"></div>
            <div className="store-info-selector"></div>
            <div className="secondary-menu">
              <div>Monthly Circular</div>
              <div>Best Rewards</div>
              <div>About Us</div>
            </div>
          {/* </div> */}
        </div>
      </header>
    );
  }
}

MapTo('doitbest/components/header')(withRouter(Header), HeaderEditConfig);

