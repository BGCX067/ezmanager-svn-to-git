


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cost.label', default: 'Cost')}" />
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
            <g:hasErrors bean="${costInstance}">
            <div class="errors">
                <g:renderErrors bean="${costInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${costInstance?.id}" />
                <g:hiddenField name="version" value="${costInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="costId"><g:message code="cost.costId.label" default="Cost Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: costInstance, field: 'costId', 'errors')}">
                                    <g:textField name="costId" value="${fieldValue(bean: costInstance, field: 'costId')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="cost.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: costInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="100" value="${costInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="amountProposed"><g:message code="cost.amountProposed.label" default="Amount Proposed" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: costInstance, field: 'amountProposed', 'errors')}">
                                    <g:textField name="amountProposed" value="${fieldValue(bean: costInstance, field: 'amountProposed')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="totalHours"><g:message code="cost.totalHours.label" default="Total Hours" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: costInstance, field: 'totalHours', 'errors')}">
                                    <g:textField name="totalHours" value="${fieldValue(bean: costInstance, field: 'totalHours')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="taskIdTask"><g:message code="cost.taskIdTask.label" default="Task" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: costInstance, field: 'taskIdTask', 'errors')}">
                                    <g:select name="taskIdTask.taskId" from="${Task.list()}" optionKey="taskId" optionValue="name" value="${costInstance?.taskIdTask?.taskId}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="vatIdBaseVat"><g:message code="cost.vatIdBaseVat.label" default="Vat" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: costInstance, field: 'vatIdBaseVat', 'errors')}">
                                    <g:select name="vatIdBaseVat.vatId" from="${BaseVat.list()}" optionKey="vatId" optionValue="name" value="${costInstance?.vatIdBaseVat?.vatId}"  />
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
