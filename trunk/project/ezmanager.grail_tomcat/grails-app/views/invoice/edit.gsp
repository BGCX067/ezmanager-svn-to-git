<%@page import="ezmanager.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'invoice.label', default: 'Invoice')}" />
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
            <g:hasErrors bean="${invoiceInstance}">
            <div class="errors">
                <g:renderErrors bean="${invoiceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${invoiceInstance?.invoiceId}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="invoiceId"><g:message code="invoice.invoiceId.label" default="Invoice Id" /></label>
                                </td>
                                <td valign="top" class="value">${fieldValue(bean: invoiceInstance, field: "invoiceId")}</td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="position"><g:message code="invoice.position.label" default="Position" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: invoiceInstance, field: 'position', 'errors')}">
                                    <g:textField name="position" value="${fieldValue(bean: invoiceInstance, field: 'position')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="executedTask"><g:message code="invoice.executedTask.label" default="Executed Task" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: invoiceInstance, field: 'executedTask', 'errors')}">
                                    <g:select name="executedTask.executedTaskId" from="${ExecutedTask.list()}" optionKey="executedTaskId" optionValue="name" value="${invoiceInstance?.executedTask?.executedTaskId}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="customerList"><g:message code="invoice.customerList.label" default="Customer List" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: invoiceInstance, field: 'customerList', 'errors')}">
                                    
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
