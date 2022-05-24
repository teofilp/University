import { Breadcrumb } from "antd";
import { Content } from "antd/lib/layout/layout";
import React, { PropsWithChildren } from "react";
import { UserFooter } from "./footer";
import { UserHeader } from "./header";
import { Sider } from "./sider";

import "./main.css";

export const Layout = ({ children }: PropsWithChildren<any>) => {
  return (
    <div id="layout">
      <UserHeader />
      <Content id="layout-inner">
        <Sider />
        <div id="layout-content-wrapper">
          <Breadcrumb style={{ margin: 16 }}>
            <Breadcrumb.Item></Breadcrumb.Item>
            <Breadcrumb.Item>Dashboard</Breadcrumb.Item>
            <Breadcrumb.Item>Test Page</Breadcrumb.Item>
          </Breadcrumb>
          <div id="layout-content">{children}</div>
        </div>
      </Content>
      <UserFooter />
    </div>
  );
};
