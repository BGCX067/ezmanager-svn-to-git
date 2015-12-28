


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'executedTask.label', default: 'ExecutedTask')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${executedTaskInstance}">
            <div class="errors">
                <g:renderErrors bean="${executedTaskInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalHours"><g:message code="executedTask.totalHours.label" default="Total Hours" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: executedTaskInstance, field: 'totalHours', 'errors')}">
                                    <g:textField name="totalHours" value="${fieldValue(bean: executedTaskInstance, field: 'totalHours')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="executedTask.amount.label" default="Amount" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: executedTaskInstance, field: 'amount', 'errors')}">
                                    <g:textField name="amount" value="${fieldValue(bean: executedTaskInstance, field: 'amount')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startDate"><g:message code="executedTask.startDate.label" default="Start Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: executedTaskInstance, field: 'startDate', 'errors')}">
                                    <g:datePicker name="startDate" precision="day" value="${executedTaskInstance?.startDate}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endDate"><g:message code="executedTask.endDate.label" default="End Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: executedTaskInstance, field: 'endDate', 'errors')}">
                                    <g:datePicker name="endDate" precision="day" value="${executedTaskInstance?.endDate}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="executedByUserIdUserIdentity"><g:message code="executedTask.executedByUserIdUserIdentity.label" default="Executed By User" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: executedTaskInstance, field: 'executedByUserIdUserIdentity', 'errors')}">
                                    <g:select name="executedByUserIdUserIdentity.userIdentityId" from="${UserIdentity.list()}" optionKey="userIdentityId" optionValue="name" value="${executedTaskInstance?.executedByUserIdUserIdentity?.userIdentityId}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="taskIdTask"><g:message code="executedTask.taskIdTask.label" default="Task" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: executedTaskInstance, field: 'taskIdTask', 'errors')}">
                                    <g:select name="taskIdTask.taskId" from="${Task.list()}" optionKey="taskId" optionValue="name" value="${executedTaskInstance?.taskIdTask?.taskId}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>