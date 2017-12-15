<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<ul>
  <!-- 递归  宏定义 -->
  <#macro bpTree children>
  <#if children?? && children?size gt 0>
    <#list children as child>
    <#if child.children?? && child.children?size gt 0>
      <li class="treeview">
        <a href="javascript:void(0)">
          <i class="${child.iconCls}" aria-hidden="true"></i> <span>${child.menuname}</span>
          <i class="fa fa-angle-left pull-right" aria-hidden="true"></i>
        </a>
        <ul class="treeview-menu">
          <@bpTree children=child.children />
        </ul>
      </li>
    <#else>
      <li><a href="javascript:void(0)" onclick="loadContent('${child.url}')"><i class="${child.iconCls}" aria-hidden="true"></i>${child.menuname}</a></li>   
    </#if>
    </#list>
  </#if>
  </#macro>
  <!-- 调用宏 生成递归树 -->
  <@bpTree children=treeMenu />
</ul>