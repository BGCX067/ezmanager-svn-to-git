<%@page import="ezmanager.taglib.jquery.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}" />
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
                            <td>Task:</td>
                            <td><g:textField name="name" value="${name}"/></td>
                         </tr>
                         <tr>
                            <td>Start date:</td>
                            <td><g:jqDatePicker name="startDate" value="${params.startDate}"/></td>
                         </tr>
                         <tr>
                            <td>End date:</td>
                            <td><g:jqDatePicker name="endDate" value="${params.endDate}"/></td>
                         </tr>
						 <tr>
						 	<td>Position:</td>
						 	<td>Min:<g:textField name="positionMin" value="${positionMin}"/></td>
						 	<td>Max:<g:textField name="positionMax" value="${positionMax}"/></td>
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
                        
                            <g:sortableColumn property="taskId" title="${message(code: 'task.taskId.label', default: 'Task Id')}" width="7%" params="${params}"/>
                        
                            <g:sortableColumn property="name" title="${message(code: 'task.name.label', default: 'Name')}" params="${params}"/>
                        
                            <g:sortableColumn property="position" title="${message(code: 'task.position.label', default: 'Position')}" params="${params}"/>
                        
                            <g:sortableColumn property="startDate" title="${message(code: 'task.startDate.label', default: 'Start Date')}" params="${params}"/>
                        
                            <g:sortableColumn property="endDate" title="${message(code: 'task.endDate.label', default: 'End Date')}" params="${params}"/>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${taskInstanceList}" status="i" var="taskInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${taskInstance.taskId}">${fieldValue(bean: taskInstance, field: "taskId")}</g:link></td>
                        
                            <td>${fieldValue(bean: taskInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: taskInstance, field: "position")}</td>
                        
                            <td><g:formatDate date="${taskInstance.startDate}" /></td>
                        
                            <td><g:formatDate date="${taskInstance.endDate}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${taskInstanceTotal}" params="${params}"/>
            </div>
        </div>
    </body>
</html>
