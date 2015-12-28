<%@page import="ezmanager.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'address.label', default: 'Address')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${addressInstance}">
            <div class="errors">
                <g:renderErrors bean="${addressInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${addressInstance?.addressId}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="addressId"><g:message code="address.addressId.label" default="Address Id" /></label>
                                </td>
                                <td valign="top" class="value">${fieldValue(bean: addressInstance, field: "addressId")}</td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="streetNumber"><g:message code="address.streetNumber.label" default="Street Number" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'streetNumber', 'errors')}">
                                    <g:textField name="streetNumber" value="${fieldValue(bean: addressInstance, field: 'streetNumber')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="street"><g:message code="address.street.label" default="Street" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'street', 'errors')}">
                                    <g:select name="street.streetId" from="${Street.list()}" optionKey="streetId" optionValue="name" value="${addressInstance?.street?.streetId}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="zip"><g:message code="address.zip.label" default="Zip" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'zip', 'errors')}">
                                    <g:textField name="zip" maxlength="20" value="${addressInstance?.zip}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="remark"><g:message code="address.remark.label" default="Remark" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'remark', 'errors')}">
                                    <g:textArea name="remark" cols="40" rows="5" value="${addressInstance?.remark}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="bActive"><g:message code="address.bActive.label" default="BA ctive" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'bActive', 'errors')}">
                                    <g:textField name="bActive" maxlength="1" value="${addressInstance?.bActive}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="customerList"><g:message code="address.customerList.label" default="Customer List" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'customerList', 'errors')}">
                                    <g:select name="customerList" from="${Customer.listOrderByName()}" multiple="yes" optionKey="id" size="5" value="${addressInstance?.customerList*.customerId}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
