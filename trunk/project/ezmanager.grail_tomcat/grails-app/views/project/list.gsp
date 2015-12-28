<%@page import="ezmanager.*"%>
<%@page import="ezManager.taglib.jquery.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
		<g:javascript library="jquery" plugin="jquery"/>
		<jqui:resources themeCss="${resource(dir:'js/jquery/humanity',file:'jquery-ui-1.8.11.custom.css')}"/>
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
                   <div>
                   </div>
                      <table>
                         <tr>
                            <td>Project:</td>
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
						 	<td>Status:</td>
						 	<td><g:select name="statusId" value="${statusId}" from="${BaseStatus.listOrderByName()}" optionKey="statusId" optionValue="name" noSelection="['':'All']"/></td>
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
                        
                            <g:sortableColumn property="projectId" title="${message(code: 'project.projectId.label', default: 'Project Id')}" width="7%" params="${params}"/>
                        
                            <g:sortableColumn property="name" title="${message(code: 'project.name.label', default: 'Name')}" params="${params}"/>
                        
                            <g:sortableColumn property="startDate" title="${message(code: 'project.startDate.label', default: 'Start Date')}" params="${params}"/>
                        
                            <g:sortableColumn property="endDate" title="${message(code: 'project.endDate.label', default: 'End Date')}" params="${params}"/>
                        
                            <th><g:message code="project.status.label" default="Status" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectInstanceList}" status="i" var="projectInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${projectInstance.projectId}">${fieldValue(bean: projectInstance, field: "projectId")}</g:link></td>
                        
                            <td>${fieldValue(bean: projectInstance, field: "name")}</td>
                        
                            <td><g:formatDate date="${projectInstance.startDate}" /></td>
                        
                            <td><g:formatDate date="${projectInstance.endDate}" /></td>
                        
                            <td>${fieldValue(bean: projectInstance, field: "status.name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${projectInstanceTotal}" params="${params}"/>
            </div>
        </div>
    </body>
</html>