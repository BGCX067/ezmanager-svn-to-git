<%@page import="ezmanager.taglib.jquery.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'executedTask.label', default: 'Executed Task')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
               <g:form method="post" >
                   <div class="dialog">
                      <table>
                         <tr>
                            <td>Total hours</td>
                            <td>Min:<g:textField name="totalHoursMin" value="${totalHoursMin}"/></td>
                            <td>Max:<g:textField name="totalHoursMax" value="${totalHoursMax}"/></td>
                         </tr>
                         <tr>
                            <td>Amount</td>
                            <td>Min:<g:textField name="amountMin" value="${amountMin}"/></td>
                            <td>Max:<g:textField name="amountMax" value="${amountMax}"/></td>
                         </tr>
                         <tr>
                            <td>Start date:</td>
                            <td><g:jqDatePicker name="startDate" value="${params.startDate}"/></td>
                         </tr>
                         <tr>
                            <td>End date:</td>
                            <td><g:jqDatePicker name="endDate" value="${params.endDate}"/></td>
                         </tr>
                      </table>
                   </div>
                   <div class="buttons">
                      <span class="button"><g:actionSubmit class="search" action="list" value="${message(code: 'default.button.search.label', default: 'Search')}" /></span>
                   </div>
               </g:form>
            
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="executedTaskId" title="${message(code: 'executedTask.executedTaskId.label', default: 'Executed Task Id')}" width="10%" params="${params}"/>
                        
                            <g:sortableColumn property="totalHours" title="${message(code: 'executedTask.totalHours.label', default: 'Total Hours')}" params="${params}"/>
                        
                            <g:sortableColumn property="amount" title="${message(code: 'executedTask.amount.label', default: 'Amount')}" params="${params}"/>
                        
                            <g:sortableColumn property="startDate" title="${message(code: 'executedTask.startDate.label', default: 'Start Date')}" params="${params}"/>
                        
                            <g:sortableColumn property="endDate" title="${message(code: 'executedTask.endDate.label', default: 'End Date')}" params="${params}"/>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${executedTaskInstanceList}" status="i" var="executedTaskInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${executedTaskInstance.executedTaskId}">${fieldValue(bean: executedTaskInstance, field: "executedTaskId")}</g:link></td>
                        
                            <td>${fieldValue(bean: executedTaskInstance, field: "totalHours")}</td>
                        
                            <td>${fieldValue(bean: executedTaskInstance, field: "amount")}</td>
                        
                            <td><g:formatDate date="${executedTaskInstance.startDate}" /></td>
                        
                            <td><g:formatDate date="${executedTaskInstance.endDate}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${executedTaskInstanceTotal}" params="${params}"/>
            </div>
        </div>
    </body>
</html>
