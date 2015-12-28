

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'executedTask.label', default: 'ExecutedTask')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="executedTask.executedTaskId.label" default="Executed Task Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: executedTaskInstance, field: "executedTaskId")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="executedTask.totalHours.label" default="Total Hours" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: executedTaskInstance, field: "totalHours")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="executedTask.amount.label" default="Amount" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: executedTaskInstance, field: "amount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="executedTask.startDate.label" default="Start Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${executedTaskInstance?.startDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="executedTask.endDate.label" default="End Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${executedTaskInstance?.endDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="executedTask.executedByUserIdUserIdentity.label" default="Executed By User" /></td>
                            
                            <td valign="top" class="value"><g:link controller="userIdentity" action="show" id="${executedTaskInstance?.executedByUserIdUserIdentity?.userIdentityId}">${executedTaskInstance?.executedByUserIdUserIdentity?.name}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="executedTask.taskIdTask.label" default="Task" /></td>
                            
                            <td valign="top" class="value"><g:link controller="task" action="show" id="${executedTaskInstance?.taskIdTask?.taskId}">${executedTaskInstance?.taskIdTask?.name}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${executedTaskInstance?.executedTaskId}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
