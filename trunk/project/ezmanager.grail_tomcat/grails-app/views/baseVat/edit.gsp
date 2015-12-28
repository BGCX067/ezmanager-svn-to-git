<%@page import="ezmanager.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'baseVat.label', default: 'Vat')}" />
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
            <g:hasErrors bean="${baseVatInstance}">
            <div class="errors">
                <g:renderErrors bean="${baseVatInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${baseVatInstance?.vatId}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="vatId"><g:message code="baseVat.vatId.label" default="Vat Id" /></label>
                                </td>
                                <td valign="top" class="value">${fieldValue(bean: baseVatInstance, field: "vatId")}</td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="baseVat.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: baseVatInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="100" value="${baseVatInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="value"><g:message code="baseVat.value.label" default="Value" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: baseVatInstance, field: 'value', 'errors')}">
                                    <g:textField name="value" value="${fieldValue(bean: baseVatInstance, field: 'value')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="currency"><g:message code="baseVat.currency.label" default="Currency" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: baseVatInstance, field: 'currency', 'errors')}">
                                    <g:select name="currency.currencyId" from="${BaseCurrency.listOrderByName()}" optionKey="currencyId" optionValue="name" value="${baseVatInstance?.currency?.currencyId}"  /> 
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
